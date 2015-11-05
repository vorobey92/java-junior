package com.acme.edu.iteration03;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exception.NullMessageException;
import com.acme.edu.exception.PreviousStateIsNullException;
import com.acme.edu.printer.ConsolePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private static final String SEP = System.lineSeparator();
    private Logger logger;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        captureSysout();
        logger = new Logger(new ConsolePrinter());
    }
    //endregion

    @After
    public void tearDown() {
        resetOut();
    }



    @Test
    public void shouldLogIntegersArray() throws IOException, PreviousStateIsNullException, NullMessageException {
        //region when
        logger.log(new int[]{-1, 0, 1});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives array: {-1, 0, 1}" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMatrix() throws IOException, NullMessageException {
        //region when
        logger.log(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        //endregion

        //region then
        assertSysoutEquals(
            "primitives matrix: {" + SEP +
                "{-1, 0, 1}" + SEP +
                "{1, 2, 3}" + SEP +
                "{-1, -2, -3}" + SEP +
            "}" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException, NullMessageException {
        //region when
        logger.log(new int[][][][] {{{{0}}}});
        //endregion

        //region then
        assertSysoutEquals(
            "primitives multimatrix: {" + SEP +
                "{" + SEP + "{" + SEP + "{"  +
                    "0" +
                "}" + SEP + "}" + SEP + "}" + SEP +
            "}" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException, NullMessageException {
        //region when
        logger.log("str1", "string 2", "str 3");
        //endregion

        //region then
        assertSysoutContains("str1"+SEP+"string 2"+SEP+"str 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException, NullMessageException {
        //region when
        logger.log(-1, 0, 1, 3);
        //endregion

        //region then
        assertSysoutEquals(
                "primitives array: {-1, 0, 1, 3}" + SEP
        );
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException, NullMessageException {
        //region when
        logger.log(1);
        logger.log("str");
        logger.log(Integer.MAX_VALUE - 10);
        logger.log(11);
        //endregion

        //region then
        assertSysoutContains("1");
        assertSysoutContains("str");
        assertSysoutContains(""+(Integer.MAX_VALUE - 10));
        assertSysoutContains("11");
        //endregion
    }


}