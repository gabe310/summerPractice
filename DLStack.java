package assignment3;

public class DLStack<T> implements DLStackADT<T>{

    private DoubleLinkedNode<T> top = new DoubleLinkedNode<T>();        //instance variables, of top and numitems
    private int numItems;

    public DLStack() {      //creates empty stack
        top = null;
        numItems = 0;
    }


    public void push(T dataItem) {
        DoubleLinkedNode<T> node = new DoubleLinkedNode<>(dataItem);

        if (top == null) {  //if stack empty then sets top to node
            top = node;
        }

        else {
            node.setPrevious(top);      //sets top to previous, so now node is on top
            top.setNext(node);          // top's next is new node
            top = node;                 //node is now set to top
        }

        numItems++;         //increment items
    }


    public T pop() throws EmptyStackException {

        if (top == null) {          //if empty then throws error
            throw new EmptyStackException("Empty stack");
        }

        T item = top.getElement();      // item is top element

        if (numItems == 1) {        //if statement when numitems is 1, top becomes empty, so it doesnt create null pointer exception
            top = null;
        }
        else {
            top = top.getPrevious();        // top is now the one under it
            top.setNext(null);              //top's next is null so its at the very top
        }

        numItems--;         //decrement item
        return item;
    }


    public T pop(int k) throws InvalidItemException {

        if (k > numItems || k <= 0) {           //invalid item if its 0 or less, or more than the items
            throw new InvalidItemException("Invalid Item");
        }

        T item;  //since we are not only popping the top, i cant assign item to only the top

        if (k == 1) {
            item = pop();       //easily call pop
        }

        else {
            DoubleLinkedNode<T> current = top;

            for (int i = 1; i < k; i++) {
                current = current.getPrevious();     //assigns current to previous to move the node backwards from top to bottom of stack
            }

            item = current.getElement();        //assigns item to current element

            if (k == numItems) {        //if its at the end current is current get next, and current's previous is null (the tail)
                current = current.getNext();
                current.setPrevious(null);
            }

            else {          //else its inbetween 2 nodes
                DoubleLinkedNode<T> previous = current.getPrevious();           //previous is currents previous
                current = current.getNext();            //current is now currents next

                current.setPrevious(previous);              //sets currents previous to the previous node and then previous next is current, so the middle node is gone
                previous.setNext(current);
            }
        }

        numItems--;     //decrement item
        return item;
    }


    public T peek() throws EmptyStackException {        //if num items is 0, then it gives empty error
        if (numItems == 0) {
            throw new EmptyStackException("Empty stack");
        }

        return top.getElement();        //returns value of top
    }


    public boolean isEmpty() {      //if numitem is 0 then its empty
        return numItems == 0;
    }


    public int size() {     //return num items of size
        return numItems;
    }


    public DoubleLinkedNode<T> getTop() {       //return top
        return top;
    }


    public String toString() {

        String s = "";
        DoubleLinkedNode<T> current = top;      //starts at the top

        while (current != null) {
            s = s + current.getElement().toString() + " ";
            current = current.getPrevious();                    //assigns it backwards to the bottom of the stack
        }

        return s;
    }

}
