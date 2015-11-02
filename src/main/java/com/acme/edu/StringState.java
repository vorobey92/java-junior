package com.acme.edu;



public class StringState extends State {

    public void log(String message){
        println(message);
    }

    public void close(){

        checkAndPrintSum();
        releaseStringsFromTemp();

    }
    private static void checkAndPrintSum() {
//        if (cntOfInts > 0) {
//            println(PRIMITIVE + bufferOfInts);
//            resetCounters();
//        }
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

}
