package com.acme.edu.unit;

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

public class LogUnaccumulatedTypeCommandIsolationTest {
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
    public void shouldLogWhenHasMesage() throws PrinterException {
        when(stubDecorator.decorate(message)).thenReturn(decoratedString);

        sut.setMessage(message);
        sut.execute();

        verify(mockPrinter, times(1)).println(decoratedString);
    }

    @Test
    public void shouldSetMessageToNullAfterLogging() throws PrinterException {
        sut.setMessage(message);
        sut.execute();

        assertThat(sut.getMessage()).isEqualTo(null);
    }

    @Test
    public void shouldNotLogWhenHasNullMessage() throws PrinterException {
        when(stubDecorator.decorate(any())).thenReturn(decoratedString);

        sut.setMessage(null);
        sut.execute();

        verify(mockPrinter, times(0)).println(anyString());
    }

    @Test
    public void shouldHaveNullMessageRightAfterCreation() {
        assertThat(sut.getMessage()).isEqualTo(null);
    }
}
