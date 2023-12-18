public class Pel {

    private Location p;     //instance variables
    private int color;

    public Pel(Location p, int color) {     //constuctor method assigning the variables

        this.p = p;
        this.color = color;

    }

    public Location getLocus() {        //returns location p
        return p;
    }

    public int getColor() {     //returns color
        return color;
    }


}
