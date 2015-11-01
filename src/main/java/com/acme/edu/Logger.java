package com.acme.edu;

/**
 *  Class for logging messages.
 *  To use logging you need to use method Logger.close() to finish.
 */
public class Logger {

    //region privateVars
    private static int cntOfInts = 0;
    private static int sumOfInts = 0;
    private static String tempStr = "";
    private static int cntOfStrings = 1;
    //endregions

    /**
     *
     * @param message  number (int) that will be logged
     */
    public static void log(int message) {
        if (!"".equals(tempStr)) {
            releaseStringsFromTemp();
        }

        if (printIfOverflof(message)) {
            return;
        }

        sumOfInts += message;
        cntOfInts++;
    }

    /**
     *
     * @param message  number (byte) that will be logged
     */
    public static void log(byte message) {
        if (!"".equals(tempStr)) {
            releaseStringsFromTemp();
        }

        if (printIfOverflof(message)) {
            return;
        }

        sumOfInts += message;
        cntOfInts++;
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

        if ("".equals(tempStr)){
            tempStr = message;
            return;
        }

        if (logOrCountAndReturn(message)) {
            return;
        }

        tempStr = message;
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
        System.out.print("primitives matrix: ");
        printMatrix(message);
    }

    /**
     *
     * @param message multimatrix that will be loged
     */
    public static void log(int[][][][] message){
        System.out.println("primitives multimatrix: {");

        for (int[][][] mes : message) {
            System.out.println("{");
            for (int[][] mes2 : mes) {
                printMatrix(mes2);
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
        for(String str : message) {
            System.out.println(str);
        }
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

    private static boolean printIfOverflof(int message) {
        if (message + sumOfInts < 0) {
            print("primitive: " + sumOfInts);
            print("primitive: " + message);
            resetCounters();
            return true;
        }
        return false;
    }

    private static boolean printIfOverflof(byte message) {
        if ((byte)(message + sumOfInts) < 0) {
            print("primitive: " + sumOfInts);
            print("primitive: " + message);
            resetCounters();
            return true;
        }
        return false;
    }

    private static void checkAndPrintSum() {
        if (cntOfInts > 0) {
            print("primitive: " + sumOfInts);
            resetCounters();
        }
    }

    private static void releaseStringsFromTemp() {
        if (cntOfStrings != 1) {
            print("string: " + tempStr + " (x" + cntOfStrings + ")");
        } else if (!"".equals(tempStr)) {
            print("string: " + tempStr);
        }
        cntOfStrings = 1;
        tempStr = "";

    }

    private static boolean logOrCountAndReturn(String message) {
        if (message.equals(tempStr)){
            cntOfStrings++;
            return true;
        } else if (cntOfStrings == 1) {
            print("string: " + tempStr);
        } else {
            print("string: " + tempStr + " (x" + cntOfStrings + ")");
            cntOfStrings = 1;
        }
        return false;
    }

    private static void resetCounters() {
        sumOfInts = 0;
        cntOfInts = 0;
    }

    private static void printArray(int[] message) {
        System.out.print("{");
        for (int i = 0; i < message.length - 1; i++){
            System.out.print(message[i] + ", ");
        }
        System.out.println(message[message.length - 1] + "}");
    }

    private static void printMatrix(int[][] message) {
        System.out.println("{");
        for (int i = 0; i < message.length; i++){
            printArray(message[i]);
        }
        System.out.println("}");
    }

    private Logger(){}



}
