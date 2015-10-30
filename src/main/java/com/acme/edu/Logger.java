package com.acme.edu;

public class Logger1 {

    private final static int NOTHING = 0;
    private final static int INT = 1;
    private final static int BYTE = 2;
    private final static int CHAR = 3;
    private final static int STRING = 4;
    private final static int BOOLEAN = 5;
    private final static int OBJECT = 6;

    private static int currentType = NOTHING;

    private final static String PRIMITIVE_STRING = "primitive: ";

    private static int intSum = 0;
    private static byte byteSum = 0;
    private static int lengthOfStringsSequence = 0;
    private static String lastString = null;


    public static void log(int message) {
        if (currentType != INT) {
            fflush(currentType);
        }
        currentType = INT;

        intSum += message;
    }

    public static void log(byte message) {
        if (currentType != BYTE) {
            fflush(currentType);
        }
        currentType = BYTE;

        byteSum += message;
    }

    public static void log(char message) {
        if (currentType != CHAR) {
            fflush(currentType);
        }
        currentType = CHAR;

        println("char: " + message);
    }

    public static void log(String message) {
        if (currentType != STRING) {
            fflush(currentType);
        }
        currentType = STRING;

        if (lastString != null) {
            if (lastString.equals(message)) {
                lengthOfStringsSequence++;
            } else {
                printString();
                lengthOfStringsSequence = 1;
            }
        } else {
            lengthOfStringsSequence = 1;
        }
        lastString = message;
    }

    public static void log(boolean message) {
        if (currentType != BOOLEAN) {
            fflush(currentType);
        }
        currentType = BOOLEAN;

        println(PRIMITIVE_STRING + message);
    }

    public static void log(Object message) {
        if (currentType != OBJECT) {
            fflush(currentType);
        }
        currentType = OBJECT;

        println("reference: " + message);
    }

    public static void close() {
        if (currentType != NOTHING) {
            fflush(currentType);
        }
        currentType = NOTHING;
    }

    private static String stringSuffix() {
        return (lengthOfStringsSequence == 1) ? "" : (" (x" + lengthOfStringsSequence + ")");
    }

    private static void fflush(int type) {
        switch (type) {
            case NOTHING:
                break;
            case INT:
                printIntSum();
                intSum = 0;
                break;
            case BYTE:
                printByteSum();
                byteSum = 0;
                break;
            case CHAR:
                break;
            case STRING:
                printString();
                lastString = null;
                lengthOfStringsSequence = 0;
                break;
            case BOOLEAN:
                break;
            case OBJECT:
                break;
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);

        }
    }

    private static void printIntSum() {
        println(PRIMITIVE_STRING + intSum);
    }

    private static void printString() {
        println("string: " + lastString + stringSuffix());
    }

    private static void printByteSum() {
        println(PRIMITIVE_STRING + byteSum);
    }

    private static void println(String message) {
        System.out.println(message);
    }
}
