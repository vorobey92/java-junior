package com.acme.edu.unit;


import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exception.PrintException;
import com.acme.edu.exception.LogException;
import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.OutputStreamPrinter;
import com.acme.edu.printer.Printable;
import com.acme.edu.state.DefaultState;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import com.acme.edu.state.StringState;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StateTest implements SysoutCaptureAndAssertionAbility {

    private Printable mock;
    private State sut;

    @Test
    public void shouldLogSumOfPrimitivesWhenYouTryingToLogSequenceOfInts() throws PrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new IntState(mock);

        sut.log("2");
        sut.log("5");
        sut.log("3");
        sut.flush();

        verify(mock).println("primitive: 10");
    }

    @Test
    public void shouldLogTwoPrimitivesWhenAppearsTypeOverflowWhileSumming() throws PrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new IntState(mock);

        sut.log("1");
        sut.log("2");
        sut.log("" + (Integer.MAX_VALUE-1));

        verify(mock).println("primitive: 3");
        verify(mock).println("primitive: " + (Integer.MAX_VALUE-1));
    }

    @Test
    public void shouldLogTwoPrimitivesWhenAppearsTypeOverflowWhileSubtracting() throws PrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new IntState(mock);

        sut.log("-1");
        sut.log("-1");
        sut.log("" + (Integer.MIN_VALUE));

        verify(mock).println("primitive: -2");
        verify(mock).println("primitive: " + (Integer.MIN_VALUE));
    }

    @Test
    public void shouldLogStringAndStringWithCounter() throws PrintException, LogException {
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
    public void shoudLogThatWeWillGiveToThatDefaultState() throws PrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new DefaultState(mock);

        sut.log("primitive: boolean");
        sut.log("char: a");
        sut.flush();

        verify(mock).print("primitive: boolean");
        verify(mock).print("char: a");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenNullStringIsFirst() throws PrintException, LogException {
        mock = mock(ConsolePrinter.class);
        sut = new StringState(mock);

        sut.log(null);
        sut.flush();

        verify(mock, times(0)).println("");
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldTryToWriteToOtherPrintersAlthoughThereAreExceptionFromOneOfPrinters() throws IOException, PrintException {
        Printable mockCons = mock(ConsolePrinter.class);
        Printable mockFile = mock(OutputStreamPrinter.class);
        sut = new StringState(mockCons, mockFile);

        doThrow(PrintException.class).when(mockFile).println(anyString());
        doThrow(PrintException.class).when(mockCons).println(anyString());

        // don't know how to show that there will be exception like
        // StateException([PrintException, PrintException])
        // but it will
        exception.expect(PrintException.class);
        sut.log("a");
        sut.flush();

    }


}
