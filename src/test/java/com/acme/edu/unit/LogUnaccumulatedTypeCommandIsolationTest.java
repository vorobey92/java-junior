package com.acme.edu.unit;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.commands.LogUnaccumulatedTypeCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.writers.LogWriter;
import com.acme.edu.writers.LogWriterException;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogUnaccumulatedTypeCommandIsolationTest {
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
    public void shouldLogWhenHasMesage() throws LoggingException, LogWriterException {
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
