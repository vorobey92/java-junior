package com.acme.edu;


public abstract class State {

    public void log(String message){

    }

    protected static void print(String message){
        System.out.print(message);
    }

    protected static void println(String message){
        print(message + System.lineSeparator());
    }


    public void close(){

    }



}
