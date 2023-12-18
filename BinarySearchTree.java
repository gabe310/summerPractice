
public class BinarySearchTree implements BinarySearchTreeADT{

    private BNode r;        //instance variable

    public BinarySearchTree() {     // constructor method
        this.r = new BNode();           //this is the root as it is the first node

    }


    @Override
    public BNode getRoot() {        //returns the root
        return r;
    }

    @Override
    public Pel get(BNode r, Location key) {     //get method

        //returns either null or returns the data

        if (r.isLeaf()) {       //if r node is a leaf then you return it back as its at the end of the traverse
            return r.getData();     //returns null if it is a leaf
            // base case
        }

        else {
            if (r.getData().getLocus().compareTo(key) == 0) {       //if they equal they you found the node data with the key
                return r.getData();
            }
            else if (r.getData().getLocus().compareTo(key) == 1) {      //goes to the left side and calls recursively
                return get(r.leftChild(), key);
            }
            else {
                return get(r.rightChild(), key);        //goes to right side and calls recursively
            }
        }

    }

    private BNode getNode(BNode r, Location key) {      //private helper method, the same thing as the get method but this returns the node instead of pel

        if (r.isLeaf()) {       //if its a leaf node, meaning its data is null
            return r;
        }

        else {
            if (r.getData().getLocus().compareTo(key) == 0) {   //if the node has the same key as the key im looking for
                return r;
            }

            else if(r.getData().getLocus().compareTo(key) == 1) {       //goes to left child side
                return getNode(r.leftChild(), key);
            }
            else {
                return getNode(r.rightChild(), key);            //goes to right child side
            }
        }

    }


    @Override
    public void put(BNode r, Pel data) throws DuplicatedKeyException {      //put method

        BNode p = getNode(r, data.getLocus());      //current node with r and data, gets the node with the data

        Pel a = get(r, data.getLocus());            //get method for pel, so that i can use this for duplicate key exception

        if (a != null) {        //if a isnt null, it means that there is a key, so it could be a duplicate
            if (a.getLocus().compareTo(data.getLocus()) == 0) {     //checks the key if its the same one and throws error
                throw new DuplicatedKeyException("key already exists");
            }
        }

        if (p.isLeaf()) {       //if current node p is a leaf
            p.setContent(data);     //i set the data i want to add to this node

            BNode left = new BNode();       //make a left and right child empty
            BNode right = new BNode();

            p.setLeftChild(left);       //set pointers so that left is current node p left child and vice versa for right side
            p.setRightChild(right);

            left.setParent(p);      //set left and right parent to p
            right.setParent(p);

        }
    }

    @Override
    public void remove(BNode r, Location key) throws InexistentKeyException {       //remove method

        BNode p = getNode(r, key);      //p is current node and gets node with location of key
        Pel a = get(r, key);            //sets as the data

        if (a == null || !(p.getData().getLocus().compareTo(key) == 0) ) {  //if the data is null or it doesnt equal the key we got to remove, then it doesnt exist
            throw new InexistentKeyException("key doesn't exist");
        }


        if(!p.isLeaf()) {   //if p is not a leaf

            if (p.leftChild().isLeaf() || p.rightChild().isLeaf()) {        //if there is atleast a 1 leaf in the p node

                BNode newParent = p.parent();   //new parent is parent of p, which p is the node i want to remove


                if (p.leftChild().isLeaf()) {       //if the left child is a leaf

                    BNode c1 = p.rightChild();      // c1 is set as the other child so it is right child

                    if (newParent != null) {                //if new parent != null
                        if (newParent.leftChild() == p) {       //if new parent left child is p, then change the pointers. this makes sure its the right one, else its the other child to change pointers
                            newParent.setLeftChild(c1);
                        } else {
                            newParent.setRightChild(c1);
                        }
                    }
                    else {          //else it is a root node we need to remove

                        c1.setParent(newParent);        //c1 new parent is null and then set the root as c1
                        this.r = c1;

                    }
                }

                else if (p.rightChild().isLeaf()) {     //if right child is a leaf

                    BNode c2 = p.leftChild();       //sets other child as left child

                    if (newParent != null) {                    //same thing as above
                        if (newParent.rightChild() == p) {
                            newParent.setRightChild(c2);
                        }
                        else {
                            newParent.setLeftChild(c2);
                        }
                    }
                    else {

                        c2.setParent(newParent);        //set c2 as the new root node
                        this.r = c2;
                    }
                }


            }
            else {      //else it is an internal node with 2 internal children
                BNode s = null;     //create a null node
                try {
                    s = getNode(r,smallest(p.rightChild()).getLocus());     //get the node of smallest right child
                } catch (EmptyTreeException e) {
                    e.printStackTrace();
                }
                p.setContent(s.getData());      //set the data to se
                remove(s, p.getData().getLocus());      //then do a recursive call

                //this basically goes through the whole right side to get the node with no children with internal nodes, then deletes there and traverses
                // like a line of dominos falling to each other

            }

        }


    }

    @Override
    public Pel successor(BNode r, Location key) {       //successor method

        if (r.isLeaf()) {       //if the root is a leaf then return null
            return null;
        }

        else {
            BNode p = getNode(r, key);      // get the node and key

            if (!(p.isLeaf()) && !(p.rightChild().isLeaf()) ) {     //if the node is not a leaf and the right child is not a leaf
                try {
                    return smallest(p.rightChild());        //then i return the smallest child of right side
                } catch (EmptyTreeException e) {
                    e.printStackTrace();
                }
            }
            else {                                      //else i set p as parent
                p = p.parent();

                while (p != null && p.getData().getLocus().compareTo(key) == -1) {      //traverse through the whole right side
                    p = p.parent();
                }

                if (p == null) {        //if p is null then its empty
                    return null;
                }
                else {
                    return p.getData();     //else i return its node data there
                }
            }

            return p.getData();     //returns the data of node
        }
    }

    @Override
    public Pel predecessor(BNode r, Location key) {     //predecessor, it is the exact same as the one above but i switch it so it traverses through the left side and then i would return largest of left side

        if (r.isLeaf()) {
            return null;
        }

        else {
            BNode p = getNode(r, key);

            if (!(p.isLeaf()) && !(p.leftChild().isLeaf()) ) {
                try {
                    return largest(p.leftChild());
                } catch (EmptyTreeException e) {
                    e.printStackTrace();
                }
            }
            else {
                p = p.parent();

                while (p != null && p.getData().getLocus().compareTo(key) == 1) {
                    p = p.parent();
                }

                if (p == null) {
                    return null;
                }
                else {
                    return p.getData();
                }
            }

            return p.getData();
        }

    }

    @Override
    public Pel smallest(BNode r) throws EmptyTreeException {        //smallest method

        if (r.isLeaf()) {           //if root is a leaf then its empty tree
            throw new EmptyTreeException("empty tree error");
        }
        else {
            BNode p = r;                        //p is root and then goes through left side till its a leaf
            while (!(p.isLeaf())) {
                p = p.leftChild();
            }

            return p.parent().getData();        //returns the data of the one above as it is the one with data
        }
    }

    @Override
    public Pel largest(BNode r) throws EmptyTreeException {     //same thing as smallest method but its right side

        if (r.isLeaf()) {
            throw new EmptyTreeException("empty tree error");
        }
        else {
            BNode p = r;
            while (!(p.isLeaf())) {
                p = p.rightChild();
            }

            return p.parent().getData();
        }

    }
}
