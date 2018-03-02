import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;

/*
    Project: Predictive Analytics for Re-order of data
    Made By: Garrett VanBuskirk
    StudentID: 
    Purpose: Capstone Project for WalMart
    Authorization: 
*/


public class PredictiveAnalytics {

    public static void testPrint(String[] items, int[] lowerBounds, int[] upperBounds){
        for(int i = 0; i < items.length; i++){
            System.out.println("For the item: " + items[i] + " the bounds found were...\nLower: " +
                    lowerBounds[i] + "      Upper: " + upperBounds[i] + "\n");
        }
    }

    public static double accuracy(int[] lowerBounds, int[] upperBounds, int[] a, String[] items){
        double accuracy = 0; //May change to hold double[] and return accuracy array
        /*

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
        Arrays.sort(a);
        if(a.length % 2 == 0){
            int median = (a[a.length/2] + a[a.length/2 - 1])/2;
        }
        else{
            int median = a[a.length/2];
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

        */
        return accuracy;
    }

    //Calculate the Lower Boundary for an Item
    public static int lowerBound(int[] a){ //TODO: Algorithm used to calculate lower boundary
        int lower = 0;

        //Only want the lower 60%.
        Arrays.sort(a);
        double SixtyPercentOfSize = (a.length*.6);
        int newSize = (int) SixtyPercentOfSize;

        //Mean calculation variables
        int total = 0;
        int[] newA = new int[newSize];

        //Loop through data
        for(int i = 0; i < newSize; i++){
            //Mean calculations
            total+= a[i];

            newA[i] = a[i];

        }

        //Mean calculation
        double mean = total/newSize;

        //Median calculation
        double median = 0;
        if(newA.length % 2 == 0){
             median = (newA[newA.length/2] + newA[newA.length/2 - 1])/2;
        }
        else{
             median = newA[newA.length/2];
        }

        //Weigh Median and Mean Evenly, use to calculate
        double PreExponential = (mean + median)/2;

        //TODO: Check For Exponential Growth
        //lower = ExponentialGrowth(PreExponential);

        lower = (int) PreExponential;

        //Add-ons:
        //Including Accuracy of prediction

        return lower;
    }

    //Calculate the Upper Boundary for an Item
    public static int upperBound(int[] a){ //TODO: Algorithm used to calculate upper boundary
        int upper = 0;

        //Only want the upper 60%.
        Arrays.sort(a);
        double SixtyPercentOfSize = (a.length*.6);
        int newSize = (int) SixtyPercentOfSize;

        int total = 0;
        int[] newA = new int[newSize];

        //Loop through data
        for(int i = a.length - newSize; i < a.length; i++){
            //Mean calculations
            total+= a[i];
            newA[i-(a.length-newSize)] = a[i];
        }

        //Mean calculation
        double mean = (double) total/newSize;

        //Median calculation
        double median = 0;
        if(newA.length % 2 == 0){
            median = (newA[newA.length/2] + newA[newA.length/2 - 1])/2;
        }
        else{
            median = newA[newA.length/2];
        }

        //Weigh Median and Mean Evenly, use to calculate
        double PreExponential = (mean + median)/2;

        //TODO Check For Exponential Growth
        //upper = ExponentialGrowth(PreExponential);

        upper = (int) PreExponential;

        //Add-ons:
        //Including Accuracy of prediction

        return upper;
    }


    public static void main (String[] args) throws Exception{//TODO: Transfer some of this shit to methods
        //Scanner for importing data
        //Scanner scan = new Scanner(System.in); //Shouldn't need this
        Scanner scan = new Scanner(new File("FakeData.csv")); //May need to add specific directory

        //Data is sent via CSV made from Excel Spreadsheet
        //Until I can see their data, this is what I'm writing it for

        //----------------------------------------------------------------------------------------
        //INPUTS:

        //Use Scanner to read in each line of data to an ArrayList
        //Initial Capacity set to 10 on default, can increase/decrease if needed
        ArrayList<String> Lines = new ArrayList();
        int count = 0;
        while(scan.hasNextLine()){
            //If first line, toss for now (dates in my fake data)
            if(count == 0) {
                count++;
                scan.nextLine();
                continue;
            }
            else{
              Lines.add(scan.nextLine());
            }
        }

        //----------------------------------------------------------------------------------------
        //DATA SORTING AND PARSING

        //Create array of how many items we have based off size inputted
        String[] items = new String[Lines.size()];
        int[][] itemData = null;
        String[] tempLine = null;

        //To initialize array
        tempLine = Lines.get(0).split("\\s*,\\s*");
        itemData = new int[items.length][tempLine.length-1];

        for(int i = 0; i < items.length; i++){ //This is equal to number of lines

            tempLine = Lines.get(i).split("\\s*,\\s*"); //Removes trailing and leading whitespace

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
                itemData[i][j-1] = Integer.parseInt(tempLine[j]);
            }
        }

        //Data should be stored and sorted at this point

        //----------------------------------------------------------------------------------------
        //LOWER AND UPPER BOUND GENERATING (ACCURACY GENERATION)

        //Call the lower bound generator and upper bound generator
        //Create an array to store lower bound and upper bound values returned
        int[] lowerBounds = new int[items.length];
        int[] upperBounds = new int[items.length];

        for(int i = 0; i < items.length; i++){ //Should loop according to number of items
            //Store lower and upper bounds according to number of items we have
            lowerBounds[i] = lowerBound(itemData[i]);
            upperBounds[i] = upperBound(itemData[i]);
        }
        //Upper and Lower bounds made

        //Test Print to see similarities
        testPrint(items, lowerBounds, upperBounds);

        //Accuracy array to store accuracy for each item
        double[] accuracies = new double[items.length];

        //Call Accuracy Function for the list of bounds
        for(int i = 0; i < itemData.length; i++){ //Should loop according to number of items
            accuracies[i] = accuracy(lowerBounds, upperBounds, itemData[i], items);
        }


    }

}
