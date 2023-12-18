package assignment2;

public class Letter {

    //create instance variables
    private char letter;
    private int label;
    private final int UNSET = 0;
    private final int UNUSED = -1;
    private final int USED = 1;
    private final int CORRECT = 2;


    public Letter(char c) {     //constructor method

        label = UNSET;      //makes label unset
        letter = c;         //letter will be the given char the user puts
    }

    public boolean equals(Object otherObject) {     //equals method

        if (!(otherObject instanceof Letter)) {     //if the other object is not an instance of the letter class, it automatically returns false

            return false;
        }

        else if (((Letter) otherObject).letter == this.letter) {    //else if other's letter and this letter is equal, return true
            return true;
        }

        else {              //otherwise returns false
            return false;
        }
    }

    public String decorator() {     //decorator method returning string for the letters such as !A!, or -A-

        if (label == USED) {        //if label == used, returns the decorator for the label and so on forr the others
            return "+";
        }
        else if (label == UNUSED) {
            return "-";
        }
        else if (label == CORRECT) {
            return "!";
        }
        else {
            return " ";
        }
    }

    public String toString() {      //to string that creates the letters to be !A! or -A-

        return this.decorator() + this.letter + this.decorator();
    }

    //setter methods for the labels
    public void setUnused() {
        label = UNUSED;
    }

    public void setUsed() {
        label = USED;
    }

    public void setCorrect() {
        label = CORRECT;
    }

    public boolean isUnused() {     //if label is unused return true, else false

        if (label == UNUSED) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Letter[] fromString(String s) {       //fromString, with an array of letters

        Letter[] letters = new Letter[s.length()];      //creates array of letters with the length of a string

        for (int i = 0; i < s.length(); i++) {          //for loop in which adds each character of string s, into the elements of the array of letters

            letters[i] = new Letter(s.charAt(i));
        }

        return letters; //returns the array of letters full of characters
    }
}
