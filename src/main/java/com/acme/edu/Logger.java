package com.acme.edu;
 import com.acme.edu.*;
/**
 *  Class for logging messages.
 *  To use logging you need to use method Logger.close() to finish.
 */
public class Logger {
    //region Constants
//    public static final String PRIMITIVE = "primitive: ";
//    public static final String STRING = "string: ";
    //endregion
    //region privateVars
//    private static int cntOfInts = 0;
//    private static int bufferOfInts = 0;
//    private static String buffer = "";
//    private static int cntOfStrings = 1;
//    private static String bufferOfInts = "0";
    //endregions

    public Logger(){

    }

//    enum State {
//        STRING, INT_OR_BYTE, EMPTY;
//    }

    /**
     *  Method for logging ints.
     *  Tip: For sequence of ints This method will log sum of ints when
     *  Stirng log is came or logging is completed.
     *  Also it logs two numbers (sum and new int) if type overflow while summing.
     * @param message  number (int) that will be logged (or sum for sequence)
     */
    public void log(int message) {
        State intState = new IntState();
        intState.log(message+"");
        intState.close();
    }

    /**
     *  Method for logging bytes.
     *  Tip: For sequence of bytes This method will log sum of bytes when
     *  Stirng log is came or logging is completed.
     *  Also it logs two numbers (sum and new byte) if type overflow while summing.
     * @param message  number (byte) that will be logged (or sum for sequence)
     */
//    public static void log(byte message) {
//        if (printIfOverflof(message)) {
//            return;
//        }
//        log((int)message);
//    }

    /**
     *  Method for logging bytes.
     * @param message  char that will be logged
     */
//    public static void log(char message) {
//        println("char: " + message);
//    }

    /**
     *  Method for logging boolean.
     * @param message  boolean that will be logged
     */
//    public static void log(boolean message) {
//        println(PRIMITIVE + message);
//    }

    /**
     * Method for logging Strings.
     * Before logging String, this method will log sum of sequent ints (if sum is exists).
     * If message matches last logged String, method will count it before new String is came or Logger.close()
     * method is called or any number is logged.
     * @param message string that will be logged
     */
//    public static void log(String message) {
//        checkAndPrintSum();
//
//        if (buffer.isEmpty()){
//            buffer = message;
//            return;
//        }
//
//        if (logOrCountAndReturn(message)) {
//            return;
//        }
//
//        buffer = message;
//        changeStateOrPrint(State.STRING, buffer);
//    }

    /**
     *  Method for logging Object
     * @param message object that will be logged
     */
//    public static void log(Object message) {
//        println("reference: " + message.toString());
//    }

    /**
     *  Method for logging arrays of ints.
     * @param message array of ints that will be loged
     */
//    public static void log(int... message){
//        print("primitives array: ");
//        printArray(message);
//    }

    /**
     *
     * @param message matrix of ints that will be loged
     */
//    public static void log(int[][] message){
//        print("primitives matrix: ");
//        printMatrix(message);
//    }

    /**
     *
     * @param message multimatrix that will be loged
     */
//    public static void log(int[][][][] message){
//        println("primitives multimatrix: {");
//
//        for (int[][][] mes : message) {
//            println("{");
//            for (int[][] mes2 : mes) {
//                printMatrix(mes2);
//            }
//            println("}");
//        }
//        println("}");
//    }

    /**
     *
     * @param message array of Strings that will be loged
     */
//    public static void log(String... message){
//        for(String str : message) {
//            println(str);
//        }
//    }

    /**
     * Method for finishing logging. Prints the rest statement.
     */
//    public static void close(){
//        checkAndPrintSum();
//        releaseStringsFromTemp();
//    }

 /*   private static void changeStateOrPrint(State param, String message){
//        switch (param){
//            case STRING :
//                break;
//            case INT_OR_BYTE :
                if (printIfOverflof(Integer.parseInt(message))) {
                    return;
                }
                if (!buffer.isEmpty()) {
                    releaseStringsFromTemp();
                }
                bufferOfInts += Integer.parseInt(message);
                cntOfInts++;
//                break;
//            case EMPTY :
//                break;
        }
    }
    */
/*


    private static void checkAndPrintSum() {
        if (cntOfInts > 0) {
            println(PRIMITIVE + bufferOfInts);
            resetCounters();
        }
    }



    private static boolean logOrCountAndReturn(String message) {
        if (message == null) {
            return false;
        }
        if (message.equals(buffer)){
            cntOfStrings++;
            return true;
        } else if (cntOfStrings == 1) {
            println(STRING + buffer);
        } else {
            println(STRING + buffer + " (x" + cntOfStrings + ")");
            cntOfStrings = 1;
        }
        return false;
    }

    protected static void print(String message){
        System.out.print(message);
    }

    protected static void println(String message){
        print(message + System.lineSeparator());
    }

    private static void printArray(int[] message) {
        print("{");
        for (int i = 0; i < message.length - 1; i++){
            print(message[i] + ", ");
        }
        println(message[message.length - 1] + "}");
    }

    private static void printMatrix(int[][] message) {
        println("{");
        for (int[] arr : message){
            printArray(arr);
        }
        println("}");
    }
*/
}
