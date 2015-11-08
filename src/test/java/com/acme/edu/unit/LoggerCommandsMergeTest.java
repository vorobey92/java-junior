package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.businessexceptions.LoggerException;
import com.acme.edu.commands.Command;
import com.acme.edu.commands.CommandFactory;
import com.acme.edu.printers.LogWriter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LoggerCommandsMergeTest {
    private Logger sut;
    private CommandFactory mockCommandFactory = mock(CommandFactory.class);
    private LogWriter dummyLogWriter = mock(LogWriter.class);
    private Command mockCommandOld = mock(Command.class);
    private Command mockCommandNew = mock(Command.class);

    @Before
    public void setUp() throws LoggerException {
        sut = new Logger(mockCommandFactory, dummyLogWriter);
    }

    @Test
    public void shouldMergeOldCommandWithNewWhenLoggingSuccessively() throws LoggerException {
        Object dummyMessageOld = new Object();
        Object dummyMessageNew = new Object();

        when(mockCommandFactory.createCommand(eq(dummyMessageOld), anyVararg())).thenReturn(mockCommandOld);
        when(mockCommandFactory.createCommand(eq(dummyMessageNew), anyVararg())).thenReturn(mockCommandNew);
        when(mockCommandOld.merge(any())).thenReturn(mockCommandOld);

        sut.log(dummyMessageOld);
        sut.log(dummyMessageNew);

        verify(mockCommandNew, times(1)).merge(mockCommandOld);
    }

    @Test
    public void shouldSetLastCommandToNullRightAfterCreation() throws LoggerException {
        Object dummyMessage = new Object();

        when(mockCommandFactory.createCommand(eq(dummyMessage), anyVararg())).thenReturn(mockCommandOld);

        sut.log(dummyMessage);

        verify(mockCommandOld, times(1)).merge(null);
    }
}
