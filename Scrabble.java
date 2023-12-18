package assignment1;

//import all the classes I used
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.*;
import java.util.HashMap;

public class Scrabble {

    private Tile[] tiles;       //instance variable with type Tile array called tiles


    public Scrabble (){     //constructor method
        tiles = new Tile[7];            //creates tile type, and makes 7 tiles in the array

        for (int i = 0; i < 7; i++) {       //for loop assigning each of the 7 tiles
            tiles[i] = new Tile();          //for each element in the tiles array it creates new tile
            tiles[i].pickup();              //then pickup a random letter to assign
        }

    }

    public Scrabble(Tile[] tiles) {     //overloading method, when user inputs their own values for tiles
        this.tiles = tiles;             //assigns tiles to tiles
    }


    public String getLetters(){     //getLetters method
        String str = "";        //create an empty string
        for (int i = 0; i < tiles.length; i++) {    //for loop that adds each letter to each other, creating a whole string of letters ex. ABCDEF
         str = str + tiles[i].getValue();   //adds the tile value to each other
        }
        return str; //returns the string variable
    }


   public ArrayList<String> getWords(){     //getWords method
   //what i need to create is an array, in which each element is a word that can be made using the 7 letters,

       ArrayList<String> words = new ArrayList<>();     //creates an arraylist of strings
       List<String> collinList = new ArrayList<>();     //creates a list of strings, so i can use the .add() method to add list of strings
       String s = "";   //empty string

       try {    //uses the try and catch method for files, and throws a file not found exception
           File source = new File("CollinsScrabbleWords2019.txt");      //creates a new file with the file name
           Scanner reader = new Scanner(source);        //creates a scanner that will read the file
           while (reader.hasNext()) {           //while loop to see if theres a next word in the txt file
               s = reader.next();       //finds the next string
               collinList.add(s);       //adds that string word into the list
           }
           reader.close();      //closes the scanner
       }
       catch (FileNotFoundException e) {        //catches the file exception
           System.out.println("file not found");        //gives an error message

       }

       for (int i = 0; i < collinList.size(); i++) {    //for loop that loops through each word in the list

           char[] letter = this.getLetters().toCharArray();     //creates a char array of the 7 random letters
           int counter = 0;         //create a counter that will be used to count the same letters of the 7 tiles and the given word
           String s1 = collinList.get(i);       //string that contains the given word of the list
           char[] cWord = s1.toCharArray();     //uses that string to put into a char array
           Arrays.sort(letter);         //sorts the letter from a to z
           Arrays.sort(cWord);          //sorts given word from a to z
           Boolean[] usedTile = new Boolean[tiles.length];      //creates boolean array with size of the tiles, checks to see if letter is used or not
           Arrays.fill(usedTile, false);      //assigns all of them to false
            Boolean[] usedWord = new Boolean[cWord.length];        //creates boolean array with size of the given word
            Arrays.fill(usedWord, false);   //assigns all to false


           if (collinList.get(i).length() >= 1 && collinList.get(i).length() <= tiles.length) {     //if the length of the given word is more than or equal to 1, and less than or equal to 7
               //check each letter in the word, if a letter is in the word then add to counter, if counter == word length then add it to list
               for (int j = 0; j < cWord.length; j++) { //for loop going through the length of the given word

                       for (int k = 0; k < letter.length; k++) {        //for loop going through the length of the letters
                           if (letter[k] == cWord[j] && !usedTile[k] && !usedWord[j]) {  //if each of the letters in the tile equals
                               //each of the letters in the word, and if the used letter in the tile and used letter in the word is false then goes through
                               counter++;   //adds the counter of what letter equals
                               usedTile[k] = true;        //assigns the tile letter to true, meaning it is now used
                               usedWord[j] = true;          //same thing but for word letter

                           }
                       }
                       
                       
                   if(counter == cWord.length ) {       //if the counter of the tile letters and word letters equaling is the same as the length of the word, then thats the word that can be made using the letters
                       words.add(collinList.get(i));        //adds the possible word from the list into the words arraylist, meaning thats whats possible to make
                       counter = 0;             //resets the counter to 0 so it can loop to find next possible word
                       Arrays.fill(usedTile, false);        //same thing, resetting the usedTile and usedWord to find next possible word
                       Arrays.fill(usedWord, false);
                   }
               }


           }
           //got to compare each word with the 7 tiles to see if it spells a word,
           //so if 1 to 7 tiles == each element in the file then add it to the arraylist words??
           
       }
       return words;    //returns words
    }


    public int[] getScores() {      //getScores method

        HashMap<String, Integer> scoreTiles = new HashMap<>();  //creates a hashmap variable

        //creates all the scores of each of the letters
        scoreTiles.put("A", 1);
        scoreTiles.put("B", 3);
        scoreTiles.put("C", 3);
        scoreTiles.put("D", 2);
        scoreTiles.put("E", 1);
        scoreTiles.put("F", 4);
        scoreTiles.put("G", 2);
        scoreTiles.put("H", 4);
        scoreTiles.put("I", 1);
        scoreTiles.put("J", 8);
        scoreTiles.put("K", 5);
        scoreTiles.put("L", 1);
        scoreTiles.put("M", 3);
        scoreTiles.put("N", 1);
        scoreTiles.put("O", 1);
        scoreTiles.put("P", 3);
        scoreTiles.put("Q", 10);
        scoreTiles.put("R", 1);
        scoreTiles.put("S", 1);
        scoreTiles.put("T", 1);
        scoreTiles.put("U", 1);
        scoreTiles.put("V", 4);
        scoreTiles.put("W", 4);
        scoreTiles.put("X", 8);
        scoreTiles.put("Y", 4);
        scoreTiles.put("Z", 10);


       List<String> wordList = getWords();      //creates a list of strings, with all the possible words that the tiles can make
        int[] score = new int[wordList.size()];     //creates a score with length of the possible words size


        for (int i = 0; i < wordList.size(); i++) {  //goes through each possible word

            String s1 = wordList.get(i);        //assigns the given word to a string
            char[] word = s1.toCharArray();     //puts that string in a char array called word

            for (int j = 0; j < word.length; j++) {     //for loop that goes through the letters of the word

                //simplified if statements as these words equal the same values, and is repeated
                if (word[j] == 'A' || word[j] == 'E' || word[j] == 'I' || word[j] == 'L' || word[j] == 'N' || word[j] =='U' || word[j] == 'O' || word[j] == 'S' || word[j] == 'T') {
                    score[i] = score[i] + scoreTiles.get("A");
                }

                if(word[j] == 'D' || word[j] == 'G') {
                    score[i] = score[i] + scoreTiles.get("D");
                }

                if(word[j] == 'M' || word[j] == 'P' || word[j] == 'B' || word[j] == 'C') {
                    score[i] = score[i] + scoreTiles.get("M");
                }

                if(word[j] == 'F' || word[j] == 'H' || word[j] == 'V' || word[j] == 'W' || word[j] == 'Y') {
                    score[i] = score[i] + scoreTiles.get("F");
                }

                if(word[j] == 'K') {
                    score[i] = score[i] + scoreTiles.get("K");
                }

                if(word[j] == 'J' || word[j] == 'X') {
                    score[i] = score[i] + scoreTiles.get("J");
                }

                if(word[j] == 'Q' || word[j] == 'Z') {
                    score[i] = score[i] + scoreTiles.get("Q");
                }
            }

        }
//use the getwords and find the score value of the them,
//what i need to do is get value of each letters.
//i use the hashmap method

        Arrays.sort(score);     //sorts the array of the score, from least to greatest

        return score;       // returns the array of scores
    }


   public boolean equals(Scrabble other) {          //equal method with other scrabble parameter

       char[] tile1 = this.getLetters().toCharArray();      //gets char array of the first tile set
       char[] tile2 = other.getLetters().toCharArray();     //gets char array of the other tile set
       Arrays.sort(tile1);      //sorts them alphabetically
       Arrays.sort(tile2);

       if (Arrays.equals(tile1, tile2)) {       //if the arrays are equal return true
           return true;
       }
       else {
           return false;        //else false
       }

   }


}

