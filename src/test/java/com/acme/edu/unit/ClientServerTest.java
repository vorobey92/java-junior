package com.acme.edu.unit;

import com.acme.edu.exception.CanNotPrintException;
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
    public void testtesttest() throws CanNotPrintException {

        Printable net = new OutputStreamPrinter("127.0.0.1", 6666);

        for(int i = 0; i < 140; i++)
            net.println("sdf");

        OutputStreamPrinter.stop();
    }
}
