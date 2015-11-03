package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.StateFactory;
import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.Printable;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

@Ignore
public class LoggerTest {

    Logger logger;
    Printable mock;

    @Test
    public void should(){
        mock = mock(ConsolePrinter.class);
        logger = new Logger(mock);

        logger.log(5);


    }

}
