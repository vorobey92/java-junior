package com.acme.edu.iteration01;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.businessexceptions.LoggerException;
import com.acme.edu.commands.CommandFactory;
import com.acme.edu.printers.ConsoleLogWriter;
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
        logger = new Logger(new CommandFactory(), new StateFactory(), new ConsoleLogWriter());
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogInteger() throws LoggerException {
        //region when
        logger.log(1);
        logger.log(0);
        logger.log(-1);
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 0" + SEP);
        //endregion
    }

    @Test
    public void shouldLogByte() throws LoggerException {
        //region when
        logger.log((byte) 1);
        logger.log((byte) 0);
        logger.log((byte) -1);
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("0");
        //endregion
    }

    @Test
    public void shouldLogChar() throws LoggerException {
        //region when
        logger.log('a');
        logger.log('b');
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws LoggerException {
        //region when
        logger.log("test string 1");
        logger.log("other str");
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws LoggerException {
        //region when
        logger.log(true);
        logger.log(false);
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws LoggerException {
        //region when
        logger.log(new Object());
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }

}