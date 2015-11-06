package com.acme.edu.unit;

import com.acme.edu.commands.LogStringCommand;
import com.acme.edu.commands.LogUnaccumulatedTypeCommand;
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

public class LogUnaccumulatedTypeCommandInteractionTest {
    private Decorator stubDecorator;
    private String decoratedString = "decorated string";
    private String message = "test message";
    private Printer mockPrinter;
    private LogUnaccumulatedTypeCommand sut;

    @Before
    public void setUp() {
        mockPrinter = mock(Printer.class);
        stubDecorator = mock(Decorator.class);
        sut = new LogUnaccumulatedTypeCommand(mockPrinter, stubDecorator);
    }

    @Test
    public void shouldLogWhenMergeWithNullCommand() throws PrinterException {
        when(stubDecorator.decorate(message)).thenReturn(decoratedString);

        sut.setMessage(message);
        sut.merge(null);

        verify(mockPrinter, times(1)).println(decoratedString);
    }

    @Test
    public void shouldLogWhenMergeWithCommandThatDoesNotHaveMessage() throws PrinterException {
        LogUnaccumulatedTypeCommand stubCommand = mock(LogUnaccumulatedTypeCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        verify(mockPrinter, times(1)).println(decoratedString);
    }

    @Test
    public void shouldNotLogOldMessageWhenMergeWithAnotherCommand() throws PrinterException {
        String oldMessage = "old message";
        LogUnaccumulatedTypeCommand mockCommand = mock(LogUnaccumulatedTypeCommand.class);

        when(stubDecorator.decorate(message)).thenReturn(decoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).execute();
    }
}