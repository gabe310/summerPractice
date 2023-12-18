package assignment2;

public class WordLL {

    //instance variables
    private Word mysteryWord;
    private LinearNode<Word> history;


    public WordLL(Word mystery) {       //constructor method

        history = new LinearNode<Word>();       //makes empty nodes for history
        mysteryWord = mystery;     //assigns the mystery word to mysteryWord
    }

    public boolean tryWord(Word guess) {

        LinearNode<Word> node = new LinearNode<Word>(guess);        //assigns node to the users guess
        boolean equals = guess.labelWord(mysteryWord);              //see if its true or not as it goes to the labelword method

        node.setNext(history);   //the nodes next is history, so it can point to the next bits of history
        history = node; // history points to node, putting it to the front, and maintains the rest of the list

        return equals;
    }

   public String toString() {
        String list = "";       //creates empty string

        LinearNode<Word> node = history;        //assigns empty node to history

        while (node.getNext() != null) {        //while the next is not null

            list = list + node.getElement().toString() + "\n";          //creates the list of all the users guesses
            node = node.getNext();                                      //goes to the next users guess till empty
        }

        return list;
    }

}
