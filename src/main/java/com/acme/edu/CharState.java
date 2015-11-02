package com.acme.edu;

/**
 *
 */
public class CharState extends State {
    public void log(String message){
        println("char: " + message);
    }

    @Override
    /**
     *
     */
    public void flush() {
    }
}
