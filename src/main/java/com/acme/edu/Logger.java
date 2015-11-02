package com.acme.edu;

/**
 *  Class for logging messages.
 *  To use logging you need to use method Logger.close() to finish.
 */
public class Logger {

    private State state;
    private State lastState;

  private enum StateLoggerHolder{
        INT(new IntState()),
        STRING(new StringState()),
        BYTE(new ByteState()),
        CHAR(new CharState()),
        BOOL(new BooleanState()),
        OBJ(new ObjectState());

        private State state;

        StateLoggerHolder(State state){
            this.state = state;
        }

    }

    /**
     *  Method for logging ints.
     *  Tip: For sequence of ints This method will log sum of ints when
     *  Stirng log is came or logging is completed.
     *  Also it logs two numbers (sum and new int) if type overflow while summing.
     * @param message  number (int) that will be logged (or sum for sequence)
     */
    public void log(int message) {
        matchIntStateOrReleaseBuff();
        state = StateLoggerHolder.INT.state;
        state.log(message + "");
        lastState = StateLoggerHolder.INT.state;
    }

    /**
     *  Method for logging bytes.
     *  Tip: For sequence of bytes This method will log sum of bytes when
     *  String log is came or logging is completed.
     *  Also it logs two numbers (sum and new byte) if type overflow while summing.
     * @param message  number (byte) that will be logged (or sum for sequence)
     */
    public void log(byte message) {
        matchIntStateOrReleaseBuff();
        state = new ByteState();
        state.log(message + "");
        lastState = StateLoggerHolder.BYTE.state;
    }

    /**
     *  Method for logging bytes.
     * @param message  char that will be logged
     */
    public void log(char message) {
        state = StateLoggerHolder.CHAR.state;
        state.log("" + message);
    }

    /**
     *  Method for logging boolean.
     * @param message  boolean that will be logged
     */
    public void log(boolean message) {
        state = StateLoggerHolder.BOOL.state;
        state.log("" + message);
    }

    /**
     * Method for logging Strings.
     * Before logging String, this method will log sum of sequent ints (if sum exists).
     * If message matches last logged String, method will count it before new String is came or Logger.close()
     * method is called or any number is logged.
     * @param message string that will be logged
     */
    public void log(String message) {
        matchStringStateOrReleaseBuff();
        state = StateLoggerHolder.STRING.state;
        state.log(message);
        lastState = StateLoggerHolder.STRING.state;
    }

    /**
     *  Method for logging Object
     * @param message object that will be logged
     */
    public void log(Object message) {
        state = StateLoggerHolder.OBJ.state;
        state.log(""+message);
    }

    private void matchIntStateOrReleaseBuff(){
        if (lastState != null && lastState != StateLoggerHolder.INT.state ){
            lastState.flush();
        }
    }

    private void matchStringStateOrReleaseBuff(){
        if (lastState != null && lastState != StateLoggerHolder.STRING.state ){
            lastState.flush();
        }
    }

    /**
     * Method for finishing logging. Prints the rest statement.
     */
    public void close(){
        lastState.flush();
    }

    /**
     *  Method for logging arrays of ints.
     * @param message array of ints that will be loged
     */
//    public static void log(int... message){
//        print("primitives array: ");
//        printArray(message);
//    }

    /**
     *
     * @param message matrix of ints that will be loged
     */
//    public static void log(int[][] message){
//        print("primitives matrix: ");
//        printMatrix(message);
//    }

    /**
     *
     * @param message multimatrix that will be loged
     */
//    public static void log(int[][][][] message){
//        println("primitives multimatrix: {");
//
//        for (int[][][] mes : message) {
//            println("{");
//            for (int[][] mes2 : mes) {
//                printMatrix(mes2);
//            }
//            println("}");
//        }
//        println("}");
//    }

    /**
     *
     * @param message array of Strings that will be loged
     */
//    public static void log(String... message){
//        for(String str : message) {
//            println(str);
//        }
//    }

    /**
     * Method for finishing logging. Prints the rest statement.
     */
//    public static void close(){
//        checkAndPrintSum();
//        releaseStringsFromTemp();
//    }

 /*   private static void changeStateOrPrint(State param, String message){
//        switch (param){
//            case STRING :
//                break;
//            case INT_OR_BYTE :
                if (printIfOverflof(Integer.parseInt(message))) {
                    return;
                }
                if (!buffer.isEmpty()) {
                    releaseStringsFromTemp();
                }
                bufferOfInts += Integer.parseInt(message);
                cntOfInts++;
//                break;
//            case EMPTY :
//                break;
        }
    }
    */
/*


    private static void checkAndPrintSum() {
        if (cntOfInts > 0) {
            println(PRIMITIVE + bufferOfInts);
            resetCounters();
        }
    }



    private static boolean logOrCountAndReturn(String message) {
        if (message == null) {
            return false;
        }
        if (message.equals(buffer)){
            cntOfStrings++;
            return true;
        } else if (cntOfStrings == 1) {
            println(STRING + buffer);
        } else {
            println(STRING + buffer + " (x" + cntOfStrings + ")");
            cntOfStrings = 1;
        }
        return false;
    }

    protected static void print(String message){
        System.out.print(message);
    }

    protected static void println(String message){
        print(message + System.lineSeparator());
    }

    private static void printArray(int[] message) {
        print("{");
        for (int i = 0; i < message.length - 1; i++){
            print(message[i] + ", ");
        }
        println(message[message.length - 1] + "}");
    }

    private static void printMatrix(int[][] message) {
        println("{");
        for (int[] arr : message){
            printArray(arr);
        }
        println("}");
    }
*/
}
