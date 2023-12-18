public class MyObject implements MyObjectADT{

    private int id;             //instance variables
    private int width;
    private int height;
    private String type;
    private Location pos;
    private BinarySearchTree tree;


    public MyObject (int id, int width, int height, String type, Location pos) {        //constructor method

        this.id = id;
        this.width = width;
        this.height = height;
        this.type = type;
        this.pos = pos;
        tree = new BinarySearchTree();
    }


    @Override
    public int getWidth() {     //returns width
        return width;
    }

    @Override
    public int getHeight() {        //returns height
        return height;
    }

    @Override
    public String getType() {       //returns type
        return type;
    }

    @Override
    public int getId() {        //returns id
        return id;
    }

    @Override
    public Location getLocus() {        //returns position of object
        return pos;
    }

    @Override
    public void setLocus(Location value) {      //sets a new value
        pos = value;
    }

    @Override
    public void setType(String t) {     //sets a new type
        type = t;
    }



    @Override
    public void addPel(Pel pix) throws DuplicatedKeyException {     //need to insert pix into binary search tree

        tree.put(tree.getRoot(), pix);      //use the put method to add pix in a node, starting from the root
    }

    @Override
    public boolean intersects(MyObject fig) {       //intersects method

        Pel p = null;       //have a pel p as null

        try {
            p = tree.smallest(tree.getRoot());            // p will return smallest node of the tree
        } catch (EmptyTreeException e) {
            e.printStackTrace();
        }


        while(p != null) {      //goes through the whole tree from smallest left side to largest right sidde by using the successor method

            int x1 = p.getLocus().getx() + pos.getx() - fig.pos.getx();         //this is the x and y formula given
            int y1 = p.getLocus().gety() + pos.gety() - fig.pos.gety();
            Location key = new Location(x1, y1);        // this location is key

            if(fig.tree.get(fig.tree.getRoot(), key) != null) {     //if the other tree has a node with the key, and returns it as something that is not null,
                // then it intersects meaning it is true
                return true;
            }

            p = tree.successor(tree.getRoot(), p.getLocus());       // goes through the next node using successor method
        }

        return false;       //if i go through the whole tree, then that means it never intersects so it returns flase
    }

}
