public class LinkedList<Edge> {

    private LinkedList<Edge> next;      //instance variables
    private Edge element;

    public LinkedList()     //constructor method
    {
        next = null;
        element = null;
    }

    public LinkedList(Edge elem)        //setting the elem of the node
    {
        next = null;
        element = elem;
    }


    public LinkedList<Edge> getNext()       //get next method
    {
        return next;
    }

    public void setNext (LinkedList<Edge> node)     //setting next
    {
        next = node;
    }

    public Edge getElement()        //returning the element of the node
    {
        return element;
    }

    public void setElement (Edge elem)      //setting the element of current
    {
        element = elem;
    }
}
