package com.acme.edu.unit;

import com.acme.edu.commands.LogIntegerCommand;
import com.acme.edu.commands.LogStringCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.Printer;
import com.acme.edu.printers.PrinterException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogStringCommandInteractionTest {
    private Decorator stubOneStringSequenceDecorator;
    private Decorator stubMultipleStringSequenceDecorator;
    private String oneStringSequenceDecoratedString = "decorated string when 1 string accumulated";
    private String multipleStringsSequenceDecoratedString = "decorated string when several strings accumulated";
    private String message = "test message";
    private Printer mockPrinter;
    private LogStringCommand sut;

    @Before
    public void setUp() {
        stubOneStringSequenceDecorator = mock(Decorator.class);
        stubMultipleStringSequenceDecorator = mock(Decorator.class);
        mockPrinter = mock(Printer.class);
        sut = new LogStringCommand(mockPrinter, stubOneStringSequenceDecorator, stubMultipleStringSequenceDecorator);
    }

    @Test
    public void shouldNotLogWhenMergeWithNullCommand() throws PrinterException {

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);

        sut.setMessage(message);
        sut.merge(null);

        verify(mockPrinter, times(0)).println(anyString());
    }

    @Test
    public void shouldNotModifyOwnMessageWhenMergeWithNullCommand() throws PrinterException {

        sut.setMessage(message);
        sut.merge(null);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldNotLogWhenMergeWithCommandThatDoesNotHaveMessage() throws PrinterException {
        LogStringCommand stubCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        verify(mockPrinter, times(0)).println(anyString());
    }

    @Test
    public void shouldNotModifyOwnMessageWhenMergeWithCommandThatDoesNotHaveMessage() throws PrinterException {
        LogStringCommand stubCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

}
