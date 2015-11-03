package com.acme.edu.unit;


import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.Printable;
import com.acme.edu.state.EmptyState;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import com.acme.edu.state.StringState;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class StateTest {

    private Printable mock;
    private State sut;

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

    @Test
    public void shouldLogStringAndStringWithCounter(){
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
    public void shoudLogThatWeWillGiveToThatEmptyState(){
        mock = mock(ConsolePrinter.class);
        sut = new EmptyState(mock);

        sut.log("primitive: boolean");
        sut.log("char: a");
        sut.flush();

        verify(mock).print("primitive: boolean");
        verify(mock).print("char: a");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullStringIsFirst(){
        mock = mock(ConsolePrinter.class);
        sut = new StringState(mock);

        sut.log(null);
        sut.flush();

    }


}
