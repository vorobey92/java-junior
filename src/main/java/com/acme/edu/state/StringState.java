package com.acme.edu.state;


import com.acme.edu.printer.Printable;

/**
 * Class of String state.
 */
public class StringState extends State {

    private static final String STRING = "string: ";
    private String buffer="";
    private static int cntOfStrings = 1;

    /**
     *  Creating StringState object
     * @param printer determines stream for logging
     */
    public StringState(Printable printer) {
        super(printer);
    }

    /**
     * Method for logging Strings.
     *  If message matches last logged String, method will count it before new String is came or Logger.flush() is called
     * @param message string that will be logged
     */
    @Override
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
     * Method to release buffer into log.
     */
    @Override
    public void flush(){
        releaseStringsFromTemp();
    }

    private void releaseStringsFromTemp() {
        if (cntOfStrings != 1) {
            println(STRING + buffer + " (x" + cntOfStrings + ")");
        } else if (!buffer.isEmpty()) {
            println(STRING + buffer);
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
            println(STRING + buffer);
        } else {
            println(STRING + buffer + " (x" + cntOfStrings + ")");
            cntOfStrings = 1;
        }
        return false;
    }

}
