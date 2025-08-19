package TextEditor;

import java.util.NoSuchElementException;

/**
 * A Text Editor that uses stacks to keep track of undo-able and redo-able actions.
 * Characters can be inserted, removed, undone, and redone.
 *
 * @author Ian Kramer
 * @version June 19, 2024
 */
public class TextEditor {
    private LinkedListStack<Edit> undoStack;
    private LinkedListStack<Edit> redoStack;
    private StringBuilder text;

    public TextEditor(){
        this.undoStack = new LinkedListStack<>();
        this.redoStack = new LinkedListStack<>();
        this.text = new StringBuilder();
    }

    public TextEditor(SinglyLinkedList<Edit> history){
        this.undoStack = new LinkedListStack<>();
        this.redoStack = new LinkedListStack<>();
        this.text = new StringBuilder();

        for(Edit e : history){
            e.apply(text);
        }
    }

    /**
     * Inserts the given character at the given position in the text.
     * @param character - a singular character (letter, number, symbol).
     * @param position - an index
     */
    public void insert(char character, int position){
        Edit edit = new Edit(character, position, Edit.INSERT);

        edit.apply(text);

        undoStack.push(edit);
        redoStack.clear();
    }

    /**
     * Removes the character in the text that is located at the given position.
     *
     * @param position - an index
     */
    public void remove(int position){
        char characterAtPosition = text.charAt(position);
        Edit edit = new Edit(characterAtPosition, position, Edit.REMOVE);

        edit.apply(text);

        undoStack.push(edit);
        redoStack.clear();
    }

    /**
     * Undoes the last action/modification to the text.
     *
     * @throws NoSuchElementException
     */
    public void undo() throws NoSuchElementException{
        if(undoStack.isEmpty())
            throw new NoSuchElementException();

        Edit edit = undoStack.pop();
        edit.revert(text);

        redoStack.push(edit);
    }

    /**
     * Redoes the last undone action to the text.
     *
     * @throws NoSuchElementException
     */
    public void redo() throws NoSuchElementException{
        if(redoStack.isEmpty())
            throw new NoSuchElementException();

        Edit edit = redoStack.pop();
        edit.apply(text);

        undoStack.push(edit);
    }

    /**
     * Generates a history of edits in the form of a list ordered
     * from least recently to most recently applied.
     *
     * @return - A SinglyLinkedList containing Edit objects.
     */
    public SinglyLinkedList<Edit> history(){
        SinglyLinkedList<Edit> editHistory = new SinglyLinkedList<>();
        LinkedListStack<Edit> tempStack = new LinkedListStack<>();

        while(!undoStack.isEmpty()) {
            Edit e = undoStack.pop();
            tempStack.push(e);
            editHistory.insertFirst(e);
        }
        while(!tempStack.isEmpty()) {
            undoStack.push(tempStack.pop());
        }

        return editHistory;
    }

    /**
     * Returns a readable String from the text.
     *
     * @return - String
     */
    public String toString(){
        return text.toString();
    }
}
