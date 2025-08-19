package TextEditor;

import java.util.NoSuchElementException;
/**
 * A class representing a stack through a Singly-Linked List
 * data structure.
 *
 * @author Ian Kramer
 * @version June 19, 2024
 *
 * @param <T> - the type of elements contained in the list
 */
public class LinkedListStack<T> implements Stack<T>{
    private SinglyLinkedList<T> stack;

    public LinkedListStack() {
        this.stack = new SinglyLinkedList<>();
    }

    public LinkedListStack(SinglyLinkedList<T> stack) {
        this.stack = stack;
    }

    /**
     * Removes all the elements from the stack list.
     */
    @Override
    public void clear() {
        stack.clear();
    }

    /**
     * @return true if the stack list contains no elements; false, otherwise.
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Returns, but does not remove, the element at the top of the stack list.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public T peek() throws NoSuchElementException {
        return stack.getFirst();
    }

    /**
     * Returns and removes the item at the top of the stack list.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public T pop() throws NoSuchElementException {
        return stack.deleteFirst();
    }

    /**
     * Adds a given element to the stack list, putting it at the top of the stack.
     *
     * @param element - the element to be added
     */
    @Override
    public void push(T element) {
        stack.insertFirst(element);
    }

    /**
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return stack.size();
    }
}
