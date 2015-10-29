package com.acme.edu;

public class Logger {
    public static void log(int message) {
        print("primitive: " + message);
    }

    public static void log(byte message) {
        print("primitive: " + message);
    }

    public static void log(char message) {
        print("char: " + message);
    }
    public static void log(boolean message) {
        print("primitive: " + message);
    }

    private static void print(String message){
        System.out.println(message);
    }
}
