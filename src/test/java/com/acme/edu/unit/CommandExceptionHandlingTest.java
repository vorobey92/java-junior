package com.acme.edu.unit;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.commands.LogUnaccumulatedTypeCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;
import com.acme.edu.printers.LogWriterException;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.Fail.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandExceptionHandlingTest {
    private Decorator stubDecorator;
    private String decoratedString = "decorated string";
    private String message = "test message";
    private LogUnaccumulatedTypeCommand sut;

    @Before
    public void setUp() {
        stubDecorator = mock(Decorator.class);
    }

    @Test(expected = LoggingException.class)
    public void shouldThrowLoggingExceptionWhenLogWriterExceptionIsThrownWhileExecuting() throws LogWriterException, LoggingException {
        LogWriter mockLogWriter = mock(LogWriter.class);
        sut = new LogUnaccumulatedTypeCommand(stubDecorator, mockLogWriter);

        doThrow(new LogWriterException()).when(mockLogWriter).writeLine(anyString());
        when(stubDecorator.decorate(anyString())).thenReturn(decoratedString);

        sut.setMessage(message);
        sut.execute();
    }

    @Test
    public void shouldStoreOneLogWriterExceptionsInLoggingExceptionWhenOneLogWriterExceptionsAreThrownWhileExecuting() throws LogWriterException {
        LogWriter mockLogWriter = mock(LogWriter.class);
        LogWriterException dummyException = new LogWriterException();
        sut = new LogUnaccumulatedTypeCommand(stubDecorator, mockLogWriter);

        doThrow(dummyException).when(mockLogWriter).writeLine(anyString());
        when(stubDecorator.decorate(anyString())).thenReturn(decoratedString);

        sut.setMessage(message);
        try {
            sut.execute();
            fail("LoggingExceptions was not thrown");
        } catch (LoggingException e) {
            assertThat(e.getCauses()).containsOnly(dummyException);
        }
    }

    @Test
    public void shouldStoreTwoLogWriterExceptionsInLoggingExceptionWhenTwoLogWriterExceptionsAreThrownWhileExecuting() throws LogWriterException {
        LogWriter mockLogWriterFirst = mock(LogWriter.class);
        LogWriter mockLogWriterSecond = mock(LogWriter.class);
        LogWriterException dummyExceptionFirst = new LogWriterException();
        LogWriterException dummyExceptionSecond = new LogWriterException();
        sut = new LogUnaccumulatedTypeCommand(stubDecorator, mockLogWriterFirst, mockLogWriterSecond);

        doThrow(dummyExceptionFirst).when(mockLogWriterFirst).writeLine(anyString());
        doThrow(dummyExceptionSecond).when(mockLogWriterSecond).writeLine(anyString());
        when(stubDecorator.decorate(anyString())).thenReturn(decoratedString);

        sut.setMessage(message);
        try {
            sut.execute();
            fail("LoggingExceptions was not thrown");
        } catch (LoggingException e) {
            assertThat(e.getCauses()).containsOnly(dummyExceptionFirst, dummyExceptionSecond);
        }
    }
}
