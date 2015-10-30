package com.acme.edu;

import java.io.PrintStream;

/**
 *  Class for logging messages.
 *  To use logging you need to use method Logger.close() to finish.
 */
public class Logger{
    private static int cnt =0;

    /**
     *
     * @param message  number (int) that will be logged
     */
    public static void log(int message) {
        if (checkOverflof(message)) return;
        cnt+=message;
    }

    /**
     *
     * @param message  number (byte) that will be logged
     */
    public static void log(byte message) {
        if (checkOverflof(message)) return;
        cnt=(byte)cnt;
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
        checkAndPrintSum();
        print("string: " + message);
    }

    /**
     *
     * @param message object that will be logged
     */
    public static void log(Object message) {
        print("reference: " + message.toString());
    }

    /**
     * Method for finishing logging. Prints the rest statement.
     */
    public static void close(){
        print("primitive: " + cnt);
        cnt=0;
    }

    private static void print(String message){
        System.out.println(message);
    }

    private static boolean checkOverflof(int message) {
        if (message + cnt < 0) {
            print("primitive: " + cnt);
            print("primitive: " + message);
            cnt=0;
            return true;
        }
        return false;
    }

    private static boolean checkOverflof(byte message) {
        if ((byte)(message +( (byte) cnt)) < 0) {
            print("primitive: " + cnt);
            print("primitive: " + message);
            cnt=0;
            return true;
        }
        return false;
    }
    
    private static void checkAndPrintSum() {
        if (cnt>0) {
            print("primitive: " + cnt);
            cnt=0;
        }
    }



}
