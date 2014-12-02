/**/

import java.util.*;
import java.text.*;
import java.io.*;

public class Weather {

    static Scanner maxDataRead = null;
    static Scanner minDataRead = null;

    static void setScanners(File minData, File maxData) {
        System.out.println("Reading file and setting delimiter...");
        try {
            maxDataRead = new Scanner(maxData).useDelimiter("[ ,\r\n]");
            minDataRead = new Scanner(minData).useDelimiter("[ ,\r\n]");
            System.out.println("Complete!");
        } catch (Exception e) {
            System.out.println("Error reading imported txt files!");
        }
    }

    static int[] setArrays(Scanner dataRead, int arrayCount) {
        int[] setArray = new int[arrayCount];
        int arraySet = 0;

        while(dataRead.hasNext()) {
            setArray[arraySet] = (int) (dataRead.nextDouble() * 10);
            arraySet++;
        }
        return(setArray);
    }

    static double[] setArrays(int arrayCount, int[] minTempArray, int[] 
            maxTempArray) {
        double[] setArray = new double[arrayCount];

        for(int avgCounter = 0; avgCounter < maxTempArray.length || avgCounter 
                < minTempArray.length; avgCounter++) {
            setArray[avgCounter] = (((double) maxTempArray[avgCounter] / 10)
                    + ((double) minTempArray[avgCounter] / 10)) / 2;
        }
        return(setArray);
    }

    static String setInitialSpace(Calendar cal) {
        String initialSpaceString = null;

        switch(cal.get(Calendar.DAY_OF_WEEK)) {
        case 1: initialSpaceString = " ";
        break;

        case 2: initialSpaceString = "        ";
        break;

        case 3: initialSpaceString = "               ";
        break;

        case 4: initialSpaceString = "                      ";
        break;

        case 5: initialSpaceString = "                             ";
        break;

        case 6: initialSpaceString = "                                 ";
        break;

        case 7: initialSpaceString = "                                      ";
        break;

        default: System.out.println("Error setting initial space!");
        }
        
        return(initialSpaceString);
    }
    
    static void printNum(double value) {
        DecimalFormat threeDigits = new DecimalFormat("00.0");
        System.out.print(threeDigits.format(value));
    }

    static int printDays(int tempDayWeekCount, int print, String 
            initialSpaceString, Calendar cal) {
        if(cal.get(Calendar.DAY_OF_MONTH) == 1)
            System.out.print(initialSpaceString + "  ");
        else
            System.out.print("   ");

        while(tempDayWeekCount <= 7 && print < cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            System.out.print(print + 1);
            if(print + 1 <= 10)
                System.out.print("      ");
            else
                System.out.print("     ");
            tempDayWeekCount++;
            print++;
        }
        return(print);
    }

    static int printArrays(int tempDayWeekCount, int print, String 
            initialSpaceString, Calendar cal, int[] printArray) {
        if(cal.get(Calendar.DAY_OF_MONTH) == 1)
            System.out.print(initialSpaceString);
        else
            System.out.print(" ");

        while(tempDayWeekCount <= 7 && print < cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            printNum((double) printArray[print] / 10);
            if(((double) printArray[print]) / 10 > 0)
                System.out.print("   ");
            else
                System.out.print("  ");
            tempDayWeekCount++;
            print++;
        }
        return(print);
    }

    static int printArrays(int tempDayWeekCount, int print, String 
            initialSpaceString, Calendar cal, double[] printArray) {
        if(cal.get(Calendar.DAY_OF_MONTH) == 1)
            System.out.print(initialSpaceString);
        else
            System.out.print(" ");

        while(tempDayWeekCount <= 7 && print < cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            printNum(printArray[print]);
            System.out.print("   ");
            tempDayWeekCount++;
            print++;
        }
        return(print);
    }
    public static void main(String[] args) {
        int arrayCount = 0;
        int dayPrint = 0;
        int minPrint = 0;
        int maxPrint = 0;
        int avgPrint = 0;
        String initialSpaceString = null;
        int[] maxTempArray, minTempArray;
        double[] avgTempArray;

        Calendar cal = new GregorianCalendar(2014, 9, 1);
        int tempDayWeekCount = cal.get(Calendar.DAY_OF_WEEK);

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
            maxDataRead.next();
            arrayCount++;
        }
        
        initialSpaceString = setInitialSpace(cal);

        setScanners(minData, maxData);

        maxTempArray = new int[arrayCount];
        minTempArray = new int[arrayCount];
        avgTempArray = new double[arrayCount];

        maxTempArray = setArrays(maxDataRead, arrayCount);
        minTempArray = setArrays(minDataRead, arrayCount);
        avgTempArray = setArrays(arrayCount, minTempArray, maxTempArray);

        System.out.println("Weather report for Hamilton-----");

        while(cal.get(Calendar.DAY_OF_MONTH) < cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            System.out.println("====================================================");
            System.out.println("     --S--  --M--  --T--  --W--  --T--  --F--  --S--");
            System.out.println("====================================================");
            System.out.print("MAX|");
            maxPrint = printArrays(tempDayWeekCount, maxPrint, initialSpaceString, cal, maxTempArray);
            System.out.println();
            System.out.print("MIN|");
            minPrint = printArrays(tempDayWeekCount, minPrint, initialSpaceString, cal, minTempArray);
            System.out.println();
            System.out.print("AVG|");
            avgPrint = printArrays(tempDayWeekCount, avgPrint, initialSpaceString, cal, avgTempArray);
            System.out.println();
            System.out.println("----------------------------------------------------");
            System.out.print("DAY|");
            dayPrint = printDays(tempDayWeekCount, dayPrint, initialSpaceString, cal);
            System.out.println();
            tempDayWeekCount = 1;
            cal.set(Calendar.DAY_OF_MONTH, dayPrint);
        }
    }
}
