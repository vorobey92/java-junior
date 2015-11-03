package com.acme.edu;

/**
 *  Class for
 */
public class Mapper {

    public static final String SEP = System.lineSeparator();

    private Mapper(){

    }

    /**
     *
     * @param message
     * @return
     */
    public static String fromArrayToString(int... message){
        StringBuilder str = new StringBuilder("primitives array: ");
        return str.append(printArray(message)).toString();
    }

    /**
     *
     * @param message
     * @return
     */
    public static String fromMatrixToString(int[][] message){
        StringBuilder str = new StringBuilder("primitives matrix: ");
        return str.append(printMatrix(message)).toString();
    }

    /**
     *
     * @param message
     * @return
     */
    public static String fromMultiMatrixToString(int[][][][] message){
        StringBuilder str = new StringBuilder("primitives multimatrix: {" + SEP);
        for (int[][][] mes : message) {
            str.append("{" + SEP);
            for (int[][] mes2 : mes) {
                str.append(printMatrix(mes2));
            }
            str.append("}" + SEP);
        }
        return  (str.append("}" + SEP)).toString();

    }

    private static String printArray(int[] message) {
        if (message.length == 0) return "{}" + SEP;
        StringBuilder s = new StringBuilder("");
        s.append("{");
        for (int i = 0; i < message.length - 1; i++){
            s.append(message[i] + ", ");
        }
        s.append(message[message.length - 1] + "}" + SEP);
        return s.toString();
    }

    private static String printMatrix(int[][] message) {
        StringBuilder s = new StringBuilder("");
        s.append("{" + SEP);
        for (int[] arr : message){
            s.append(printArray(arr));
        }
        return (s.append("}" + SEP)).toString();
    }

}
