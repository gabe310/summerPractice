import java.io.BufferedReader;
import java.io.FileReader;

public class test {
    public static void main(String[] args) throws Exception{

        try {
            FileReader file = new FileReader("map0");
            BufferedReader reader = new BufferedReader(file);

            String line = reader.readLine();

            int scale = Integer.parseInt(line);
            line = reader.readLine();

            int start = Integer.parseInt(line);
            line = reader.readLine();

            int end = Integer.parseInt(line);
            line = reader.readLine();

            int width = Integer.parseInt(line);
            line = reader.readLine();

            int length = Integer.parseInt(line);
            line = reader.readLine();

            int privateRoad = Integer.parseInt(line);
            line = reader.readLine();

            int constructionRoad = Integer.parseInt(line);
            line = reader.readLine();

            int first = 0;
            boolean horizontal = true;
            int  current = 0;
            int   inti = 0;


            while (line != null) {

                if (line.charAt(0) == '+') {
                    horizontal = true;
                    inti = 1;

                    while (inti < line.length()) {

                        if (line.charAt(inti) == 'B') {

                        }


                    }

                }
                else {
                    horizontal = false;
                    current = first;
                    inti = 0;


                    //adding edge from current + width


                }



                line = reader.readLine();         //this is at the end to update line
                first = first + width;
            }






        }
        catch(Exception e) {
            throw new Exception("err");
        }
    }
}
