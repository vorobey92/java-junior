package com.acme.edu;

/**
 *
 */
public abstract class State {
    /**
     *
     * @param message JavaDoc not ready
     */
    public void log(String message){

    }

    /**
     *
     */
    public void flush(){

    }

    protected static void print(String message){
        System.out.print(message);
    }

    protected static void println(String message){
        print(message + System.lineSeparator());
    }




}
