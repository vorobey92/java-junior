package com.acme.edu.unit;

import com.acme.edu.exception.PrintException;
import com.acme.edu.printer.OutputStreamPrinter;
import com.acme.edu.printer.Printable;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class OutputStreamPrinterTest {

    File file ;
    Printable pr;

    @Test
    public void shouldFlushBufferOf50WordsToTXTFileWhenBufferizationActivated() throws PrintException {
        file = new File("test.txt");
        pr = new OutputStreamPrinter(file,"UTF-8",true);
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
        file.delete();
    }

    @Test
    public void shouldNoWritesToFileWhenBufferizationActivated() throws PrintException, IOException, InterruptedException {

        file = new File("test.txt");
        file.createNewFile();
        Printable pr = new OutputStreamPrinter(file,"UTF-8", true);
        pr.print("str2");

        try {
            assertEquals("", FileUtils.readFileToString(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        while ( i++ < 50)
            pr.print("");
        file.delete();
    }

    @Test
    public void shouldWrite1MessageToFileWhenBufferizationDeactivated() throws PrintException, IOException, InterruptedException {

        file = new File("test.txt");
        file.createNewFile();
        Printable pr = new OutputStreamPrinter(file,"UTF-8", false);
        pr.print("str3");

        try {
            assertEquals("str3", FileUtils.readFileToString(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.delete();
    }


    @Test

    public void shouldWriteStringAndThenTerminateTheLineWhenBufferizationOff() throws PrintException, IOException {
        file = new File("test.txt");
        file.createNewFile();
        pr = new OutputStreamPrinter(file,"UTF-8",false);

        String s = "str" + System.lineSeparator();

        pr.println("str");


        try {
            assertEquals(s, FileUtils.readFileToString(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();
    }

    @Test(expected = PrintException.class)
    public void shouldThrowPrintExceptionWhenCreatingObject() throws PrintException {

        File error = new File("");
        Printable pr = new OutputStreamPrinter(error, "UTF-8", false);

        pr.print("3");
    }

    @Test(expected = PrintException.class)
    public void shouldThrowPrintExceptionWhenCreatingObjectBecauseWrongCharset() throws PrintException {

        File error = new File("");
        Printable pr = new OutputStreamPrinter(error, "", false);

        pr.print("");
    }

    @Test
    public void shouldDontThrowCannotPrintExceptionWhenTryingToWriteASequenceOfMessagesWhileBufferizationOff() throws PrintException {
        file = new File("test.txt");
        Printable pr = new OutputStreamPrinter(file,"UTF-8", false);
        pr.print("str1");
        pr.print("str2");
        pr.print("str3");
        file.delete();
    }



}



