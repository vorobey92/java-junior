package com.acme.edu.unit;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.commands.LogStringCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;
import com.acme.edu.printers.LogWriterException;
import org.junit.Before;
import org.junit.Test;
import sun.rmi.runtime.Log;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogStringCommandIsolationTest {
    private Decorator stubOneStringSequenceDecorator;
    private Decorator stubMultipleStringSequenceDecorator;
    private String oneStringSequenceDecoratedString = "decorated string when 1 string accumulated";
    private String multipleStringsSequenceDecoratedString = "decorated string when several strings accumulated";
    private String message = "test message";
    private LogWriter mockLogWriter;
    private LogStringCommand sut;

    @Before
    public void setUp() {
        mockLogWriter = mock(LogWriter.class);
        stubOneStringSequenceDecorator = mock(Decorator.class);
        stubMultipleStringSequenceDecorator = mock(Decorator.class);
        sut = new LogStringCommand(stubOneStringSequenceDecorator, stubMultipleStringSequenceDecorator, mockLogWriter);
    }

    @Test
    public void shouldLogWhenAccumulatedNumberIsOne() throws LogWriterException, LoggingException {
        when(stubOneStringSequenceDecorator.decorate(message, 1)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message, 1)).thenReturn(multipleStringsSequenceDecoratedString);

        sut.setMessage(message);
        sut.execute();

        verify(mockLogWriter, times(1)).writeLine(oneStringSequenceDecoratedString);
    }

    @Test
    public void shouldLogWhenAccumulatedNumberIsTwo() throws LogWriterException, LoggingException {
        when(stubOneStringSequenceDecorator.decorate(message, 2)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message, 2)).thenReturn(multipleStringsSequenceDecoratedString);
        LogStringCommand stubLogStringCommand = mock(LogStringCommand.class);

        when(stubLogStringCommand.getMessage()).thenReturn(message);
        when(stubLogStringCommand.getLengthOfStringsSequence()).thenReturn(1);

        sut.setMessage(message);
        sut.merge(stubLogStringCommand);
        sut.execute();

        verify(mockLogWriter, times(1)).writeLine(multipleStringsSequenceDecoratedString);
    }

    @Test
    public void shouldSetMessageToNullAfterLogging() throws LogWriterException, LoggingException {
        sut.setMessage(message);
        sut.execute();

        assertThat(sut.getMessage()).isEqualTo(null);
    }

    @Test
    public void shouldNotLogWhenHasNullMessage() throws LogWriterException, LoggingException {
        when(stubOneStringSequenceDecorator.decorate(any())).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(any())).thenReturn(multipleStringsSequenceDecoratedString);

        sut.setMessage(null);
        sut.execute();

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

    @Test
    public void shouldHaveNullMessageRightAfterCreation() {
        assertThat(sut.getMessage()).isEqualTo(null);
    }

    @Test
    public void shouldHaveZeroAsCounterRightAfterCreation() {
        assertThat(sut.getLengthOfStringsSequence()).isEqualTo(0);
    }

    @Test
    public void shouldHaveOneAsCounterRightAfterMessageWasSet() {
        sut.setMessage(message);

        assertThat(sut.getLengthOfStringsSequence()).isEqualTo(1);
    }
}