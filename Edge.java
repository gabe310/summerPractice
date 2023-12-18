public class Edge {

    private Node u;         //instance variables
    private Node v;
    private String type;

    public Edge (Node u, Node v, String type) {     //constructor method

        this.u = u;
        this.v = v;
        this.type = type;
    }

    public Node firstNode() {       //returns the first node u
        return u;
    }

    public Node secondNode() {      //returns the 2nd node v
        return v;
    }

    public String getType() {       //returns the type of edge it
        return type;
    }

}
