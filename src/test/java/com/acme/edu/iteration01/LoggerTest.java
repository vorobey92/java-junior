package com.acme.edu.iteration01;

import com.acme.edu.IntState;
import com.acme.edu.Logger;
import com.acme.edu.State;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.*;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private static final String SEP = System.lineSeparator();
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();

        Logger logger = new Logger();

    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogInteger() throws IOException {
        //region when
          Logger logger = new Logger();
          logger.log(1);
          logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 1"+ SEP);
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        Logger logger = new Logger();
        logger.log((byte)1);
        logger.close();

        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("1");
        //endregion
    }


//    TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogChar() throws IOException {
        //region when
        Logger logger = new Logger();
        logger.log('a');
        logger.log('b');
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws IOException {


        //region when
        Logger logger = new Logger();
        logger.log("test string 1");
        logger.log("other str");
        logger.close();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        Logger logger = new Logger();
        logger.log(true);
        logger.log(false);
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException {
        //region when
        Logger logger = new Logger();
        logger.log(new Object());

        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }


}