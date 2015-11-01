package com.acme.edu.iteration03;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private final static String SEP = System.lineSeparator();

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogIntegersArray() throws IOException {
        //region when
        Logger.log(-1, 0, 1);
        Logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMatrix() throws IOException {
        //region when
        Logger.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        Logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
        //region when
        Logger.log(new int[][][][]{{{{0}}}});
        Logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException {
        //region when
        Logger.log("str1", "string 2", "str 3");
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("string: str1" + SEP + "string: string 2" + SEP + "string: str 3" + SEP);
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        Logger.log(-1, 0, 1, 3);
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
        //region when
        Logger.log(1);
        Logger.log("str");
        Logger.log(Integer.MAX_VALUE - 10);
        Logger.log(11);
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("" + 1);
        assertSysoutContains("str");
        assertSysoutContains("" + (Integer.MAX_VALUE - 10));
        assertSysoutContains("" + 11);
        //endregion
    }
}