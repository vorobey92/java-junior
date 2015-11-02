package com.acme.edu;

/**
 *
 */
public class ObjectState extends State {
    /**
     *
     * @param message JavaDoc not ready
     */
    public void log(String message){
        println("reference: " + message);
    }

    @Override
    /**
    *
    */
    public void flush() {
    }
}
