package com.acme.edu;


public class IntState extends State {

    private static final String PRIMITIVE = "primitive: ";
    private String buffer="";
    private static int cntOfInts = 0;
    private static int bufferOfInts = 0;

    public void log(String message){
        if (printIfOverflof(Integer.parseInt(message))) {
            return;
        }
        if (!buffer.isEmpty()) {
            releaseStringsFromTemp();
        }
        bufferOfInts += Integer.parseInt(message);
        cntOfInts++;
    }

    public void close(){

        checkAndPrintSum();
        releaseStringsFromTemp();

    }

    private static void resetCounters() {
        bufferOfInts = 0;
        cntOfInts = 0;
    }

    private static boolean printIfOverflof(int message) {
        if (message + bufferOfInts < 0) {
            println(PRIMITIVE + bufferOfInts);
            println(PRIMITIVE + message);
            resetCounters();
            return true;
        }
        return false;
    }

    private static void checkAndPrintSum() {
        if (cntOfInts > 0) {
            println(PRIMITIVE + bufferOfInts);
            resetCounters();
        }
    }

    private static void releaseStringsFromTemp() {
//        if (cntOfStrings != 1) {
//            println(STRING + buffer + " (x" + cntOfStrings + ")");
//        } else if (!buffer.isEmpty()) {
//            println(STRING + buffer);
//        }
//        cntOfStrings = 1;
//        buffer = "";
        return;

    }

    private static boolean printIfOverflof(byte message) {
        if ((byte)(message + bufferOfInts) < 0) {
            println(PRIMITIVE + bufferOfInts);
            println(PRIMITIVE + message);
            resetCounters();
            return true;
        }
        return false;
    }
}
