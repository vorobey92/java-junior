package com.acme.edu.integration;

import com.acme.edu.printers.FileBufferWriter;
import com.acme.edu.printers.LogWriterException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.*;

public class FileBufferWriterTest {
    private String fileName = "test-file.txt";
    private String charset = "UTF-8";
    private List<String> testBuffer;

    @After
    public void tearDown() throws IOException {
        deleteFileIfItExists(fileName);
    }

    @Before
    public void setUp() throws IOException {
        deleteFileIfItExists(fileName);
        testBuffer = Arrays.asList("test string1", "test string 2", "test string 3");
    }

    @Test
    public void shouldCreateFileWhenFileDoesNotExist() throws LogWriterException {
        FileBufferWriter fileBufferWriter = new FileBufferWriter(fileName, charset);

        fileBufferWriter.writeBuffer(testBuffer);

        assertThat(Files.exists(Paths.get(fileName))).isTrue();
    }

    @Test
    public void shouldWriteBufferToFileWhenFileDoesNotExist() throws LogWriterException, IOException {
        FileBufferWriter fileBufferWriter = new FileBufferWriter(fileName, charset);

        fileBufferWriter.writeBuffer(testBuffer);

        assertThat(FileUtils.readLines(new File(fileName), charset)).isEqualTo(testBuffer);
    }

    @Test
    public void shouldAppendBufferToFileWhenFileDoesNotExist() throws LogWriterException, IOException {
        FileBufferWriter fileBufferWriter = new FileBufferWriter(fileName, charset);
        String testString = "test string";
        List<String> expected = new ArrayList<String>() {
            {
                add(testString);
                addAll(testBuffer);
            }
        };

        FileUtils.writeStringToFile(new File(fileName), testString + System.lineSeparator(), charset);
        fileBufferWriter.writeBuffer(testBuffer);


        assertThat(FileUtils.readLines(new File(fileName), charset)).isEqualTo(expected);
    }

    private static void deleteFileIfItExists(String fileName) throws IOException {
        Files.deleteIfExists(Paths.get(fileName));
    }
}
