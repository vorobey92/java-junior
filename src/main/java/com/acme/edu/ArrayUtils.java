package com.acme.edu;

import com.acme.edu.businessexceptions.IllegalArgumentException;

import java.util.Arrays;

class ArrayUtils {

    private ArrayUtils() {

    }

    static int[] multiDimIntArrayToOneDimIntArray(Object[] multiDimArray) throws IllegalArgumentException {
        if (multiDimArray == null) {
            throw new IllegalArgumentException();
        }

        int[] resultArray = new int[0];

        for (Object element : multiDimArray) {
            int[] intArray;

            if (element instanceof int[]) {
                intArray = (int[]) element;
            } else {
                intArray = multiDimIntArrayToOneDimIntArray((Object[]) element);
            }

            resultArray = merge(resultArray, intArray);
        }

        return resultArray;
    }

    private static int[] merge(int[] array1, int[] array2) {
        int[] newArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, newArray, array1.length, array2.length);

        return newArray;
    }
}