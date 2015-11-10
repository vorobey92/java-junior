package com.acme.edu.unit;

import com.acme.edu.Server;
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
    public void testtesttest() throws PrintException, InterruptedException {

        Server server = new Server(9123);

        Thread.sleep(2500);

        Printable net = new OutputStreamPrinter("localhost", 9123, true);

        for (int i = 0; i < 25; i++) {
            net.println("ServeClient : " + i);
        }
        for (int i = 0; i < 26; i++) {
            net.println("ERROR"+i);
        }
        Thread.sleep(1000);
        net.println("STOP");
    }

}

