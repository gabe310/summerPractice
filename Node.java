public class Node {

    private int id;     //instance variables
    private boolean mark;

    public Node (int id) {      //constructor method
        this.id = id;
    }


    public void markNode (boolean mark) {       //markNode method in which makes the node whatever the mark is
        this.mark = mark;
    }

    public boolean getMark() {      //returns the marked if its true or false
        return mark;
    }

    public int getId() {    //returns the getId
        return id;
    }



}
