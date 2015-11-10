package com.acme.edu.unit;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.commands.LogIntegerCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.writers.LogWriter;
import com.acme.edu.writers.LogWriterException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.fest.assertions.Assertions.*;

public class LogIntegerCommandIsolationTest {
    private LogWriter mockLogWriter;
    private LogIntegerCommand sut;
    private Decorator stubDecorator;
    private String decoratedString;
    private String message;

    @Before
    public void setUp() {
        message = "message";
        decoratedString = "decorated string ";
        mockLogWriter = mock(LogWriter.class);
        stubDecorator = mock(Decorator.class);
        sut = new LogIntegerCommand(stubDecorator, Integer.MAX_VALUE, Integer.MIN_VALUE, mockLogWriter);
    }

    @Test
    public void shouldLogWhenHasMessage() throws LogWriterException, LoggingException {
        when(stubDecorator.decorate(message)).thenReturn(decoratedString);

        sut.setMessage(message);
        sut.execute();

        verify(mockLogWriter, times(1)).writeLine(decoratedString);
    }

    @Test
    public void shouldSetMessageToNullAfterLogging() throws LogWriterException, LoggingException {
        sut.setMessage(message);
        sut.execute();

        assertThat(sut.getMessage()).isEqualTo(null);
    }

    @Test
    public void shouldNotLogWhenHasNullMessage() throws LogWriterException, LoggingException {
        when(stubDecorator.decorate(any())).thenReturn(decoratedString);

        sut.setMessage(null);
        sut.execute();

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

    @Test
    public void shouldHaveNullMessageRightAfterCreation() {
        assertThat(sut.getMessage()).isEqualTo(null);
    }
}
