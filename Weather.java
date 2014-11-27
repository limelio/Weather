/**/

import java.util.*;
import java.io.*;

public class Weather {
    
    static Scanner maxDataRead = null;
    static Scanner minDataRead = null;
    
    static void setScanners(File minData, File maxData) {
        System.out.println("Reading files and setting delimiters...");
        try {
            maxDataRead = new Scanner(maxData).useDelimiter(",");
            minDataRead = new Scanner(minData).useDelimiter(",");
            System.out.println("Complete!");
        } catch (Exception e) {
            System.out.println("Error reading imported txt files!");
        }
    }
    
    static int[] setMinMaxArrays(Scanner dataRead, int arrayCount) {
        int[] setArray = new int[arrayCount];
        int arraySet = 0;
        
        while(dataRead.hasNext()) {
            setArray[arraySet] = (int) (dataRead.nextDouble() * 10);
        }
        return(setArray);
    }
    
    static double[] setAvgArray(int arrayCount, int[] minTempArray, int[] 
            maxTempArray) {
        double[] setArray = new double[arrayCount];
        
        for(int avgCounter = 0; avgCounter < maxTempArray.length || avgCounter 
                < minTempArray.length; avgCounter++) {
            setArray[avgCounter] = ((double) maxTempArray[avgCounter] / 10)
                    / ((double) minTempArray[avgCounter] / 10);
        }
        
        return(setArray);
    }
    
    public static void main(String[] args) {
        int arrayCount = 0;
        String scanInput;
        
        int[] maxTempArray, minTempArray;
        double[] avgTempArray;
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Importing \"temp data files\"...");
        File maxData = null;
        File minData = null;
        try {
            maxData = new File("max.txt");
            minData = new File("min.txt");
            System.out.println("Imported \"files\"!");
        } catch (Exception e) {
            System.out.println("Error importing files!");
        }
        
        setScanners(minData, maxData);
        
        while(maxDataRead.hasNext()) {
            minDataRead.next();
            arrayCount++;
        }
        
        setScanners(minData, maxData);
        
        maxTempArray = new int[arrayCount];
        minTempArray = new int[arrayCount];
        avgTempArray = new double[arrayCount];
        
        maxTempArray = setMinMaxArrays(maxDataRead, arrayCount);
        minTempArray = setMinMaxArrays(minDataRead, arrayCount);
        avgTempArray = setAvgArray(arrayCount, minTempArray, maxTempArray);
        
        
    }
}