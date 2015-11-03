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



    @Test
    public void shouldPrint(){
        Printable mock = mock(ConsolePrinter.class);
        State sut = new IntState(mock);

        sut.log("2");
        sut.flush();

        verify(mock).println("primitive: 2");
    }

}
