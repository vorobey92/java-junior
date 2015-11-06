package com.acme.edu.iteration03;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.businessexceptions.LoggerException;
import com.acme.edu.commands.CommandFactory;
import com.acme.edu.printers.ConsolePrinter;
import com.acme.edu.states.StateFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private final static String SEP = System.lineSeparator();
    private Logger logger;

    //region given
    @Before
    public void setUpSystemOut() throws LoggerException {
        resetOut();
        captureSysout();
        logger = new Logger(new CommandFactory(), new StateFactory(), new ConsolePrinter());
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogIntegersArray() throws LoggerException {
        //region when
        logger.log(-1, 0, 1);
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMatrix() throws LoggerException {
        //region when
        logger.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws LoggerException {
        //region when
        logger.log(new int[][][][]{{{{0}}}});
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws LoggerException {
        //region when

        logger.log("str1", "string 2", "str 3");
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("string: str1" + SEP + "string: string 2" + SEP + "string: str 3" + SEP);
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws LoggerException {
        //region when
        logger.log(-1, 0, 1, 3);
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws LoggerException {
        //region when
        logger.log(1);
        logger.log("str");
        logger.log(Integer.MAX_VALUE - 10);
        logger.log(11);
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("" + 1);
        assertSysoutContains("str");
        assertSysoutContains("" + (Integer.MAX_VALUE - 10));
        assertSysoutContains("" + 11);
        //endregion
    }
}