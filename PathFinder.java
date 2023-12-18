package assignment3;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PathFinder {

    private Map pyramidMap;     //instance variable

    public PathFinder(String fileName) {
    //try catch method, if theres an invalid character in the map name or input/output error
        try {
            pyramidMap = new Map(fileName);
        } catch (InvalidMapCharacterException exception) {
            System.out.println("invalid character.");
        }
        catch (IOException e) {
            System.out.println("input/output error.");
        }


    }

    public DLStack path() {

        DLStack<Chamber> chambers = new DLStack<>();            //creates an empty chamber stack
        Chamber firstChamber = pyramidMap.getEntrance();        // gets first chamber which is the entrance
        int numTreasures = pyramidMap.getNumTreasures();        // the number of treasures in the map

        Chamber C;      //current chamber
        int foundTreasure = 0;      //number of treasures found, increments till found is == to numTreasure

        chambers.push(firstChamber);        //pushes first chamber to stack
        firstChamber.markPushed();          //then marks it as pushed

        while (!chambers.isEmpty()) {   //while chambers not empty

            Chamber currentChamber = chambers.peek();       //sets current chamber as the top

            if (currentChamber.isTreasure() && foundTreasure == numTreasures) {     //if it finds the last treasure loop finishes
                break;
            }

            C = bestChamber(currentChamber);       //C is being assigned to currents best possible chamber from indexs that surround the current chamber,

            if (C != null) {        //if not null then pushes to stack and marks it
                chambers.push(C);
                C.markPushed();

                if (C.isTreasure()) {       //if is it a treasure then increment whats found
                    foundTreasure++;
                }
            }
            else {
                C = chambers.pop();     //else C is popped from chambers, and so it is now marked as popped
                C.markPopped();
            }

        }

        return chambers;        //returns chambers
    }

    public Map getMap() {       //returns map
        return pyramidMap;
    }

    public boolean isDim(Chamber currentChamber) {

        if (currentChamber != null && !currentChamber.isSealed() && !currentChamber.isLighted()) {  //if not null, not sealed, not lighted

                for (int i = 0; i < 6; i++) {           //for loop checking each sides if neighbor is lighted
                    if(currentChamber.getNeighbour(i).isLighted()) {
                        return true;
                    }
                }
        }
            return false;
    }

    public Chamber bestChamber(Chamber currentChamber) {

        int bestIndex = -1; // looks for best index, set to -1 if none are best
        boolean lighted = false;        //boolean used to see if there is already a lighted chamber

        for (int i = 0; i < 6; i++) {   //for loop going through the 6 indexes

            if (!currentChamber.getNeighbour(i).isMarked() && !currentChamber.getNeighbour(i).isEntrance()) {   //if not marked and is not entrance

                if (currentChamber.getNeighbour(i).isTreasure()) {  // if the chamber is treasure, then it automatically returns that chamber
                   return currentChamber.getNeighbour(i);
                }
                else if (currentChamber.getNeighbour(i).isLighted()) {        //else if there is a lighted one, then it sets index to that, and lighted to true
                    bestIndex = i;
                    lighted = true;
                }
                else if (isDim(currentChamber.getNeighbour(i)) && !lighted) {   //else if chamber is dim and there not already a lighted one found, then its best index
                    bestIndex = i;
                }
            }
        }

        if (bestIndex == -1) {      //if all the chambers around are marked, not lighted, not dim, then returns null
            return null;
        }

        else {
            return currentChamber.getNeighbour(bestIndex);  //returns best chamber
        }
    }
}
