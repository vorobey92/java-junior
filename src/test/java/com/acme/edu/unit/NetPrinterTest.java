package com.acme.edu.unit;

import com.acme.edu.Server;
import com.acme.edu.exception.CanNotPrintException;
import com.acme.edu.printer.NetPrinter;
import com.acme.edu.printer.Printable;
import org.junit.Test;

import java.io.File;

/**
 *
 */
public class NetPrinterTest {

    @Test
    public void testtesttest() throws CanNotPrintException {
        Thread t = new Thread(new Server());
        t.start();
        Printable net = new NetPrinter("127.0.0.1", 6666);

        for(int i = 0; i < 140; i++)
            net.println("sdf");

        NetPrinter.stop();
    }
}
