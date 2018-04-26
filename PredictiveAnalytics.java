import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

/*
    Project: Predictive Analytics for Re-order of data
    Made By: Garrett VanBuskirk
    StudentID: 010691035
    Purpose: Capstone Project for WalMart
*/


public class PredictiveAnalytics {
	
	int[] lowerBounds;
	int[] upperBounds;
	String[] items;
	boolean foundGrowth = false;
	
	PredictiveAnalytics(){
		//Immediately run it on creation
		start();
	}
        
    private double ExponentialGrowth(int[] a, int lowerUpper){ //0 = lower, 1 = upper
        //Original array
        int[] temp = new int[a.length];
        for(int i = 0; i < a.length; i++){
          temp[i] = a[i];
        }
        
        Arrays.sort(temp);
        
        //Count for correct positions
        int count = 0;
        
        //Store positions for double check if overall not growth.
        boolean[] positionCheck = new boolean[a.length];
        
        //Calculate
        for(int i = 0; i < a.length; i++){
          if(a[i] == temp[i]){
            positionCheck[i] = true;
            count++;
          }
          else{
            positionCheck[i] = false;
          }
        }
          
        //First check of overall growth
        if((double) count/a.length >= .5){
          return (double) (((double)count/a.length)/2)+1;
        }
        else{
          count = 0;
          double SixtyPercentOfSize = (a.length*.6);
          int newSize = (int) SixtyPercentOfSize;
          if(lowerUpper == 0){
            for(int i = 0; i < newSize; i++){
              if(positionCheck[i]){
                count++;
              }
            }
            if((double) count/newSize >= .5){
              return (double) (((double)count/a.length)/2)+1;
            }
            else{
              //No growth detected on lower bound either
              return (double) 1;
            }
          }
          else if(lowerUpper == 1){
            for(int i = a.length - newSize; i < a.length; i++){
              if(positionCheck[i]){
                count++;
              }
            }
            if((double) count/newSize >= .5){
              return (double) (((double)count/a.length)/2)+1;
            }
            else{
              //No growth detected on upper bound either
              return (double) 1;
            }
          }
          return (double) 1; //Never executed unless bad parameters.
        }
    }

    //Calculate the Lower Boundary for an Item
    private int lowerBound(int[] a){ //TODO: Algorithm used to calculate lower boundary
        int lower = 0;

        //Calculate for exponential growth
        double ExponentialGrowth = ExponentialGrowth(a, 0);
        
        if(ExponentialGrowth != (double) 1){
        	foundGrowth = true;
        }

        //Original array
        int[] temp = new int[a.length];
        for(int i = 0; i < a.length; i++){
          temp[i] = a[i];
        }
        
        //Only want the lower 60%.
        Arrays.sort(temp);
        double SixtyPercentOfSize = (temp.length*.6);
        int newSize = (int) SixtyPercentOfSize;

        //Mean calculation variables
        int total = 0;
        int[] newA = new int[newSize];

        //Loop through data
        for(int i = 0; i < newSize; i++){
            //Mean calculations
            total+= temp[i];

            newA[i] = temp[i];

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

        double PostExponential = ExponentialGrowth * PreExponential;

        lower = (int) PostExponential;

        //Add-ons:
        //Including Accuracy of prediction

        return lower;
    }

    //Calculate the Upper Boundary for an Item
    private int upperBound(int[] a){ //TODO: Algorithm used to calculate upper boundary
        int upper = 0;

        //Calculate for exponential growth
        double ExponentialGrowth = ExponentialGrowth(a, 1);
        
        if(ExponentialGrowth != (double) 1){
        	foundGrowth = true;
        }

        //Original array
        int[] temp = new int[a.length];
        for(int i = 0; i < a.length; i++){
          temp[i] = a[i];
        }
        
        //Only want the upper 60%.
        Arrays.sort(temp);
        double SixtyPercentOfSize = (temp.length*.6);
        int newSize = (int) SixtyPercentOfSize;

        int total = 0;
        int[] newA = new int[newSize];

        //Loop through data
        for(int i = temp.length - newSize; i < temp.length; i++){
            //Mean calculations
            total+= temp[i];
            newA[i-(temp.length-newSize)] = temp[i];
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

        double PostExponential = ExponentialGrowth * PreExponential;

        upper = (int) PostExponential;

        //Add-ons:
        //Including Accuracy of prediction

        return upper;
    }


    public void start (){
        //Scanner for importing data
        Scanner scan;
		try {
			scan = new Scanner(new File("FakeData.csv"));
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
		
		    items = new String[Lines.size()];
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
		
		            itemData[i][j-1] = Integer.parseInt(tempLine[j]);
		        }
		    }
		    lowerBounds = new int[items.length];
		    upperBounds = new int[items.length];
		
		
		    for(int i = 0; i < items.length; i++){ //Should loop according to number of items
		        //Store lower and upper bounds according to number of items we have
		        lowerBounds[i] = lowerBound(itemData[i]);
		        upperBounds[i] = upperBound(itemData[i]);
		        if(foundGrowth){
		        	System.out.println("Found Exponential Growth on Item: " + items[i]);
		        	foundGrowth = false;
		        }
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

}
