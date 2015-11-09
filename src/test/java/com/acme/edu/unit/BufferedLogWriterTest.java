package com.acme.edu.unit;

import com.acme.edu.printers.BufferWriter;
import com.acme.edu.printers.BufferedLogWriter;
import com.acme.edu.printers.LogWriterException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.fest.assertions.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BufferedLogWriterTest {
    private String dummyMessage = "test message";
    private String highPriorityMessage =
            "high priority message contains " + BufferedLogWriter.HIGH_PRIORITY_SUBSTRING + " substring";
    private BufferedLogWriter sut;
    private ArgumentCaptor<List> argument;
    private BufferWriter mockBufferWriter;

    @Before
    public void setUp() {
        mockBufferWriter = mock(BufferWriter.class);
        argument = ArgumentCaptor.forClass(List.class);
        sut = new BufferedLogWriter(mockBufferWriter);
    }

    @Test
    public void shouldPlaceHighPriorityMessageInTheBeginningOfTheBufferWhenItWasLastMessage() throws LogWriterException {
        for (int i = 0; i < BufferedLogWriter.BUFFER_SIZE - 1; ++i) {
            sut.writeLine(dummyMessage);
        }
        sut.writeLine(highPriorityMessage);

        verify(mockBufferWriter).writeBuffer(argument.capture());
        assertThat(argument.getValue().get(0)).isEqualTo(highPriorityMessage);
    }

    @Test
    public void shouldPlaceHighPriorityMessageInTheBeginningOfTheBufferWhenItWasFirstMessage() throws LogWriterException {
        sut.writeLine(highPriorityMessage);
        for (int i = 1; i < BufferedLogWriter.BUFFER_SIZE; ++i) {
            sut.writeLine(dummyMessage);
        }

        verify(mockBufferWriter).writeBuffer(argument.capture());
        assertThat(argument.getValue().get(0)).isEqualTo(highPriorityMessage);
    }
}
