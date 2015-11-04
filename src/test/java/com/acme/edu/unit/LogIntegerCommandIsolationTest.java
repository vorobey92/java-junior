package com.acme.edu.unit;

import com.acme.edu.commands.LogIntegerCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.Printer;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.fest.assertions.Assertions.*;

public class LogIntegerCommandIsolationTest {
    private Printer mockPrinter;
    private LogIntegerCommand sut;
    private Decorator stubDecorator;
    private String decoratedString;
    private String message;

    @Before
    public void setUp() {
        message = "message";
        decoratedString = "decorated string ";
        mockPrinter = mock(Printer.class);
        stubDecorator = mock(Decorator.class);
        sut = new LogIntegerCommand(mockPrinter, stubDecorator, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    @Test
    public void shouldLogWhenHasMessage() {
        when(stubDecorator.decorate(message)).thenReturn(decoratedString);

        sut.setMessage(message);
        sut.execute();

        verify(mockPrinter, times(1)).println(decoratedString);
    }

    @Test
    public void shouldSetMessageToNullAfterLogging() {
        sut.setMessage(message);
        sut.execute();

        assertThat(sut.getMessage()).isEqualTo(null);
    }

    @Test
    public void shouldNotLogWhenHasNullMessage() {
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
