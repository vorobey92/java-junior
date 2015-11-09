package com.acme.edu.unit;

import com.acme.edu.exception.PrintException;
import com.acme.edu.printer.OutputStreamPrinter;
import com.acme.edu.printer.Printable;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 */
public class ClientServerTest {

    @Test
    @Ignore
    public void testtesttest() throws PrintException {

        Printable net = new OutputStreamPrinter("localhost", 6666, false);

//        for(int i = 0; i < 51; i++)
            net.print("STOP");

    }
}
