package com.acme.edu.integration;

import com.acme.edu.businessexceptions.LoggerException;
import com.acme.edu.clientserver.Client;
import com.acme.edu.clientserver.LogServer;
import com.acme.edu.printers.LogWriterException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.fest.assertions.Assertions.*;


public class ClientServerTest {
    private int port = 5000 + new Random(System.currentTimeMillis()).nextInt(60000);
    private String localhost = "127.0.0.1";
    private List<String> stringsToBeLogged = Arrays.asList("test string");
    private String charset = "UTF-8";

    @Before
    public void setUp() throws IOException {
        Files.deleteIfExists(Paths.get(getFileName()));
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(getFileName()));
    }

    @Test
    public void clientInMainThreadSmokeTest() throws LoggerException, InterruptedException, LogWriterException, IOException {
        LogServer server = new LogServer(port, getFileName());
        Client client = new Client(localhost, port);

        Thread serverThread = new Thread(server);
        serverThread.setDaemon(true);
        serverThread.start();
        Thread.sleep(1000);

        client.sendData(stringsToBeLogged, charset);

        assertThat(FileUtils.readLines(new File(getFileName()), charset)).isEqualTo(stringsToBeLogged);
    }

    private String getFileName() {
        return "server-" + port + ".txt";
    }
}
