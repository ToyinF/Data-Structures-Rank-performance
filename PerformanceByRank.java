import java.io.FileNotFoundException;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import lib280.list.LinkedList280;

public class PerformanceByRank {

    public static LinkedList280<Crew> readCrewData(String path) {
        Scanner infile = null;

        try {
            infile = new Scanner(new File(path));
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        }

        // Initialize output list.
        LinkedList280<Crew> pirateCrew = new LinkedList280<Crew>();

        // While there is more stuff to read...
        while(infile.hasNext()) {
            // Read the three values for a Crew record
            int rank = infile.nextInt();
            double pay = infile.nextDouble();
            int sacks = infile.nextInt();

            // Create a crew object from the data
            Crew c = new Crew(rank, pay, sacks);

            // Place the new Crew instance in the linked list.
            pirateCrew.insertFirst(c);
        }

        // Close the input file like a good citizen. :)
        infile.close();

        // Return the list of Crew objects.
        return pirateCrew;
    }


    public static void main( String args[] ) {
        // Read the data for Jack's pirate crew.
        // If you are getting a "File Not Found" error here, you may adjust the
        // path to piratecrew.txt as needed.
        LinkedList280<Crew> pirateCrew = readCrewData("C:\\Users\\User\\Desktop\\Computer\\CMPT 280\\Assignments\\A1_Question1\\src\\piratecrew.txt");

        /* an array piratesByRank of ten LinkedList280<Crew> objects, one for each rank */
        LinkedList280 < Crew> piratesByRank [] = new LinkedList280 [10];
        // initializing the elements of the list before adding to it
        for (int i = 0; i < 10; i++)
            if (piratesByRank[i] == null) piratesByRank[i] = new LinkedList280<>();

        // iterating and adding each Crew object to the list of piratesByRank using the ranks
        pirateCrew.goFirst();   //cursor setting
        while (pirateCrew.itemExists()){
            Crew temp = pirateCrew.item();
            piratesByRank[temp.rank].insert(temp);
            pirateCrew.goForth();   //cursor iteration
        }

        //String outputs declared
        String a = "Jack's rank ";
        String b = " crew members were paid of ";
        String c = " guineas per sack of grain plundered. ";
        String d = "Jack has no pirates employed at this rank";

        /* For loop to analyze the crew and sum the total pay and total plunder. Then get the average pay per plunder */
        for (int i = 0; i< piratesByRank.length; i++){
            double pay = 0;
            int plunder = 0;

            if (piratesByRank[i].isEmpty()) System.out.println(d);
            else {
                piratesByRank[i].goFirst(); //set the cursor to the first item in list
                while ( piratesByRank[i].itemExists()){
                    pay = pay + piratesByRank[i].item().getPay();   //get the pay for each crew member and add it up
                    plunder = plunder + piratesByRank[i].item().getGrainSacks();    //add the corresponding plunder
                    piratesByRank[i].goForth(); //iterate the cursor
                }
                //Finding the average for eah crew
                double avg = pay / plunder;
                //Print to console
                System.out.println(a + i + b + avg + c);
            }
        }
    }
}