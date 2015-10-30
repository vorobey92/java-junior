package com.acme.edu;

import java.util.Arrays;

public class Logger {

    private final static int NOTHING = 0;
    private final static int INT = 1;
    private final static int BYTE = 2;
    private final static int CHAR = 3;
    private final static int STRING = 4;
    private final static int BOOLEAN = 5;
    private final static int OBJECT = 6;
    private final static int INT_ARRAY = 7;

    private static int currentType = NOTHING;

    private final static String PRIMITIVE_STRING = "primitive: ";

    private static int intSum = 0;
    private static byte byteSum = 0;
    private static int lengthOfStringsSequence = 0;
    private static String lastString = null;


    public static void log(int message) {
        changeType(INT);

        if (isSumOutOfIntegerRange(message, intSum)) {
            changeType(NOTHING);
            printlnInt(message);

            return;
        }
        intSum += message;
    }

    public static void log(byte message) {
        changeType(BYTE);

        if (isSumOutOfByteRange(message, byteSum)) {
            changeType(NOTHING);
            printlnInt(message);

            return;
        }
        byteSum += message;
    }

    public static void log(char message) {
        changeType(CHAR);

        println("char: " + message);
    }

    public static void log(String message) {
        changeType(STRING);

        if (lastString != null) {
            if (lastString.equals(message)) {
                lengthOfStringsSequence++;
            } else {
                printlnAccumulatedData(STRING);
                lengthOfStringsSequence = 1;
            }
        } else {
            lengthOfStringsSequence = 1;
        }
        lastString = message;
    }

    public static void log(boolean message) {
        changeType(BOOLEAN);

        println(PRIMITIVE_STRING + message);
    }

    public static void log(Object message) {
        changeType(OBJECT);

        println("reference: " + message);
    }

    public static void log(int[] message) {
        changeType(INT_ARRAY);

        println("primitives array: " + toString(message));
    }

    public static void close() {
        changeType(NOTHING);
    }

    private static void changeType(int type) {
        if (currentType != type) {
            printlnAccumulatedData(currentType);
        }
        currentType = type;
    }

    private static boolean isSumOutOfIntegerRange(int summand1, int summand2) {

        return isSumOutOfRange(summand1, summand2, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private static boolean isSumOutOfByteRange(int summand1, int summand2) {

        return isSumOutOfRange(summand1, summand2, Byte.MAX_VALUE, Byte.MIN_VALUE);
    }

    private static boolean isSumOutOfRange(int summand1, int summand2, int positiveBoundary, int negativeBoundary) {

        return (summand1 > 0 && positiveBoundary - summand1 < summand2)
                || (summand1 < 0 && negativeBoundary - summand1 > summand2);
    }

    private static String toString(int[] message) {
        return Arrays.toString(message).replaceFirst("\\[", "\\{").replaceFirst("\\]", "\\}");
    }

    private static String stringSuffix() {
        return (lengthOfStringsSequence == 1) ? "" : (" (x" + lengthOfStringsSequence + ")");
    }

    private static void printlnAccumulatedData(int type) {
        switch (type) {
            case NOTHING:
                break;
            case INT:
                printlnInt(intSum);
                intSum = 0;
                break;
            case BYTE:
                printlnInt(byteSum);
                byteSum = 0;
                break;
            case CHAR:
                break;
            case STRING:
                printlnSting();
                lastString = null;
                lengthOfStringsSequence = 0;
                break;
            case BOOLEAN:
                break;
            case OBJECT:
                break;
            case INT_ARRAY:
                break;
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);

        }
    }

    private static void printlnInt(int integer) {
        println(PRIMITIVE_STRING + integer);
    }

    private static void printlnSting() {
        println("string: " + lastString + stringSuffix());
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private Logger() {}
}
