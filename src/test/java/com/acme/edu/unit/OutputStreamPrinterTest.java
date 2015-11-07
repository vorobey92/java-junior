package com.acme.edu.unit;

import com.acme.edu.exception.CanNotPrintException;
import com.acme.edu.printer.OutputStreamPrinter;
import com.acme.edu.printer.Printable;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class OutputStreamPrinterTest {

    private File file;
    private Printable pr;


    @Test
    public void shouldFlushBufferOf50WordsToTXTFile() throws CanNotPrintException {
        file = new File("test.txt");
        pr = new OutputStreamPrinter(file,"UTF-8");
        StringBuilder str = new StringBuilder("");

        for(int i = 0; i < 50; i++) {
            pr.print("str");
            str.append("str");
        }
        pr.print("");
        try {
            assertEquals(str.toString() ,FileUtils.readFileToString(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStreamPrinter.stop();
        file.delete();
    }

    @Test
    public void shouldNoWritesToFile() throws CanNotPrintException, IOException, InterruptedException {

        file = new File("test.txt");
        pr = new OutputStreamPrinter(file,"UTF-8");
        pr.print("str2");

        try {
            assertEquals("", FileUtils.readFileToString(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStreamPrinter.stop();
        file.delete();
    }

    @Test
    public void shouldWriteStringToFileAfterStop() throws CanNotPrintException {
        file = new File("test.txt");
        pr = new OutputStreamPrinter(file,"UTF-8");

        String s = "str";

        pr.print("str");
        OutputStreamPrinter.stop();

        try {
            assertEquals(s, FileUtils.readFileToString(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();
    }

    @Test
    public void shouldWriteStringAndGoToNextLineToFileAfterStop() throws CanNotPrintException {
        file = new File("test.txt");
        pr = new OutputStreamPrinter(file,"UTF-8");

        String s = "str" + System.lineSeparator();

        pr.println("str");
        OutputStreamPrinter.stop();

        try {
            assertEquals(s, FileUtils.readFileToString(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();
    }

    @Test(expected = CanNotPrintException.class)
    public void shouldThrowCannotPrintExceptionWhenCreatingObject() throws CanNotPrintException {

        File error = new File("");
        Printable printer = new OutputStreamPrinter(error, "UTF-8");
        OutputStreamPrinter.stop();
    }

    @Test(expected = CanNotPrintException.class)
    public void shouldThrowCannotPrintExceptionWhenCreatingObjectBecauseWrongCharset() throws CanNotPrintException {

        File error = new File("");
        Printable printer = new OutputStreamPrinter(error, "");
        OutputStreamPrinter.stop();
    }

    @Test(expected = CanNotPrintException.class)
    public void shouldThrowCannotPrintExceptionWhenTryingToStopAlreadyStopedWriting() throws CanNotPrintException {
        OutputStreamPrinter.stop();
        OutputStreamPrinter.stop();
    }
}
