package assignment1;

import java.util.Random; //imports the random class

public class Tile {

    private char value;  //instance variable

    public Tile() {     //constructor method
        value = ' ';        //assigns blank to value
    }

    public Tile(char value){        //overloading method with char value parameter
        this.value = value;         //assigns value to value
    }

    public void pickup() {      //pick up method
      //random character from a to z (java.util.random), then value = random character
        Random random = new Random();   //creates a new random method
        this.value = (char) (random.nextInt(26) + 'A');     //randomizes value through all the 26 characters in alphabet

    }

    public char getValue() {        //get value method
        return value;       //returns a letter
    }

}
