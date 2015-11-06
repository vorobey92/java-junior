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

public class LogStringCommandIsolationTest {
    private Decorator stubOneStringSequenceDecorator;
    private Decorator stubMultipleStringSequenceDecorator;
    private String oneStringSequenceDecoratedString = "decorated string when 1 string accumulated";
    private String multipleStringsSequenceDecoratedString = "decorated string when several strings accumulated";
    private String message = "test message";
    private Printer mockPrinter;
    private LogStringCommand sut;

    @Before
    public void setUp() {
        mockPrinter = mock(Printer.class);
        stubOneStringSequenceDecorator = mock(Decorator.class);
        stubMultipleStringSequenceDecorator = mock(Decorator.class);
        sut = new LogStringCommand(mockPrinter, stubOneStringSequenceDecorator, stubMultipleStringSequenceDecorator);
    }

    @Test
    public void shouldLogWhenAccumulatedNumberIsOne() throws PrinterException {
        when(stubOneStringSequenceDecorator.decorate(message, 1)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message, 1)).thenReturn(multipleStringsSequenceDecoratedString);

        sut.setMessage(message);
        sut.execute();

        verify(mockPrinter, times(1)).println(oneStringSequenceDecoratedString);
    }

    @Test
    public void shouldLogWhenAccumulatedNumberIsTwo() throws PrinterException {
        when(stubOneStringSequenceDecorator.decorate(message, 2)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message, 2)).thenReturn(multipleStringsSequenceDecoratedString);

        sut.setMessage(message);
        sut.setLengthOfStringsSequence(2);
        sut.execute();

        verify(mockPrinter, times(1)).println(multipleStringsSequenceDecoratedString);
    }

    @Test
    public void shouldSetMessageToNullAfterLogging() throws PrinterException {
        sut.setMessage(message);
        sut.execute();

        assertThat(sut.getMessage()).isEqualTo(null);
    }

    @Test
    public void shouldNotLogWhenHasNullMessage() throws PrinterException {
        when(stubOneStringSequenceDecorator.decorate(any())).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(any())).thenReturn(multipleStringsSequenceDecoratedString);

        sut.setMessage(null);
        sut.execute();

        verify(mockPrinter, times(0)).println(anyString());
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