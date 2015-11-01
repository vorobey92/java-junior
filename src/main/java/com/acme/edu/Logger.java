package com.acme.edu;

/**
 * The Logger can be used to log different types of messages to the standard output stream. The Logger
 * accumulates some types of messages before logging (see description in method-level documentation).
 * The close() method is used to force the Logger to write an accumulated data to the standard output stream.
 */
public class Logger {

    private final static int UNACCUMULATING = 0;
    private final static int INT = 1;
    private final static int BYTE = 2;
    private final static int STRING = 3;

    private static int currentType = UNACCUMULATING;

    private final static String PRIMITIVE_STRING = "primitive: ";

    private static int sum = 0;
    private static int lengthOfStringsSequence = 0;
    private static String lastString = null;

    private Logger() {
    }

    /**
     * Accumulates an integer. This is done by adding the passed integer to the sum. The sum is internally
     * maintained through a continuous sequence of invocations of methods referred to int-family.
     * If adding the integer comes to an integer overflow, the sum and the integer are logged respectively and
     * the sum is set to zero. The accumulating is considered dropped.
     * If the sequence of invocations of this method is discontinued by any method of Logger class not referred
     * to int-family, the sum is logged and set to zero. The accumulating is considered dropped.
     * This method also logs the currently accumulated data if it invokes after any other accumulated method of
     * the Logger class.
     *
     * @param message The int to be accumulated.
     */
    public static void log(int message) {
        logIntegerMessage(INT, message);
    }

    /**
     * Accumulates a byte. This is done by adding the passed byte to the sum. The sum is internally maintained
     * through a continuous sequence of invocations of this method.
     * If adding the byte comes to an byte overflow, the sum and the byte are logged respectively and the sum
     * is set to zero. The accumulating is considered dropped.
     * If the sequence of invocations of this method is discontinued by any other method of Logger class, the sum
     * is logged and set to zero. The accumulating is considered dropped.
     * This method also logs the currently accumulated data if it invokes after any other accumulated method of
     * the Logger class.
     *
     * @param message The byte to be accumulated.
     */
    public static void log(byte message) {
        logIntegerMessage(BYTE, message);
    }

    /**
     * Logs a char.
     * This method also logs the currently accumulated data if it invokes after any accumulated method of
     * the Logger class.
     *
     * @param message The char to be logged.
     */
    public static void log(char message) {
        changeType(UNACCUMULATING);

        println("char: " + message);
    }

    /**
     * Accumulates a String. The Logger internally maintains the last provided string and the number of
     * sequential invocations of methods referred to String-family with the same argument. If the passed string
     * equals to provided string, the number is incremented. Otherwise the last provided string is logged along
     * with the number of sequential invocations and the passed string becomes the last provided string.
     * If the sequence of invocations of this method is discontinued by any other method of Logger class
     * not referred to String-family, the last provided string is logged along with the number of sequential
     * invocations. The accumulating is considered dropped.
     * This method also logs the currently accumulated data if it invokes after any other accumulated method of
     * the Logger class.
     *
     * @param message The String to be accumulated.
     */
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

    /**
     * Logs a boolean.
     * This method also logs the currently accumulated data if it invokes after any accumulated method of
     * the Logger class.
     *
     * @param message The boolean to be logged.
     */
    public static void log(boolean message) {
        changeType(UNACCUMULATING);

        println(PRIMITIVE_STRING + message);
    }

    /**
     * Logs an Object.
     * This method also logs the currently accumulated data if it invokes after any accumulated method of
     * the Logger class.
     *
     * @param message The Object to be logged.
     */
    public static void log(Object message) {
        changeType(UNACCUMULATING);

        println("reference: " + message);
    }

    /**
     * Accumulates an array of ints. This method behaves as a sequence of invocations of method log(int) for
     * each int in the passed array.
     *
     * @param message an array of ints to be accumulated.
     */
    public static void log(int... message) {
        for (int intMessage : message) {
            log(intMessage);
        }
    }

    /**
     * Accumulates a two-dimensional array of ints. This method behaves as a sequence of invocations of
     * method log(int[]) for each underlying array of ints in the passed two-dimensional array of ints.
     *
     * @param message a two-dimensional array of ints to be accumulated.
     */
    public static void log(int[][] message) {
        log(Helper.multiDimIntArrayToOneDimIntArray(message));
    }

    /**
     * Accumulates a four-dimensional array of ints. This method behaves as a sequence of invocations of
     * method log(int[]) for each underlying array of ints in the passed four-dimensional array of ints.
     *
     * @param message a four-dimensional array of ints to be accumulated.
     */
    public static void log(int[][][][] message) {
        log(Helper.multiDimIntArrayToOneDimIntArray(message));
    }

    /**
     * Accumulates an array of Strings. This method behaves as a sequence of invocations of method log(String)
     * for each String in the passed array of Strings.
     *
     * @param messages an array of Strings to be accumulated.
     */
    public static void log(String... messages) {
        for (String string : messages) {
            log(string);
        }
    }

    /**
     * Closes the Logger. This is done by loging the accumulated data. The Logger still can be used
     * after the close() method is invoked.
     */
    public static void close() {
        changeType(UNACCUMULATING);
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
            changeType(UNACCUMULATING);
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
            case UNACCUMULATING:
                break;
            case INT:
            case BYTE:
                printlnInteger(sum);
                sum = 0;
                break;
            case STRING:
                printlnLastString();
                lastString = null;
                lengthOfStringsSequence = 0;
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