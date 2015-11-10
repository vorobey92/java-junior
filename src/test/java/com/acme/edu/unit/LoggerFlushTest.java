package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.businessexceptions.LoggerException;
import com.acme.edu.commands.Command;
import com.acme.edu.commands.CommandFactory;
import com.acme.edu.writers.LogWriter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LoggerFlushTest {
    private Logger sut;
    private CommandFactory mockCommandFactory = mock(CommandFactory.class);
    private LogWriter dummyLogWriter = mock(LogWriter.class);
    private Command mockCommand = mock(Command.class);

    @Before
    public void setUp() throws LoggerException {
        sut = new Logger(mockCommandFactory, dummyLogWriter);
    }

    @Test
    public void shouldExecuteLastCommandWhenInvokingFlush() throws LoggerException {
        Object dummyMessage = new Object();

        when(mockCommandFactory.createCommand(eq(dummyMessage), anyVararg())).thenReturn(mockCommand);
        when(mockCommand.merge(any())).thenReturn(mockCommand);

        sut.log(dummyMessage);
        sut.flush();

        verify(mockCommand, times(1)).execute();
    }

    @Test
    public void shouldSetLastCommandToNullWhenInvokingFlush() throws LoggerException {
        Object dummyMessage = new Object();
        Object dummyMessageNew = new Object();
        Command mockCommandNew = mock(Command.class);

        when(mockCommandFactory.createCommand(eq(dummyMessage), anyVararg())).thenReturn(mockCommand);
        when(mockCommandFactory.createCommand(eq(dummyMessageNew), anyVararg())).thenReturn(mockCommandNew);
        when(mockCommand.merge(any())).thenReturn(mockCommand);

        sut.log(dummyMessage);
        sut.flush();
        sut.log(dummyMessageNew);

        verify(mockCommandNew, times(1)).merge(null);
    }

    @Test
    public void shouldAllowDoubleFlushWithoutException() throws LoggerException {
        Object dummyMessage = new Object();

        when(mockCommandFactory.createCommand(eq(dummyMessage), anyVararg())).thenReturn(mockCommand);
        when(mockCommand.merge(any())).thenReturn(mockCommand);

        sut.log(dummyMessage);
        sut.flush();
        sut.flush();
    }
}
