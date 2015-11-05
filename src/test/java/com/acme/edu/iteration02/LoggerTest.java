package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exception.LogException;
import com.acme.edu.exception.NullMessageException;
import com.acme.edu.exception.PreviousStateIsNullException;
import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.state.StateFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.After;

import java.io.IOException;
@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger;
    private static final String SEP = System.lineSeparator();

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        logger = new Logger(new StateFactory(new ConsolePrinter()));
    }
    //endregion

    @After
    public void tearDown() {
        resetOut();
    }


    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException, PreviousStateIsNullException, NullMessageException, LogException {
        //region when
        logger.log("str 1");
        logger.log(1);
        logger.log(2);
        logger.log("str 2");
        logger.log(0);
        logger.close();
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
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() throws PreviousStateIsNullException, NullMessageException, LogException {
        //region when
        logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + SEP +
            "primitive: 10" + SEP +
            "primitive: "+Integer.MAX_VALUE + SEP +
            "string: str 2" + SEP +
            "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() throws PreviousStateIsNullException, NullMessageException, LogException {
        //region when
        logger.log("str 1");
        logger.log((byte)10);
        logger.log((byte)Byte.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1" + SEP +
                        "primitive: " + (Byte.MAX_VALUE + 10) + SEP +
                        "string: str 2" + SEP +
                        "primitive: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException, PreviousStateIsNullException, NullMessageException, LogException {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.close();
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


}