package com.acme.edu.unit;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.commands.Command;
import com.acme.edu.commands.LogStringCommand;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.writers.LogWriter;
import com.acme.edu.writers.LogWriterException;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogStringCommandInteractionTest {
    private Decorator stubOneStringSequenceDecorator;
    private Decorator stubMultipleStringSequenceDecorator;
    private String oneStringSequenceDecoratedString = "decorated string when 1 string accumulated";
    private String multipleStringsSequenceDecoratedString = "decorated string when several strings accumulated";
    private String message = "test message";
    private LogWriter mockLogWriter;
    private LogStringCommand sut;

    @Before
    public void setUp() {
        stubOneStringSequenceDecorator = mock(Decorator.class);
        stubMultipleStringSequenceDecorator = mock(Decorator.class);
        mockLogWriter = mock(LogWriter.class);
        sut = new LogStringCommand(stubOneStringSequenceDecorator, stubMultipleStringSequenceDecorator, mockLogWriter);
    }

    @Test
    public void shouldNotLogWhenMergeWithNullCommand() throws LogWriterException, LoggingException {

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);

        sut.setMessage(message);
        sut.merge(null);

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

    @Test
    public void shouldNotModifyOwnMessageWhenMergeWithNullCommand() throws LogWriterException, LoggingException {

        sut.setMessage(message);
        sut.merge(null);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldNotLogWhenMergeWithCommandThatDoesNotHaveMessage() throws LogWriterException, LoggingException {
        LogStringCommand stubCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

    @Test
    public void shouldNotModifyOwnMessageWhenMergeWithCommandThatDoesNotHaveMessage() throws LogWriterException, LoggingException {
        LogStringCommand stubCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(stubCommand.getMessage()).thenReturn(null);

        sut.setMessage(message);
        sut.merge(stubCommand);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
     public void shouldLogOldMessageWhenNewMessageDiffersFromOldMessage() throws LogWriterException, LoggingException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(1)).execute();
    }

    @Test
    public void shouldNotModifyOldMessageBeforeLoggingWhenNewMessageDiffersFromOldMessage() throws LogWriterException, LoggingException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).setMessage(any());
    }

    @Test
    public void shouldNotModifyNewMessageWhenNewMessageDiffersFromOldMessage() throws LogWriterException, LoggingException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldNotLogNewMessageWhenNewMessageDiffersFromOldMessage() throws LogWriterException, LoggingException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

    @Test
    public void shouldNotModifyCounterWhenNewMessageDiffersFromOldMessage() throws LogWriterException, LoggingException {
        String oldMessage = "old message";
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        assertThat(sut.getLengthOfStringsSequence()).isEqualTo(1);
    }

    @Test
    public void shouldNotLogOldMessageWhenNewMessageEqualsOldMessage() throws LogWriterException, LoggingException {
        String oldMessage = message;
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockCommand, times(0)).execute();
    }

    @Test
    public void shouldNotModifyNewMessageWhenNewMessageEqualsOldMessage() throws LogWriterException, LoggingException {
        String oldMessage = message;
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        assertThat(sut.getMessage()).isEqualTo(message);
    }

    @Test
    public void shouldNotLogNewMessageWhenNewMessageEqualsOldMessage() throws LogWriterException, LoggingException {
        String oldMessage = message;
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);

        sut.setMessage(message);
        sut.merge(mockCommand);

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

    @Test
    public void shouldIncrementCounterWhenNewMessageEqualsOldMessage() throws LogWriterException, LoggingException {
        String oldMessage = message;
        LogStringCommand mockCommand = mock(LogStringCommand.class);

        when(stubOneStringSequenceDecorator.decorate(message)).thenReturn(oneStringSequenceDecoratedString);
        when(stubMultipleStringSequenceDecorator.decorate(message)).thenReturn(multipleStringsSequenceDecoratedString);
        when(mockCommand.getMessage()).thenReturn(oldMessage);
        when(mockCommand.getLengthOfStringsSequence()).thenReturn(1);

        sut.setMessage(message);
        sut.merge(mockCommand);

        assertThat(sut.getLengthOfStringsSequence()).isEqualTo(2);
    }

    @Test
    public void shouldExecuteOldCommandOfAnotherClassWhileMerging() throws LogWriterException, LoggingException {
        Command mockOldCommand = mock(Command.class);
        String oldCommandMessage = "old command message";

        when(mockOldCommand.getMessage()).thenReturn(oldCommandMessage);

        sut.setMessage(message);
        sut.merge(mockOldCommand);

        verify(mockOldCommand, times(1)).execute();
    }

    @Test
    public void shouldNotLogWhileMergingWithOldCommandOfAnotherClass() throws LogWriterException, LoggingException {
        Command mockOldCommand = mock(Command.class);
        String oldCommandMessage = "old command message";

        when(mockOldCommand.getMessage()).thenReturn(oldCommandMessage);

        sut.setMessage(message);
        sut.merge(mockOldCommand);

        verify(mockLogWriter, times(0)).writeLine(anyString());
    }

}
