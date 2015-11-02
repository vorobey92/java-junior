package com.acme.edu;

/**
 *
 */
public class BooleanState extends State {
    /**
     *
     * @param message JavaDoc not ready
     */
    public void log(String message){
        println("primitive: " + message);
    }

    @Override
    /**
     *
     */
    public void flush() {
    }

}
