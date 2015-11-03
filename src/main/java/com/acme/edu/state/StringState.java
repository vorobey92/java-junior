package com.acme.edu.state;


import com.acme.edu.printer.Printable;

/**
 *
 */
public class StringState extends State {

    private static final String STRING = "string: ";
    private String buffer="";
    private static int cntOfStrings = 1;

    /**
     *
     * @param printable
     */
    public StringState(Printable printable) {
        super(printable);
    }

    /**
     *
     * @param message javaDoc not ready
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
     *
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
