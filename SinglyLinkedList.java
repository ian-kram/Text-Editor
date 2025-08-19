package TextEditor;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic class that represents a Singly-Linked List
 * that implements the List interface. The data structure of a
 * Singly-Linked List uses nodes as a means to store data.
 *
 * @author Ian Kramer
 * @version June 19, 2024
 */
public class SinglyLinkedList<T> implements List<T> {
    Node<T> head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * A generic nested class that represents a Node.
     * Within a node, they store data as well as a reference pointer
     * to the next node in the Linked-List.
     *
     * @param <T> - Generic Type
     */
    public class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Inserts an element at the beginning of the list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(T element) {
        if (head == null) {
            head = new Node<>(element, null);
        } else {
            Node<T> n = new Node<>(element, head);
            head = n;
        }
        size++;
    }

    /**
     * Inserts an element at a specific position in the list.
     *
     * @param index   - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void insert(int index, T element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if (index == 0)
            insertFirst(element);
        else {
            Node<T> current = head;
            int i = 0;
            while (current != null) {
                if (i + 1 == index) {
                    Node<T> n = new Node<>(element, current.next);
                    current.next = n;
                    break;
                }
                i++;
                current = current.next;
            }
            size++;
        }
    }

    /**
     * Gets the first element in the list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T getFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }

        return head.data;
    }

    /**
     * Gets the element at a specific position in the list.
     *
     * @param index - the specified position
     * @return the element at index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> current = head;
        int i = 0;
        while (current != null) {
            if (i == index) {
                return current.data;
            }
            current = current.next;
            i++;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Deletes and returns the first element from the list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T deleteFirst() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }

        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) //possible errors
            throw new IndexOutOfBoundsException();

        T data = null;

        if (index == 0)
            return deleteFirst();
        else {
            Node current = head;
            int i = 0;
            while (current != null) {
                if (i + 1 == index) {
                    data = (T) current.next.data;
                    current.next = current.next.next;
                    size--;
                    break;
                }
                i++;
                current = current.next;
            }
        }

        return data;
    }

    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    @Override
    public int indexOf(T element) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(element)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if this collection contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Removes all of the elements from this list.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Generates an array containing all of the elements in this list in proper sequence
     *
     * @return an array containing all of the elements in this list, in order
     */
    @Override
    public Object[] toArray() {
        Node<T> current = head;
        Object[] array = new Object[size];

        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }

        return array;
    }

    /**
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    public class SinglyLinkedListIterator implements Iterator<T>{
        private Node<T> current;
        private Node<T> previous;
        private boolean nextCalled;

        public SinglyLinkedListIterator() {
            current = head;
            previous = null;
            nextCalled = false;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            previous = current;
            current = current.next;
            nextCalled = true;
            return previous.data;
        }

        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException();
            }

            nextCalled = false;
            previous.next = current.next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator();
    }

}



