package com.acme.edu;

import java.io.PrintStream;

/**
 *  Class for logging messages
 */
public class Logger{
    private static int cnt =0;

    /**
     *
     * @param message  number (byte or int) that will be logged
     */
    public static void log(int message) {
        if (message==0) {
            if (cnt>0) {
                print("primitive: " + cnt);
                cnt=0;
            }
            print("primitive: " + message);
        }
        if (message + cnt < 0) {
            print("primitive: " + cnt);
            print("primitive: " + message);
        }
        cnt+=message;
    }

    /**
     *
     * @param message  char that will be logged
     */
    public static void log(char message) {
        print("char: " + message);
    }

    /**
     *
     * @param message  boolean that will be logged
     */
    public static void log(boolean message) {
        print("primitive: " + message);
    }

    /**
     *
     * @param message string that will be logged
     */
    public static void log(String message) {
        if (cnt>0) print("primitive: " + cnt);
        print("string: " + message);
        cnt=0;
    }

    /**
     *
     * @param message object that will be logged
     */
    public static void log(Object message) {
        print("reference: " + message.toString());
    }


    private static void print(String message){
        System.out.println(message);
    }



}
