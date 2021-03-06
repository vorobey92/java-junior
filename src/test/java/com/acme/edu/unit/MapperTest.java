package com.acme.edu.unit;


import com.acme.edu.Mapper;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapperTest {

    private static final String SEP = System.lineSeparator();

    @Test
    public void shouldReturnStringWhenGivenEmptyArray(){
        //region given
        int[] arr3 = {};
        //endregion

        assertEquals("primitives array: {}" + System.lineSeparator(), Mapper.fromArrayToString(arr3));
    }


    @Test
    public void shouldReturnStringWhenGivenArrayWithOneElement(){
        //region given
        int[] arr2 = {0};
        //endregion

        assertEquals("primitives array: {0}" + System.lineSeparator(), Mapper.fromArrayToString(arr2));
    }


    @Test
    public void shouldReturnStringWhenGivenArrayWithSeveralNumbers(){
        //region given
        int[] arr = {1, 2, 3};
        //endregion

        assertEquals("primitives array: {1, 2, 3}" + System.lineSeparator(), Mapper.fromArrayToString(arr));
    }


    @Test
    public void shouldReturnStringWhenGivenEmptyMatrix(){
        //region given
        int[][] mat = { {}, {} };
        //endregion

        assertEquals("primitives matrix: {" + SEP + "{}" + SEP + "{}" + SEP + "}" + SEP, Mapper.fromMatrixToString(mat));
    }

    @Test
    public void shouldReturnStringWhenGivenMatrixWithElements(){
        //region given
        int[][] mat2 = { {-1}, {0, 2, 3}, {} };
        //endregion

        assertEquals("primitives matrix: {" + SEP + "{-1}" + SEP + "{0, 2, 3}" + SEP + "{}" + SEP + "}" + SEP, Mapper.fromMatrixToString(mat2));
    }

    @Test
    public void shouldReturnStringWhenGivenMultiMatrix(){
        //region given
        int[][][][] mult = { { { {0},{} } } };
        //endregion

        assertEquals("primitives multimatrix: {" + SEP + "{" + SEP + "{" + SEP + "{"  + "0" + "}"
                + SEP + "{}"+ SEP + "}" + SEP + "}" + SEP + "}" + SEP, Mapper.fromMultiMatrixToString(mult) );
    }

}
