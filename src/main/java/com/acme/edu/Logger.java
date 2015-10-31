package com.acme.edu;

public class Logger {

    private final static int NOTHING = 0;
    private final static int INT = 1;
    private final static int BYTE = 2;
    private final static int CHAR = 3;
    private final static int STRING = 4;
    private final static int BOOLEAN = 5;
    private final static int OBJECT = 6;
    private final static int INT_ARRAY = 7;
    private final static int INT_MATRIX = 8;
    private final static int INT_MULTIMATRIX = 9;

    private static int currentType = NOTHING;

    private final static String PRIMITIVE_STRING = "primitive: ";

    private static int sum = 0;
    private static int lengthOfStringsSequence = 0;
    private static String lastString = null;

    private Logger() {
    }

    public static void log(int message) {
        logIntegerMessage(INT, message);
    }

    public static void log(byte message) {
        logIntegerMessage(BYTE, message);
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

        println("primitives array: " + Helper.intArrayToString(message));
    }

    public static void log(int[][] message) {
        changeType(INT_MATRIX);

        println("primitives matrix: " + Helper.multidimensionalIntArraytoString(message));
    }

    public static void log(int[][][][] message) {
        changeType(INT_MULTIMATRIX);

        println("primitives multimatrix: " + Helper.multidimensionalIntArraytoString(message));
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

    private static void logIntegerMessage(int type, int message) {
        changeType(type);

        if (isSumOutOfRange(type, message, sum)) {
            changeType(NOTHING);
            printlnInteger(message);

            return;
        }
        sum += message;
    }

    private static boolean isSumOutOfRange(int type, int summand1, int summand2) {
        int positiveBoundary;
        int negativeBoundary;

        switch (type) {
            case INT:
                positiveBoundary = Integer.MAX_VALUE;
                negativeBoundary = Integer.MIN_VALUE;
                break;
            case BYTE:
                positiveBoundary = Byte.MAX_VALUE;
                negativeBoundary = Byte.MIN_VALUE;
                break;
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }

        return (summand1 > 0 && positiveBoundary - summand1 < summand2)
                || (summand1 < 0 && negativeBoundary - summand1 > summand2);
    }

    private static void printlnAccumulatedData(int type) {
        switch (type) {
            case NOTHING:
                break;
            case INT:
            case BYTE:
                printlnInteger(sum);
                sum = 0;
                break;
            case CHAR:
                break;
            case STRING:
                printlnLastString();
                lastString = null;
                lengthOfStringsSequence = 0;
                break;
            case BOOLEAN:
                break;
            case OBJECT:
                break;
            case INT_ARRAY:
                break;
            case INT_MATRIX:
                break;
            case INT_MULTIMATRIX:
                break;
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);

        }
    }

    private static void printlnInteger(int integer) {
        println(PRIMITIVE_STRING + integer);
    }

    private static void printlnLastString() {
        println("string: " + lastString + stringSuffix());
    }

    private static String stringSuffix() {
        return (lengthOfStringsSequence == 1) ? "" : (" (x" + lengthOfStringsSequence + ")");
    }

    private static void println(String message) {
        System.out.println(message);
    }
}