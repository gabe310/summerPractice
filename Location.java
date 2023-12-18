public class Location {

    private int x;      //instance variables
    private int y;

    public Location (int x, int y) {        //constructor method
        this.x = x;
        this.y = y;
    }


    public int getx() {     //get method that returns x
        return x;
    }

    public int gety() {     //get method that returns y
        return y;
    }

    public int compareTo (Location p) {     //compare method that compares the x's and y's on if it left or right side or if they are equal

        if (this.gety() > p.gety() || (this.gety() == p.gety() && this.getx()  > p.getx())) {
            return 1;
        }

        else if (this.getx() == p.getx() && this.gety() == p.gety()) {
            return 0;
        }

        else if (this.gety() < p.gety() || (this.gety() == p.gety() && this.getx() < p.getx())) {
            return -1;
        }

        else {
            return -1111;
        }
    }


}
