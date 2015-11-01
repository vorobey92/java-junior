package com.acme.edu;

import java.util.Arrays;

class Helper {

    private Helper() {

    }

    static int[] multiDimIntArrayToOneDimIntArray(Object[] multiDimArray) {
        int[] intArray = new int[0];

        for (int i = 0; i < multiDimArray.length; ++i) {
            intArray = merge(intArray, elementToIntArray(i, multiDimArray));
        }

        return intArray;
    }

    private static int[] merge(int[] array1, int[] array2) {
        int[] newArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, newArray, array1.length, array2.length);

        return newArray;
    }

    private static int[] elementToIntArray(int index, Object[] array) {
        if (array[index] == null) {
            return new int[0];
        }
        if (array[index] instanceof int[]) {
            return (int[]) array[index];
        }

        return multiDimIntArrayToOneDimIntArray((Object[]) array[index]);
    }
}
