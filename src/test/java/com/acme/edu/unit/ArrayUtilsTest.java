package com.acme.edu.unit;

import com.acme.edu.ArrayUtils;
import com.acme.edu.businessexceptions.IllegalArgumentException;
import org.junit.Test;

import static org.fest.assertions.Assertions.*;

public class ArrayUtilsTest {
    @Test
    public void shouldConvertTwoDimensionalIntArrayToIntArray() throws IllegalArgumentException {
        int[][] twoDimensionalArray = {{0}, {1, 2}, {}, {3, 4}};
        int[] expectedResult = {0, 1, 2, 3, 4};

        assertThat(ArrayUtils.multiDimIntArrayToOneDimIntArray(twoDimensionalArray)).isEqualTo(expectedResult);
    }

    @Test
    public void shouldConvertFourDimensionalIntArrayToIntArray() throws IllegalArgumentException {
        int[][][][] fourDimensionalArray = {{{{0}, {1, 2}}}, {}, {{{3, 4}, {5, 6}}, {{7}}}};
        int[] expectedResult = {0, 1, 2, 3, 4, 5, 6, 7};

        assertThat(ArrayUtils.multiDimIntArrayToOneDimIntArray(fourDimensionalArray)).isEqualTo(expectedResult);
    }
}
