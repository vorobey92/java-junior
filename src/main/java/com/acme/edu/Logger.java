package com.acme.edu;

/**
 *  Class for logging messages
 */
public class Logger {

    /**
     *
     * @param message  number (byte or int) that will be logged
     */
    public static void log(int message) {
        print("primitive: " + message);
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
        print("string: " + message);
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
