package com.acme.edu.unit;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.commands.Command;
import com.acme.edu.commands.LogIntegerCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.writers.LogWriter;
import com.acme.edu.writers.LogWriterException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.fest.assertions.Assertions.*;

public class LogIntegerCommandInteractionTest {
    private LogWriter mockLogWriter;
    private LogIntegerCommand sut;
    private Decorator stubDecorator;
    private String decoratedString;
    private int maxValue;
    private int minValue;

    @Before
    public void setUp() {
        maxValue = Integer.MAX_VALUE;
        minValue = Integer.MIN_VALUE;
        decoratedString = "decorated string ";
        mockLogWriter = mock(LogWriter.class);
        stubDecorator = mock(Decorator.class);
        sut = new LogIntegerCommand(stubDecorator, maxValue, minValue, mockLogWriter);
    }

    @Test
    public void shouldNotLogWhenMergeWithNullCommand() throws LogWriterException, LoggingException {
        String message = "test message";

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);

        sut.setMessage(message);
        sut.merge(null);

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

    @Test
    public void shouldNotModifyOwnMessageWhenMergeWithNullCommand() throws LogWriterException, LoggingException {
        String message = "test message";

        sut.setMessage(message);
        sut.merge(null);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldNotLogWhenMergeWithCommandThatDoesNotHaveMessage() throws LogWriterException, LoggingException {
        String message = "test message";
        LogIntegerCommand stubCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

    @Test
    public void shouldNotModifyOwnMessageWhenMergeWithCommandThatDoesNotHaveMessage() throws LogWriterException, LoggingException {
        String message = "test message";
        LogIntegerCommand stubCommand = mock(LogIntegerCommand.class);

        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldLogTwoIntegersWhenPositiveOverflowOccurs() throws LogWriterException, LoggingException {
        String message = "" + maxValue;
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(1)).execute();
        verify(mockLogWriter, times(1)).writeLine(decoratedString);
    }

    @Test
    public void shouldLogTwoIntegersRespectivelyPositiveWhenOverflowOccurs() throws LogWriterException, LoggingException {
        String message = "" + maxValue;
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        InOrder inOrder = inOrder(mockCommand, mockLogWriter);

        inOrder.verify(mockCommand).execute();
        inOrder.verify(mockLogWriter).writeLine(anyString());
    }

    @Test
    public void shouldLogTwoIntegersWhenNegativeOverflowOccurs() throws LogWriterException, LoggingException {
        String message = "" + minValue;
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn("-1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(1)).execute();
        verify(mockLogWriter, times(1)).writeLine(decoratedString);
    }

    @Test
    public void shouldLogTwoIntegersRespectivelyNegativeWhenOverflowOccurs() throws LogWriterException, LoggingException {
        String message = "" + minValue;
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn("-1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        InOrder inOrder = inOrder(mockCommand, mockLogWriter);

        inOrder.verify(mockCommand).execute();
        inOrder.verify(mockLogWriter).writeLine(anyString());
    }

    @Test
    public void shouldNotLogWhenOverflowDoesNotOccur() throws LogWriterException, LoggingException {
        String message = "0";
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).execute();
        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

    @Test
    public void shouldSumWhenOverflowDoesNotOccur() throws LogWriterException, LoggingException {
        String message = "0";
        LogIntegerCommand stubCommand = mock(LogIntegerCommand.class);

        when(stubCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(stubCommand);

        assertThat(sut.getMessage()).isEqualTo("1");
    }

    @Test
    public void shouldNotModifyArgumentWhenOverflowDoesNotOccur() throws LogWriterException, LoggingException {
        String message = "0";
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(mockCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).setMessage(anyString());
    }

    @Test
    public void shouldExecuteOldCommandOfAnotherClassWhileMerging() throws LogWriterException, LoggingException {
        Command mockOldCommand = mock(Command.class);
        String message = "0";
        String oldCommandMessage = "old command message";

        when(mockOldCommand.getMessage()).thenReturn(oldCommandMessage);

        sut.setMessage(message);
        sut.merge(mockOldCommand);

        verify(mockOldCommand, times(1)).execute();
    }

    @Test
    public void shouldNotLogWhileMergingWithOldCommandOfAnotherClass() throws LogWriterException, LoggingException {
        Command mockOldCommand = mock(Command.class);
        String message = "0";
        String oldCommandMessage = "old command message";

        when(mockOldCommand.getMessage()).thenReturn(oldCommandMessage);

        sut.setMessage(message);
        sut.merge(mockOldCommand);

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }
}