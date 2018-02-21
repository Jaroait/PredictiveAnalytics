import java.util.Scanner;
import java.util.Arrays;
import java.io.File;

/*
    Project: Predictive Analytics for Re-order of data
    Made By: Garrett VanBuskirk
    StudentID: 010691035
    Purpose: Capstone Project for WalMart
*/


public class PredictiveAnalytics {

    public void testPrint(String[] items, int[] lowerBounds, int[] upperBounds){
        /* // <---- DELETE /* TO SEE THE LINES OF CODE
        for(int i = 0; i < items.length; i++){
            System.out.println("For the item: " + items[i] + " the bounds found were...\nLower: " +
                    lowerBounds[i] + "      Upper: " + upperBounds[i] + "\n");
        }
        */ // <---- DELETE */ TO SEE THE LINES OF CODE
    }

    public int Accuracy(int[] lowerBounds, int[] upperBounds, int[] a, String[] items){
        int accuracy = 0; //May change to hold double[] and return accuracy array
        /* // <---- DELETE /* TO SEE THE LINES OF CODE

        //stdev
        //calculate zscore and gain % accuracy according to location of the mean/median
        //Gain the assumed accuracy % based off the differences between mean/median calculation

        //----------------------------------------------------------------------------------------
        //Calculate Mean
        int total = 0;
        for(int i = 0; i < a.length; i++){
            total += a[i];
        }
        //Rough estimate, rounded to an integer for now.
        int mean = total/a.length;

        //----------------------------------------------------------------------------------------
        //Calculate Median
        int[] sorteda = Arrays.sort(a);
        if(a.length % 2 == 0){
            int median = (sorteda[sorteda.length/2] + sorteda[sorteda.length/2 - 1])/2;
        }
        else{
            int median = sorteda[sorteda.length/2];
        }
        //Rough Estimate, rounded to an integer for now.

        //----------------------------------------------------------------------------------------
        //TODO: Accuracy Calculations -- Math that I don't feel like doing right now
        //Assume Normal Distribution to be able to use Z-Score
        //Compare Median/Mean to get the more accurate result (larger one = better due to increase of demand)
        //Weigh the Median/Mean to % of 1 to get multiplicative needs
        //EX: Median = .4, Mean = .6 => (Mean*.6) + (Median*.4) => New "Normal distribution"
        //May need to implement Z score table to calculate % acc


        //----------------------------------------------------------------------------------------
        //Calculate Z Score
        //Take the number you wish to calculate for
        //Subtract the Mean
        //Divide by the STDEV
        //Equation: (X - Mean)/stdev

        //----------------------------------------------------------------------------------------
        //Calculating Stdev

        //Variance variable used to calculate stdev
        double variance = 0;

        //Loop through the array using mean.
        for(int i = 0; i < a.length; i++){
            variance += (a[i]-//MEAN) * (a[i]-//MEAN);
        }
        double stdev = Math.sqrt(variance);

        //----------------------------------------------------------------------------------------
        //Loop of items *I DONT THINK THIS IS NEEDED*
        for(int i = 0; i < items.length; i++){
            //calculate lower bound stdev zscore and % acc
            //calculate upper bound stdev zscore and % acc

        }

        */ // <---- DELETE */ TO SEE THE LINES OF CODE
        return accuracy;
    }

    //Calculate the Lower Boundary for an Item
    public int lowerBound(int[] a){ //TODO: Algorithm used to calculate lower boundary
        int lower = 0;
        /* // <---- DELETE /* TO SEE THE LINES OF CODE
        //Loop through data
        for(int i = 0; i < a.length; i++){
            //Calculate lower bounds using algorithm yet to finish
        }

        //Add-ons:
        //Including Accuracy of prediction

        */ // <---- DELETE */ TO SEE THE LINES OF CODE
        return lower;
    }

    //Calculate the Upper Boundary for an Item
    public int upperBound(int[] a){ //TODO: Algorithm used to calculate upper boundary
        int upper = 0;
        /* // <---- DELETE /* TO SEE THE LINES OF CODE
        //Loop through data
        for(int i = 0; i < a.length; i++){
            //Calculate upper bounds using algorithm yet to finish
        }

        //Add-ons:
        //Including Accuracy of prediction


        */ // <---- DELETE */ TO SEE THE LINES OF CODE
        return upper;
    }


    public Main (String[] args) throws Exception{//TODO: Transfer some of this shit to methods
       /* // <---- DELETE /* TO SEE THE LINES OF CODE
        //Scanner for importing data
        Scanner scan = new Scanner(System.in); //Shouldn't need this
        Scanner scan = new Scanner(new File("FakeData.txt")); //May need to add specific directory

        //Data is sent via CSV made from Excel Spreadsheet
        //Until I can see their data, this is what I'm writing it for


        //----------------------------------------------------------------------------------------
        //INPUTS:

        //Use Scanner to read in each line of data to an ArrayList
        //Initial Capacity set to 10 on default, can increase/decrease if needed
        ArrayList<String> Lines = new ArrayList();
        while(scan.hasNextLine()){
            //Scan all lines one by one
            //For each line other than first
            //First line indicates the "date" which won't be necessary

            //If first line, toss for now
            if(count == 0) {
                count++;
                continue;
            }

            //All other lines read and store within an array of lines
            //At this moment, I simply wrote it was "12" things of data to use to calculate a new piece
            //This would be represented as 12 years of data for a specific week of reorder.
            //Repeat for each week, restructure the inputs for multi-solve data (multiple weeks needed to solve)

            //Read in the first "real" line of data
            //Ex: Paper,5,2,1,4,5,6,2,3,4,...
            Lines.add(scan.nextLine());

            //Possible Add-ons
            //IDK atm

        }

        //----------------------------------------------------------------------------------------
        //DATA SORTING AND PARSING

        //Create array of how many items we have based off size inputted
        String[] items = new String[Lines.size];

        //Loop through items, storing the first value in string array for Printing purposes
        //Storing the data separately for ease of use
        for(int i = 0; i < items.length; i++){ //This is equal to number of lines
            //Delimit Values based off separation
            //If data is simply separated by a comma
            //Ex: Paper,1,2,3,4,5,...
            String[] tempLine = Lines[i].split(",");
            //If data is separated by a comma and space
            //Ex: Paper, 1, 2, 3, 4, 5,...
            String[] tempLine = Lines[i].split("\\s*,\\s*"); //Removes trailing and leading whitespace

            //Create array for each item to hold numbers only
            int[][] itemData = new int[items.length][tempLine.length-1];
            //This may need to be moved outside of loop like [items] ^^^

            //Loop through how many items are within this, my Example data will be 13.
            for(int j = 0; j < tempLine.length; j++){

                //Take name of item to store separately for print reasons
                if(j == 0){
                    items[i] = tempLine[j];
                    continue;
                }

                //All other pieces will be values of data (12 in my example)
                //Store data within separate array for ease of use.
                //This will parse the integer removing any additional whitespace if applicable
                itemData[i][j] = Integer.parseInt(tempLine[j]);

            }
        }

        //Data should be stored and sorted at this point

        //----------------------------------------------------------------------------------------
        //LOWER AND UPPER BOUND GENERATING (ACCURACY GENERATION)

        //Call the lower bound generator and upper bound generator
        //Create an array to store lower bound and upper bound values returned
        int[] lowerBounds = new int[items.length];
        int[] upperBounds = new int[items.length];

        for(int i = 0; i < itemData.length; i++){ //Should loop according to number of items
            //Store lower and upper bounds according to number of items we have
            lowerBounds[i] = lowerBound(itemData[i]);
            upperBounds[i] = upperBound(itemData[i]);
        }
        //Upper and Lower bounds made

        //Test Print to see similarities
        testPrint(items, lowerBounds, upperBounds);

        //In theory, only accept boundary of data with over 80% accuracy
        //If below 80% accuracy, toss Max/Min data in hopes of removing outliers and re-run

        //Accuracy array to store accuracy for each item
        int[] accuracies = new int[items.length];

        //Call Accuracy Function for the list of bounds
        for(int i = 0; i < itemData.length; i++){ //Should loop according to number of items
            accuracies[i] = accuracy(lowerBounds, upperBounds, itemData[i], items);
        }

        //Reprint/re-execute based off accuracy of each requirement
        //This will need to be able to infinitely loop in order to keep going until an accurate boundary is found
        //Issues revolving this would be rewriting code previously designed as well as what is the "limit"
        //Limit referring to how many times will I continuously redesign the algorithm until it gives up
        //Ultimately resulting in a needed loop of only "1" that also corresponds to lowering accuracy requirement

        //Do I want to call the re-calculate within the accuracy or after???


        //----------------------------------------------------------------------------------------
        //TODO: Future Stuff
        //Pass data of bounds to separate class that is the upper level
        //This class would be accessing the database and RFID information
        //This class would be accessing the Script of re-ordering to determine accuracy of re-order
        //In this class, if the Script is ordering too much or too little, send flag to "manager"...
        //to do a manual order or to authorize the Script order
        //Write Unit Tests for predictive analytics, --Lots of time spent faking data to test accuracy
        // ^^^^ This will test to see how accurate my algorithm is as a whole rather than each number.


        //----------------------------------------------------------------------------------------
        //TODO: Issues and Possible Solutions
        //Can't do Maven, therefore cant do mockito
        //Need to redesign Unit Tests to fit the ability to write them without Maven/Mockito
        //I wont be able to do this for quite some time, rethink automated testing
        //F...


        */ // <---- DELETE */ TO SEE THE LINES OF CODE

    }

}
