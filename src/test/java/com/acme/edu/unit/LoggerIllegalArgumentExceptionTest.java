package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.businessexceptions.LoggerException;
import com.acme.edu.commands.Command;
import com.acme.edu.commands.CommandFactory;
import com.acme.edu.printers.LogWriter;
import com.acme.edu.states.State;
import com.acme.edu.states.StateFactory;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LoggerIllegalArgumentExceptionTest {
    private Logger sut;
    private CommandFactory commandFactoryOfDummies = new CommandFactory() {
        @Override
        public Command createCommand(Type type, LogWriter... logWriters) {
            return mock(Command.class);
        }
    };
    private StateFactory stateFactoryOfDummies = new StateFactory() {
        @Override
        public State createState() {
            return mock(State.class);
        }
    };
    private LogWriter dummyLogWriter = mock(LogWriter.class);

//    TODO: test scenarios when exceptions should not be thrown

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenNullLogWriterWasPassed() throws LoggerException {
        sut = createSut((LogWriter) null);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenNullArrayOfLogWritersWasPassed() throws LoggerException {
        sut = createSut((LogWriter[]) null);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenEmptyArrayOfLogWritersWasPassed() throws LoggerException {
        sut = createSut(new LogWriter[0]);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPassedArrayOfLogWritersHasNullElement() throws LoggerException {
        sut = createSut(dummyLogWriter, null, dummyLogWriter);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogNullString() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log((String) null);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogNullArrayOfStrings() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log((String[]) null);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogArrayOfStringsWithNullElement() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log("test string 1", null, "test string 2");
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogNullObject() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log((Object) null);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogNullArrayOfInts() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log((int[]) null);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogNullTwoDimensionalArrayOfInts() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log((int[][]) null);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogTwoDimensionalArrayOfIntsWithNullElement() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log(new int[][] {{0}, null, {1}});
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogNullFourDimensionalArrayOfInts() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log((int[][][][]) null);
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogFourDimensionalArrayOfIntsWhichContainsNullArray() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log(new int[][][][] {{{{2, 3}}, {{0}, null, {1}}}});
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogFourDimensionalArrayOfIntsWhichContainsNullTwoDimensionalArray() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log(new int[][][][] {{{{2, 3}}}, {{{0, 1}}, null}});
    }

    @Test(expected = com.acme.edu.businessexceptions.IllegalArgumentException.class)
    public void shouldThowIllegalArgumentExceptionWhenTryingToLogFourDimensionalArrayOfIntsWhichContainsNullThreeDimensionalArray() throws LoggerException {
        sut = createSut(dummyLogWriter);
        sut.log(new int[][][][] {{{{2, 3}}}, null, {{{0, 1}}, {{5, 6}}}});
    }

    private Logger createSut(LogWriter... logWriters) throws LoggerException {
        return new Logger(commandFactoryOfDummies, stateFactoryOfDummies, logWriters);
    }
}
