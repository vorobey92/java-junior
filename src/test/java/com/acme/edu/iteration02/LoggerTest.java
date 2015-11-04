package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.commands.CommandFactory;
import com.acme.edu.printers.ConsolePrinter;
import com.acme.edu.states.StateFactory;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private final static String SEP = System.lineSeparator();
    private Logger logger;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        logger = new Logger(new ConsolePrinter(), new CommandFactory(), new StateFactory());
    }
    //endregion

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        logger.log("str 1");
        logger.log(1);
        logger.log(2);
        logger.log("str 2");
        logger.log(0);
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                "primitive: 3" + SEP +
                "string: str 2" + SEP +
                "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                "primitive: 10" + SEP +
                "primitive: " + Integer.MAX_VALUE + SEP +
                "string: str 2" + SEP +
                "primitive: " + 0 + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        logger.log("str 1");
        logger.log((byte) 10);
        logger.log(Byte.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                "primitive: 10" + SEP +
                "primitive: " + Byte.MAX_VALUE + SEP +
                "string: str 2" + SEP +
                "primitive: 0" +SEP
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.flush();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                "string: str 2 (x2)" + SEP +
                "primitive: 0" + SEP +
                "string: str 2" + SEP +
                "string: str 3 (x3)" + SEP
        );
        //endregion
    }

    @After
    public void tearDown() {
        resetOut();
    }
}