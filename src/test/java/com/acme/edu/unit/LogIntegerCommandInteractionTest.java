package com.acme.edu.unit;

import com.acme.edu.commands.LogIntegerCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.Printer;
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
    private Printer mockPrinter;
    private LogIntegerCommand sut;
    private Decorator stubDecorator;
    private String decoratedString;
    private int maxValue = Integer.MAX_VALUE;
    private int minValue = Integer.MIN_VALUE;

    @Before
    public void setUp() {
        decoratedString = "decorated string ";
        mockPrinter = mock(Printer.class);
        stubDecorator = mock(Decorator.class);
        sut = new LogIntegerCommand(mockPrinter, stubDecorator, maxValue, minValue);
    }

    @Test
    public void shouldNotLogWhenMergeWithNullCommand() {
        String message = "test message";

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);

        sut.setMessage(message);
        sut.merge(null);

        verify(mockPrinter, times(0)).println(anyString());
    }

    @Test
    public void shouldNotModifyOwnMessageWhenMergeWithNullCommand() {
        String message = "test message";

        sut.setMessage(message);
        sut.merge(null);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldNotLogWhenMergeWithCommandThatDoesNotHaveMessage() {
        String message = "test message";
        LogIntegerCommand stubCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        verify(mockPrinter, times(0)).println(anyString());
    }

    @Test
    public void shouldNotModifyOwnMessageWhenMergeWithCommandThatDoesNotHaveMessage() {
        String message = "test message";
        LogIntegerCommand stubCommand = mock(LogIntegerCommand.class);

        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldLogTwoIntegersWhenPositiveOverflowOccurs() {
        String message = "" + maxValue;
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);
        when(mockCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(1)).execute();
        verify(mockPrinter, times(1)).println(decoratedString);
    }

    @Test
    public void shouldLogTwoIntegersRespectivelyPositiveWhenOverflowOccurs() {
        String message = "" + maxValue;
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        InOrder inOrder = inOrder(mockCommand, mockPrinter);

        inOrder.verify(mockCommand).execute();
        inOrder.verify(mockPrinter).println(anyString());
    }

    @Test
    public void shouldLogTwoIntegersWhenNegativeOverflowOccurs() {
        String message = "" + minValue;
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn("-1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(1)).execute();
        verify(mockPrinter, times(1)).println(decoratedString);
    }

    @Test
    public void shouldLogTwoIntegersRespectivelyNegativeWhenOverflowOccurs() {
        String message = "" + minValue;
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn("-1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        InOrder inOrder = inOrder(mockCommand, mockPrinter);

        inOrder.verify(mockCommand).execute();
        inOrder.verify(mockPrinter).println(anyString());
    }

    @Test
    public void shouldNotLogWhenOverflowDoesNotOccur() {
        String message = "0";
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).execute();
        verify(mockPrinter, times(0)).println(anyString());
    }

    @Test
    public void shouldSumWhenOverflowDoesNotOccur() {
        String message = "0";
        LogIntegerCommand stubCommand = mock(LogIntegerCommand.class);

        when(stubCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(stubCommand);

        assertThat(sut.getMessage()).isEqualTo("1");
    }

    @Test
    public void shouldNotModifyArgumentWhenOverflowDoesNotOccur() {
        String message = "0";
        LogIntegerCommand mockCommand = mock(LogIntegerCommand.class);

        when(mockCommand.getMessage()).thenReturn("1");

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).setMessage(anyString());
    }
}
