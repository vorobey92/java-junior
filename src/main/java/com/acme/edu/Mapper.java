package com.acme.edu;

/**
 *  Class for representing Arrays as Strings.
 */
public class Mapper {

    //region constants
    private static final String SEP = System.lineSeparator();
    private static final String PREFIX_FOR_PRIMITIVES_ARRAY = "primitives array: ";
    private static final String PREFIX_FOR_PRIMITIVES_MATRIX = "primitives matrix: ";
    private static final String PREFIX_FOR_PRIMITIVES_MULTIMATRIX = "primitives multimatrix: {";
    //endregion

    private Mapper(){

    }

    /**
     * String representation of int-array
     * @param message any int array
     * @return string like "primitives array: {...}"
     */
    public static String fromArrayToString(int... message){
        StringBuilder str = new StringBuilder(PREFIX_FOR_PRIMITIVES_ARRAY);
        return str.append(printArray(message)).toString();
    }

    /**
     *  String representation of int-matrix
     * @param message any int matrix
     * @return string like "primitives matrix: { {}, {} }"
     */
    public static String fromMatrixToString(int[][] message){
        StringBuilder str = new StringBuilder(PREFIX_FOR_PRIMITIVES_MATRIX);
        return str.append(printMatrix(message)).toString();
    }

    /**
     * String representation of int-multimatrix
     * @param message any int multimatrix
     * @return "primitives multimatrix: {{{{}}}"
     */
    public static String fromMultiMatrixToString(int[][][][] message){
        StringBuilder str = new StringBuilder(PREFIX_FOR_PRIMITIVES_MULTIMATRIX + SEP);
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
        if (message.length == 0) {
            return "{}" + SEP;
        }
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
