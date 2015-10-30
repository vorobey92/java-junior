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
     *
     * @param message array of ints that will be loged
     */
    public static void log(int... message){
        System.out.print("primitives array: ");
        printArray(message);
    }

    /**
     *
     * @param message matrix of ints that will be loged
     */
    public static void log(int[][] message){
        System.out.println("primitives matrix: {");
        for (int i = 0; i < message.length; i++){
            printArray(message[i]);
        }
        System.out.println("}");
    }

    /**
     *
     * @param message multimatrix that will be loged
     */
    public static void log(int[][][][] message){
        System.out.println("primitives multimatrix: {");
        for (int i = 0; i < message.length; i++){
            System.out.println("{");
            for(int j = 0; j < message[i].length; j++){
                System.out.println("{");
                for (int k = 0; j < message[i][j].length; k++){
                    System.out.println("{");
                    for(int x:message[i][j][k])System.out.print(x);
                    System.out.println();
                    System.out.println("}");
                    break;
                }
                System.out.println("}");
            }
            System.out.println("}");
        }
        System.out.println("}");
    }

    /**
     *
     * @param message array of Strings that will be loged
     */
    public static void log(String... message){
        for(String str:message)System.out.println(str);
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

    private static void printArray(int[] message) {
        System.out.print("{");
        for (int i = 0; i < message.length-1; i++){
            System.out.print(message[i]+", ");
        }
        System.out.println(message[message.length - 1] + "}");
    }



}
