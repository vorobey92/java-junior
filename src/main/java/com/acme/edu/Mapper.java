package com.acme.edu;

/**
 *
 */
public class Mapper {


    /**
     *
     * @param message
     * @return
     */
    public static String fromArrayToString(int... message){
        StringBuffer str = new StringBuffer("primitives array: ");
        return str.append(printArray(message)).toString();
    }

    /**
     *
     * @param message
     * @return
     */
    public static String fromMatrixToString(int[][] message){
        StringBuffer str = new StringBuffer("primitives matrix: ");
        return str.append(printMatrix(message)).toString();
    }

    /**
     *
     * @param message
     * @return
     */
    public static String fromMultiMatrixToString(int[][][][] message){
        StringBuffer str = new StringBuffer("primitives multimatrix: {" + System.lineSeparator());
        for (int[][][] mes : message) {
            str.append("{" + System.lineSeparator());
            for (int[][] mes2 : mes) {
                str.append(printMatrix(mes2));
            }
            str.append("}" + System.lineSeparator());
        }
        return  (str.append("}" + System.lineSeparator())).toString();

    }

    private static String printArray(int[] message) {
        StringBuffer s = new StringBuffer("");
        s.append("{");
        for (int i = 0; i < message.length - 1; i++){
            s.append(message[i] + ", ");
        }
        s.append(message[message.length - 1] + "}" + System.lineSeparator());
        return s.toString();
    }

    private static String printMatrix(int[][] message) {
        StringBuffer s = new StringBuffer("");
        s.append("{" + System.lineSeparator());
        for (int[] arr : message){
            s.append(printArray(arr));
        }
        return (s.append("}" + System.lineSeparator())).toString();
    }

}
