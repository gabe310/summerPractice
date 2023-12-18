import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Stack;

public class MyMap {
                                //instance variabled
    private int scale;
    private int start;
    private int end;
    private int width;
    private int length;
    private int privateRoad;
    private int constructionRoad;
    private Boolean horizontal;
    private int current;
    private int inti;

    private Stack<Node> stacks;
    private Graph graph;


    public MyMap(String inputFile) throws MapException{     //constructor method

        try {           //reads the inputfile using bufferedreader
            FileReader file = new FileReader(inputFile);
            BufferedReader reader = new BufferedReader(file);

            String line = reader.readLine();

            scale = Integer.parseInt(line);
            line = reader.readLine();

            start = Integer.parseInt(line);
            line = reader.readLine();

            end = Integer.parseInt(line);
            line = reader.readLine();

            width = Integer.parseInt(line);
            line = reader.readLine();

            length = Integer.parseInt(line);
            line = reader.readLine();

            privateRoad = Integer.parseInt(line);
            line = reader.readLine();

            constructionRoad = Integer.parseInt(line);
            line = reader.readLine();

            int first = 0;
            boolean horizontal = true;
            int  current;
            int   inti;

            graph = new Graph(length * width);


            while (line != null) {      //while line is not null, basically going through the map display

                if (line.charAt(0) == '+') {    //if it is horiztonal
                    current = first;
                    inti = 1;

                    while (inti < line.length()) {      //while loop going through the whole characters in the line

                        if (line.charAt(inti) == 'P') {     //if at that position its P, then add edge, does the same for all of them except buildings
                            try {
                                graph.addEdge(graph.getNode(current), graph.getNode(current + 1), "public");
                            } catch (GraphException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (line.charAt(inti) == 'V') {
                            try {
                                graph.addEdge(graph.getNode(current), graph.getNode(current + 1), "private");
                            } catch (GraphException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (line.charAt(inti) == 'C') {
                            try {
                                graph.addEdge(graph.getNode(current), graph.getNode(current + 1), "construction");
                            } catch (GraphException e) {
                                e.printStackTrace();
                            }
                        }

                        inti = inti + 2;
                        current++;
                    }

                }
                else {      //the exact same formula except inti is 0 as it goes through even numbers
                    inti = 0;
                    current = first;

                    while (inti < line.length()) {

                        if (line.charAt(inti) == 'P') {
                            try {
                                graph.addEdge(graph.getNode(current), graph.getNode(current + width), "public");
                            } catch (GraphException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (line.charAt(inti) == 'V') {
                            try {
                                graph.addEdge(graph.getNode(current), graph.getNode(current + width), "private");
                            } catch (GraphException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (line.charAt(inti) == 'C') {
                            try {
                                graph.addEdge(graph.getNode(current), graph.getNode(current + width), "construction");
                            } catch (GraphException e) {
                                e.printStackTrace();
                            }
                        }

                        inti = inti + 2;
                        current++;
                    }

                    first = first + width;      //then since its as vertical its done using this position of first, so it goes through the next line
                }


                line = reader.readLine();         //this is at the end to update line
            }


            stacks = new Stack<>();


        }
        catch(Exception e) {
            e.printStackTrace();
            throw new MapException("error");

        }

    }

    public Graph getGraph() {       //returns graph
        return graph;
    }

    public int getStartingNode() {      //returns start
        return start;
    }

    public int getDestinationNode() {       //returns destination
        return end;
    }

    public int maxPrivateRoads() {  //returns max privateroad
        return privateRoad;
    }

    public int maxConstructionRoads() {     //returns max construction road
        return constructionRoad;
    }


    public Iterator findPath(int start, int destination, int maxPrivate, int maxConstruction) throws GraphException {
// find path handles recursive calls from path, find path is setting up varaibles to use and returns whatever i get from path,
// so from this i check if path is null or not. in path function should pass start, desitination, private, construction.
//look at all incidents on start and look at the edge type, if its construction pass it over the others and decrease contruction road, cause we want it to be greater than 0
// we want public roads over private and construction
//in find path, for recursive call, is returns true that means there is a stack

        if (path(start,destination,maxPrivate,maxConstruction)) {
            return stacks.iterator();
        }
        else {
            return null;
        }
    }


    private boolean path (int start, int destination, int maxPrivate, int maxConstruction) throws GraphException {      //private method


        Node current = graph.getNode(start);
        Node last = graph.getNode(destination);

        current.markNode(true);
        stacks.push(current);

        if (current == last) {
            return true;
        }

        Iterator edges = graph.incidentEdges(current);

        while (edges.hasNext()) {

            Node u = (Node) edges;

            if (u.getMark() == false) {
                if (path(start, end, privateRoad, constructionRoad) == true) {
                    return true;
                }
            }
            edges.next();
        }
        stacks.pop();
        return false;

    }
}
