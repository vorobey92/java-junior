package com.acme.edu.unit;


import com.acme.edu.exception.CanNotPrintException;
import com.acme.edu.exception.LogException;
import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.Printable;
import com.acme.edu.state.DefaultState;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import com.acme.edu.state.StringState;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StateTest {

    private Printable mock;
    private State sut;

    @Test
    public void shouldLogSumOfPrimitivesWhenYouTryingToLogSequenceOfInts() throws CanNotPrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new IntState(mock);

        sut.log("2");
        sut.log("5");
        sut.log("3");
        sut.flush();

        verify(mock).println("primitive: 10");
    }

    @Test
    public void shouldLogTwoPrimitivesWhenAppearsTypeOverflowWhileSumming() throws CanNotPrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new IntState(mock);

        sut.log("1");
        sut.log("2");
        sut.log("" + (Integer.MAX_VALUE-1));

        verify(mock).println("primitive: 3");
        verify(mock).println("primitive: " + (Integer.MAX_VALUE-1));
    }

    @Test
    public void shouldLogStringAndStringWithCounter() throws CanNotPrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new StringState(mock);

        sut.log("Hello JUnit!");
        sut.log("Hello JUnit!");
        sut.log("Hello JUnit!");
        sut.log("Bomb!");
        sut.log("Batman");
        sut.log("Batman");
        sut.flush();
        sut.log("and robin");
        sut.flush();

        verify(mock).println("string: Hello JUnit! (x3)");
        verify(mock).println("string: Bomb!");
        verify(mock).println("string: Batman (x2)");
        verify(mock).println("string: and robin");
    }



    @Test
    public void shoudLogThatWeWillGiveToThatDefaultState() throws CanNotPrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new DefaultState(mock);

        sut.log("primitive: boolean");
        sut.log("char: a");
        sut.flush();

        verify(mock).print("primitive: boolean");
        verify(mock).print("char: a");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenNullStringIsFirst() throws CanNotPrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new StringState(mock);

        sut.log(null);
        sut.flush();

        verify(mock, times(0)).println("");
    }


}
