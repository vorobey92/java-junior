package com.acme.edu;

class Helper {

    private final static String LINE_SEPARATOR = System.lineSeparator();

    private Helper() {

    }

    static String multidimensionalIntArraytoString(Object[] array) {
        StringBuilder sb = new StringBuilder();
        arrayToStringBuilder(array, sb);

        return sb.toString();
    }

    static String intArrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < array.length - 1; ++i) {
            sb.append(array[i]).append(", ");
        }
        if (array.length > 0) {
            sb.append(array[array.length - 1]);
        }
        sb.append("}");

        return sb.toString();
    }

    private static void arrayToStringBuilder(Object[] array, StringBuilder sb) {
        sb.append("{").append(LINE_SEPARATOR);
        for (int i = 0; i < array.length; ++i) {
            elementToStringBuilder(i, array, sb);
            sb.append(LINE_SEPARATOR);
        }
        sb.append("}");
    }

    private static void elementToStringBuilder(int index, Object[] array, StringBuilder sb) {
        if (array[index] == null) {
            sb.append("{").append(LINE_SEPARATOR).append("}");
            return;
        }
        if (array[index] instanceof int[]) {
            sb.append(intArrayToString((int[]) array[index]));
            return;
        }

        arrayToStringBuilder((Object[]) array[index], sb);
    }
}
