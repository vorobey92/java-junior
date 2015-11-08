package com.acme.edu.integration;

import com.acme.edu.Logger;
import com.acme.edu.businessexceptions.LoggerException;
import com.acme.edu.commands.CommandFactoryImpl;
import com.acme.edu.printers.FileLogWriter;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.fest.assertions.Assertions.*;

@Ignore
public class FileLogWriterTest {
    private String fileName = "test-file";
    private String charSet = "UTF-8";

    @After
    public void tearDown() {
        deleteFileIfItExists(fileName);
    }

    @Test
    public void shouldCreateFileWhenBufferIsFullAndFileDoesNotExist() throws LoggerException {
        deleteFileIfItExists(fileName);

        Logger logger = new Logger(
                new CommandFactoryImpl(), new FileLogWriter(fileName, charSet)
        );

        for (int i = 0; i < 50; ++i) {
            logger.log(true);
        }

        assertThat(new File(fileName).exists()).isTrue();
    }

    private static void deleteFileIfItExists(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            assertThat(file.delete()).isTrue();
        }
    }
}
