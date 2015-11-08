package com.acme.edu.unit;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.commands.LogUnaccumulatedTypeCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;
import com.acme.edu.printers.LogWriterException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogUnaccumulatedTypeCommandInteractionTest {
    private Decorator stubDecorator;
    private String decoratedString = "decorated string";
    private String message = "test message";
    private LogWriter mockLogWriter;
    private LogUnaccumulatedTypeCommand sut;

    @Before
    public void setUp() {
        mockLogWriter = mock(LogWriter.class);
        stubDecorator = mock(Decorator.class);
        sut = new LogUnaccumulatedTypeCommand(stubDecorator, mockLogWriter);
    }

    @Test
    public void shouldLogWhenMergeWithNullCommand() throws LogWriterException, LoggingException {
        when(stubDecorator.decorate(message)).thenReturn(decoratedString);

        sut.setMessage(message);
        sut.merge(null);

        verify(mockLogWriter, times(1)).writeLine(decoratedString);
    }

    @Test
    public void shouldLogWhenMergeWithCommandThatDoesNotHaveMessage() throws LogWriterException, LoggingException {
        LogUnaccumulatedTypeCommand stubCommand = mock(LogUnaccumulatedTypeCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        verify(mockLogWriter, times(1)).writeLine(decoratedString);
    }

    @Test
    public void shouldNotLogOldMessageWhenMergeWithAnotherCommand() throws LogWriterException, LoggingException {
        String oldMessage = "old message";
        LogUnaccumulatedTypeCommand mockCommand = mock(LogUnaccumulatedTypeCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).execute();
    }
}
