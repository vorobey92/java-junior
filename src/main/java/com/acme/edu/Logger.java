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
        if (message.equals(temp)){
            strCnt++;
            return;
        }
        else if (strCnt==1)
            print("string: " + temp);
            else {
            print("string: " + temp + " (x"+strCnt+")");
            strCnt=1;
            }
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
        if (cnt > 0) {
            print("primitive: " + sum);
            sum = 0;
            cnt=0;
        }
        if (strCnt > 1) {
            print("string: " +temp + " (x"+strCnt+")");
            strCnt=1;
            temp="";
        }
        if (!temp.equals("")) print("string: " + temp);
    }

    private static void print(String message){
        System.out.println(message);
    }

    private static boolean checkOverflof(int message) {
        if (message + sum < 0) {
            print("primitive: " + sum);
            print("primitive: " + message);
            sum =0;
            cnt=0;
            return true;
        }
        return false;
    }

    private static boolean checkOverflof(byte message) {
        if ((byte)(message +( (byte) sum)) < 0) {
            print("primitive: " + sum);
            print("primitive: " + message);
            sum =0;
            cnt=0;
            return true;
        }
        return false;
    }

    private static void checkAndPrintSum() {
        if ( cnt>0 && sum >=0) {
            print("primitive: " + sum);
            sum =0;
            cnt=0;
        }
    }

    private static void releaseStringsFromTemp() {
        if (strCnt!=1)
        print("string: " + temp + " (x"+strCnt+")");
        else print("string: " + temp);
        strCnt=1;
        temp="";
    }



}
