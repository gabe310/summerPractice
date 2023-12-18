
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Graph implements GraphADT {

    private int n;                      //instance variables
    private LinkedList<Edge>[] edges;
    private Node[] vertices;

    public Graph(int n) {       //constructor method

        this.n = n;

        vertices = new Node[n];         //creates an array of nodes called vertices (which are the amount of nodes)
        edges = new LinkedList[n];       //creates a linked list of edges with size n, the same size as vertices


        for (int i = 0; i < n; i++) {       // for loop in which creates a new node in each of the index of the vertices to its size which is n
            vertices[i] = new Node(i);
        }
    }


    @Override
    public void addEdge(Node nodeu, Node nodev, String edgeType) throws GraphException {
/* Adds to the graph an edge connecting the given nodes. The type of the edge is as indicated. Throws a GraphException
if either node does not exist or if the edge is already in the graph.*/


        if (nodeu.getId() >= vertices.length || nodev.getId() >= vertices.length) {     //if either nodes id is greater than the size of the array, then it doesnt exist
            throw new GraphException("Error");
        }

        //create a while loop to see if the edge exists in the linked lists
        //the while loop i check the linked list that is in array index nodeu
        //i only need to check for it at 1 side only,

        LinkedList<Edge> node = edges[nodeu.getId()];       //assigns node with its edges index at nodeu

        while (node != null) {      //loops through the linked list to see if the 2nd node equals the v node

            if (node.getElement() != null) {
                if (node.getElement().secondNode().getId() == nodev.getId()) {       //if v already exists in node u
                    throw new GraphException();
                }
            }

            node = node.getNext();
        }


        Edge newEdge = new Edge(nodeu, nodev, edgeType);        //creates a new edge


        if (edges[nodeu.getId()] == null) {                                 //if nodeu head is null, then just assign it here
            edges[nodeu.getId()] = new LinkedList<>(newEdge);
        }
        else {
            LinkedList<Edge> node1 = edges[nodeu.getId()];      //else i just traverse through the linked list till i get the end of it, to add it at the tail

            while (node1.getNext() != null) {
                node1 = node1.getNext();
            }

            node1.setNext(new LinkedList<Edge>(newEdge));
        }

/// the top and bottom are the same thing except i add for nodeu and nodev
// same loop, and same way i traverse through it

        if (edges[nodev.getId()] == null) {
            edges[nodev.getId()] = new LinkedList<>(newEdge);
        }
        else {
            LinkedList<Edge> node2 = edges[nodev.getId()];

            while (node2.getNext() != null) {
                node2 = node2.getNext();
            }

            node2.setNext(new LinkedList<Edge>(newEdge));
        }
    }


    @Override
    public Node getNode(int id) throws GraphException {     //get node method

        if (id <= vertices.length) { //if the id is less than the size, it must be true that it exists
            return vertices[id];
        }
        else {      //else  its larger than the size then throw an error
            throw new GraphException("error");
        }
    }

    @Override
    public Iterator incidentEdges(Node u) throws GraphException {
  /* Returns a Java Iterator storing all the edges incident on the specified node. It returns null if the node does
  not have any edges incident on it. Throws a GraphException if the node does not exist. */

        if (u.getId() >= vertices.length) {     //if node is greater than the size then it doesnt exist
            throw new GraphException("Error");
        }

        LinkedList<Edge> node = edges[u.getId()];
        ArrayList<Edge> list = new ArrayList<>();


        while (node != null) {             //while loop adding the edges into an arraylist
            list.add(node.getElement());
            node = node.getNext();
        }

        Iterator iter = list.iterator();        //then i set this list to an interator, and i return it as that

        return iter;

    }

    @Override
    public Edge getEdge(Node u, Node v) throws GraphException {
        /* Returns the edge connecting the given nodes. Throws a GraphException if there is no edge conencting the given
		      nodes, or if u or v do not exist. */
// theres 2 situations to throw an error, if the nodes id are higher than the graph.

        if (u.getId() >= vertices.length || v.getId() >= vertices.length) {     //if either node is greater than the size of nodes, it dne
            throw new GraphException("Error");
        }

        LinkedList<Edge> node = edges[u.getId()];


        while (node != null) {
            if (node.getElement() != null) {
                if (node.getElement().secondNode() == v || node.getElement().firstNode() == v) {       //if v exists in node u
                    return node.getElement();

                    //i go through the linked list of nodeu, and see if either v is in 1st or 2nd node, telling me that the edge with node u and v exists,
                    // so i then return that edge

                }
            }

            node = node.getNext();
        }

        return null;        //else it DNE and returns null
    }



    @Override
    public boolean areAdjacent(Node u, Node v) throws GraphException {
  /* Returns true is u and v are adjacent, and false otherwise.
  It throws a GraphException if either node does not exist. */

        if (u.getId() >= vertices.length || v.getId() >= vertices.length) {     //if either nodes are greater than the size, i throw error
            throw new GraphException("Error");
        }


        if (getEdge(u,v) != null) {         //if getedges returns doesnt return null its true that they are adjacent, else it its false
            return true;
        }
        else {
            return false;
        }

    }
}
