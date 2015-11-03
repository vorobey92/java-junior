package com.acme.edu.unit;


import com.acme.edu.Logger;
import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.Printable;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LoggerTest {

    private Printable mock;
    private State sut;


    @Test
    public void shouldPrintOnePrimitiveWhenYouTryingToLogOneNumber(){
        mock = mock(ConsolePrinter.class);
        sut = new IntState(mock);

        sut.log("2");
        sut.flush();

        verify(mock).println("primitive: 2");
    }

    @Test
    public void shouldLogSumOfPrimitivesWhenYouTryingToLogSequenceOfInts(){
        mock = mock(ConsolePrinter.class);
        sut = new IntState(mock);

        sut.log("2");
        sut.log("5");
        sut.log("3");
        sut.flush();

        verify(mock).println("primitive: 10");
    }

    @Test
    public void shouldLogTwoPrimitivesWhenAppearsTypeOverflowWhileSumming(){
        mock = mock(ConsolePrinter.class);
        sut = new IntState(mock);

        sut.log("1");
        sut.log("2");
        sut.log("" + (Integer.MAX_VALUE-1));

        verify(mock).println("primitive: 3");
        verify(mock).println("primitive: " + (Integer.MAX_VALUE-1));
    }

}
