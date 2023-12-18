public class BNode {

    private Pel value;      //instance variables
    private BNode left;
    private BNode right;
    private BNode parent;

    public BNode (Pel value, BNode left, BNode right, BNode parent) {       //constructor method assigning the variables
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public BNode() {        //creates an empty node instead of already assigning one
        value = null;
        left = null;
        right = null;
        parent = null;
    }

    public BNode parent() {     //returns parent
        return parent;
    }

    public void setParent(BNode newParent) {        //sets a new parent
        parent = newParent;
    }

    public void setLeftChild(BNode p) {     //sets left child
        left = p;
    }

    public void setRightChild(BNode p) {        //sets right child
        right = p;
    }

    public void setContent(Pel value) {     //sets the data value of a node
        this.value = value;
    }

    public boolean isLeaf() {       //checks if a node is a leaf or not
        if (leftChild() == null && rightChild() == null) {      //if it is a leaf node then it wouldnt have any children, so the left and right should be null
            return true;
        }
        else {
            return false;
        }
    }

    public Pel getData() {      //returns value
        return value;
    }

    public BNode leftChild() {      //returns left child
        return left;
    }

    public BNode rightChild() {     //returns right child
        return right;
    }


}
