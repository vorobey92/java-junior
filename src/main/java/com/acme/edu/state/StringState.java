package com.acme.edu.state;


import com.acme.edu.printer.Printer;

/**
 *
 */
public class StringState extends State {

    private String buffer="";
    private static int cntOfStrings = 1;

    /**
     *
     * @param printer
     */
    public StringState(Printer printer) {
        super(printer);
    }

    /**
     *
     * @param message javaDoc not ready
     */
    public void log(String message){

        if (buffer.isEmpty()){
            buffer = message;
            return;
        }

        if (logOrCountAndReturn(message)) {
            return;
        }

        buffer = message;
    }

    /**
     *
     */
    public void flush(){
        releaseStringsFromTemp();
    }

    private void releaseStringsFromTemp() {
        if (cntOfStrings != 1) {
            println("string: " + buffer + " (x" + cntOfStrings + ")");
        } else if (!buffer.isEmpty()) {
            println("string: " + buffer);
        }
        cntOfStrings = 1;
        buffer = "";
    }

    private boolean logOrCountAndReturn(String message) {
        if (message == null) {
            return false;
        }
        if (message.equals(buffer)){
            cntOfStrings++;
            return true;
        } else if (cntOfStrings == 1) {
            println("string: " + buffer);
        } else {
            println("string: " + buffer + " (x" + cntOfStrings + ")");
            cntOfStrings = 1;
        }
        return false;
    }

}
