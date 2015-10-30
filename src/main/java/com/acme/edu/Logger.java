package com.acme.edu;

/**
 *  Class for logging messages.
 *  To use logging you need to use method Logger.close() to finish.
 */
public class Logger{
    private static int cnt =0;
    private static int sum = 0;
    private static String temp = "";
    private static int strCnt = 1;

    /**
     *
     * @param message  number (int) that will be logged
     */
    public static void log(int message) {
        if (!temp.equals("")) releaseStringsFromTemp();
        if (checkOverflof(message)) return;
        sum +=message;
        cnt++;
    }

    /**
     *
     * @param message  number (byte) that will be logged
     */
    public static void log(byte message) {
        if (!temp.equals("")) releaseStringsFromTemp();
        if (checkOverflof(message)) return;
        sum +=message;
        cnt++;
    }

    /**
     *
     * @param message  char that will be logged
     */
    public static void log(char message) {
        print("char: " + message);
    }

    /**
     *
     * @param message  boolean that will be logged
     */
    public static void log(boolean message) {
        print("primitive: " + message);
    }

    /**
     *
     * @param message string that will be logged
     */
    public static void log(String message) {
        checkAndPrintSum();
        if (temp.equals("")){
            temp = message;
            return;
        }
        if (LogOrCountAndReturn(message)) return;
        temp=message;
    }

    /**
     *
     * @param message object that will be logged
     */
    public static void log(Object message) {
        print("reference: " + message.toString());
    }

    /**
     * Method for finishing logging. Prints the rest statement.
     */
    public static void close(){
        checkAndPrintSum();
        releaseStringsFromTemp();
    }

    private static void print(String message){
        System.out.println(message);
    }

    private static boolean checkOverflof(int message) {
        if (message + sum < 0) {
            print("primitive: " + sum);
            print("primitive: " + message);
            resetCounters();
            return true;
        }
        return false;
    }

    private static boolean checkOverflof(byte message) {
        if ((byte)(message +( sum)) < 0) {
            print("primitive: " + sum);
            print("primitive: " + message);
            resetCounters();
            return true;
        }
        return false;
    }

    private static void checkAndPrintSum() {
        if ( cnt>0 ) {
            print("primitive: " + sum);
            resetCounters();
        }
    }

    private static void releaseStringsFromTemp() {
        if (strCnt!=1)
            print("string: " + temp + " (x"+strCnt+")");
        else if (!temp.equals("")) print("string: " + temp);
        strCnt=1;
        temp="";

    }

    private static boolean LogOrCountAndReturn(String message) {
        if (message.equals(temp)){
            strCnt++;
            return true;
        }
        else if (strCnt==1)
            print("string: " + temp);
        else {
            print("string: " + temp + " (x"+strCnt+")");
            strCnt=1;
        }
        return false;
    }

    private static void resetCounters() {
        sum =0;
        cnt=0;
    }



}
