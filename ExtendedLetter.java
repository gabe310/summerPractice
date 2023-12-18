package assignment2;

public class ExtendedLetter extends Letter {

    //instance variabled
        private String content;
        private int family;
        private boolean related;
        private final int SINGLETON = -1;

        public ExtendedLetter(String s) {       //constructor method
            super('c');

            content = s;
            related = false;
            family = SINGLETON;
        }

        public ExtendedLetter(String s, int fam) {      //overloading constructor method, with different parameter
            super('c');
            content = s;
            related = false;
            family = fam;
        }

        public boolean equals(Object other) {       //overload equals method

            if(!(other instanceof ExtendedLetter)) {        //does the same as the other for if its not an instance of this class
                return false;
            }

            else {      //otherwise sees if the node is part of a family (same code number)

                if (((ExtendedLetter) other).family == this.family) {
                    related = true;
                }

               if(((ExtendedLetter) other).content.equals(this.content)) {      //if the values are equal then returns true
                    return true;
                }

              return false;     //otherwise it will be false
            }
        }



        public String toString() {      //toString that overrides the toString in letter class

            String s = "";      //creates empty string

            if (this.isUnused() && related == true) {       //if the node is unused but its related, then creates a special decorator
                s = "." + content + ".";
            }

            else {
                s = decorator() + content + decorator();        //else uses the Letter class tostring
            }

            return s;
        }

        public static Letter[] fromStrings (String[] content, int[] codes) {

            Letter[] letters = new Letter[content.length];  //creates array of letters with length of the given content length

            if (codes == null) {        //if there isnt a code array then creates a regular array of letters

                for (int i = 0; i < content.length; i++) {
                    ExtendedLetter ELetters = new ExtendedLetter(content[i]);
                    letters[i] = ELetters;
                }
            }

            else {      //else creates array of letters with the values, and the codes of the elements it will be assigned to
                for (int i = 0; i < content.length; i++) {
                    ExtendedLetter ELetters = new ExtendedLetter(content[i], codes[i]);
                    letters[i] = ELetters;
                }
            }

            return letters; //returns letters
        }



}
