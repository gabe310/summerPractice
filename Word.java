package assignment2;

public class Word {

    private LinearNode<Letter> firstLetter;     //instance variable

    public Word(Letter[] letters) {     //constructor method with array of letters as parameter

        LinearNode<Letter> node;        //creates empty linear node

        for (int i = letters.length - 1; i >= 0; i--) {     //for loop, I need to make a loop so that i can assign firstletter to the first node. cant go through the whole linear node forwards,
            //so i need to make it backwards in order to assign first letter to the first node

            node = new LinearNode<Letter>(letters[i]);      //assigns a letter to the empty nodes

            node.setNext(firstLetter);  //creates firstletter as next to make a head pointer

            firstLetter = node; //assigns firstletter to node, so node is first letter, then when it loops to the next, it gets next node to be next, and then assigns that new node to first letter, so that at the end, the first node technically, will be first letter

            //this basically keeps adding nodes as the head, like a prepend
        }
    }


public String toString() {      //toString method

        String s = "Word: ";      //create empty string

        //then i need to grab each node, and make it into those !node! or +node+

    LinearNode<Letter> first = firstLetter;    //make the first letter node // gotta loop it through out the whole node


    while (first != null) {     //while loop so it goes through every node till its at the end, which will be null

        s = s + first.getElement().toString() + " ";            //creates the strings
        first = first.getNext();            //makes first be the value of the next node
    }

    return s;
}

public boolean labelWord(Word mystery) {    //lets say mystery word is APPLE, and our word is BOOK

        LinearNode<Letter> node = firstLetter;          //node for first letter
        LinearNode<Letter> otherNode = mystery.firstLetter;     //node for the mystery letter
        boolean equals = false;         //set equals to false

        // the rest of the code, basically i need to loop through my word to see if it equals the mystery word
    // if first node == other node, then its correct, else if

    while (node != null) {  //while loop, that loops through the end of the word the user put
         equals = false;            //so that despite 1 node being correct and setting equals to true, theres still a chance the other nodes are false, thus regularly updates it

        if (otherNode != null) {                        //if othernode is not null
            if (node.getElement().equals(otherNode.getElement())) {     //if they equal then sets it to correct and equals is true
                node.getElement().setCorrect();
                equals = true;
            }

            else if (mystery.contains(node)) {      //else if the mystery word contains a certain letter, sets that node to used
                node.getElement().setUsed();
            }

            else {          //else the only option is unused
                node.getElement().setUnused();
            }
        }

        else {  //else statement when mystery word is shorter than my word, it still checks if the remain letters in my word is in the mystery word

            if (mystery.contains(node)) {           //if a letter is in a mystery word then set to used, else its unused
                node.getElement().setUsed();
            }
            else {
                node.getElement().setUnused();
            }
        }

        node = node.getNext();          //updates the node to move to the next letter

        if (otherNode != null) {                // so it doesn't create nullpointer exception
            otherNode = otherNode.getNext();        //makes node of mystery word move to next letter
        }

    }
    return equals;
}


private boolean contains(LinearNode<Letter> letter) {   //private helper method that loops through a word to see if a certain letter is in that word.
        // helper method to help see if a letter is to be used or not used in a word. easier to do, then making node as a character and use the java contains method

        LinearNode<Letter> node = firstLetter;      //node of the firstletter

        while (node != null) {      //goes through the nodes till its done
            if(node.getElement().equals(letter.getElement())) {     //if the elements values are equal return true
                return true;
            }
            node = node.getNext();  // goes to next node
        }

        return false;       //else returns false
}




}
