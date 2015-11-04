package com.acme.edu;

import java.util.Arrays;

class ArrayUtils {

    private ArrayUtils() {

    }

    static int[] multiDimIntArrayToOneDimIntArray(Object[] multiDimArray) {
        int[] intArray = new int[0];

        for (Object element : multiDimArray) {
            intArray = merge(intArray, elementToIntArray(element));
        }

        return intArray;
    }

    private static int[] merge(int[] array1, int[] array2) {
        int[] newArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, newArray, array1.length, array2.length);

        return newArray;
    }

    private static int[] elementToIntArray(Object element) {
        if (element == null) {
            return new int[0];
        }
        if (element instanceof int[]) {
            return (int[]) element;
        }

        return multiDimIntArrayToOneDimIntArray((Object[]) element);
    }
}
