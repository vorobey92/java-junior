package com.acme.edu.unit;

import com.acme.edu.commands.LogStringCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.Printer;
import com.acme.edu.printers.PrinterException;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
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

    @Test
     public void shouldLogOldMessageWhenNewMessageDiffersFromOldMessage() throws PrinterException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(1)).execute();
    }

    @Test
    public void shouldNotModifyOldMessageBeforeLoggingWhenNewMessageDiffersFromOldMessage() throws PrinterException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).setMessage(any());
    }

    @Test
    public void shouldNotModifyNewMessageWhenNewMessageDiffersFromOldMessage() throws PrinterException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldNotLogNewMessageWhenNewMessageDiffersFromOldMessage() throws PrinterException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockPrinter, times(0)).println(anyString());
    }

    @Test
    public void shouldNotModifyCounterWhenNewMessageDiffersFromOldMessage() throws PrinterException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        assertThat(sut.getLengthOfStringsSequence()).isEqualTo(1);
    }

    @Test
    public void shouldNotLogOldMessageWhenNewMessageEqualsOldMessage() throws PrinterException {
        String oldMessage = message;
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).execute();
    }

    @Test
    public void shouldNotModifyNewMessageWhenNewMessageEqualsOldMessage() throws PrinterException {
        String oldMessage = message;
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldNotLogNewMessageWhenNewMessageEqualsOldMessage() throws PrinterException {
        String oldMessage = message;
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockPrinter, times(0)).println(anyString());
    }

    @Test
    public void shouldIncrementCounterWhenNewMessageEqualsOldMessage() throws PrinterException {
        String oldMessage = message;
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);
        when(mockCommand.getLengthOfStringsSequence()).thenReturn(1);

        sut.setMessage(message);
        sut.merge(mockCommand);

        assertThat(sut.getLengthOfStringsSequence()).isEqualTo(2);
    }

}
