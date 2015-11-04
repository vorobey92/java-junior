package com.acme.edu.state;


import com.acme.edu.printer.Printable;

/**
 * Class of String state.
 */
public class StringState extends State {

    private static final String PREFIX = "string: ";
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
        if (message == null) {
            return;
        }

        if (buffer.isEmpty()){
            buffer = message;
            return;
        }

        if (message.equals(buffer)) {
            cntOfStrings++;
            return;

        } else if (cntOfStrings == 1) {
            println(PREFIX + buffer);
        } else {
            println(PREFIX + buffer + " (x" + cntOfStrings + ")");
            cntOfStrings = 1;
        }

        buffer = message;
    }

    /**
     * Method to release buffer into log.
     */
    @Override
    public void flush(){
        if (cntOfStrings != 1) {
            println(PREFIX + buffer + " (x" + cntOfStrings + ")");
        } else if (!buffer.isEmpty()) {
            println(PREFIX + buffer);
        }
        cntOfStrings = 1;
        buffer = "";
    }

}
