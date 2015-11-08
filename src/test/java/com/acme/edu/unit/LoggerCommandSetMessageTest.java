package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.businessexceptions.LoggerException;
import com.acme.edu.commands.Command;
import com.acme.edu.commands.CommandFactory;
import com.acme.edu.printers.LogWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class LoggerCommandSetMessageTest {
    private Logger sut;
    private CommandFactory mockCommandFactory = mock(CommandFactory.class);
    private LogWriter dummyLogWriter = mock(LogWriter.class);
    private Command mockCommand = mock(Command.class);

    @Before
    public void setUp() throws LoggerException {
        sut = new Logger(mockCommandFactory, dummyLogWriter);
    }

    @Test
    public void shouldSetPassedIntAsMessageOfCommandWhenIntIsLogged() throws LoggerException {
        int dummyInt = 1;

        when(mockCommandFactory.createCommand(eq(dummyInt), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyInt);

        verify(mockCommand, times(1)).setMessage(dummyInt + "");
    }

    @Test
    public void shouldSetPassedByteAsMessageOfCommandWhenByteIsLogged() throws LoggerException {
        byte dummyByte = 1;

        when(mockCommandFactory.createCommand(eq(dummyByte), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyByte);

        verify(mockCommand, times(1)).setMessage(dummyByte + "");
    }

    @Test
    public void shouldSetPassedCharAsMessageOfCommandWhenCharIsLogged() throws LoggerException {
        char dummyChar = 1;

        when(mockCommandFactory.createCommand(eq(dummyChar), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyChar);

        verify(mockCommand, times(1)).setMessage(dummyChar + "");
    }

    @Test
    public void shouldSetPassedStringAsMessageOfCommandWhenStringIsLogged() throws LoggerException {
        String dummyString = "test string";

        when(mockCommandFactory.createCommand(eq(dummyString), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyString);

        verify(mockCommand, times(1)).setMessage(dummyString);
    }

    @Test
    public void shouldSetPassedBooleanAsMessageOfCommandWhenBooleanIsLogged() throws LoggerException {
        boolean dummyBoolean = true;

        when(mockCommandFactory.createCommand(eq(dummyBoolean), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyBoolean);

        verify(mockCommand, times(1)).setMessage(dummyBoolean + "");
    }

    @Test
    public void shouldSetPassedObjectAsMessageOfCommandWhenObjectIsLogged() throws LoggerException {
        Object stubObject = mock(Object.class);
        String toString = "test string";

        when(stubObject.toString()).thenReturn(toString);
        when(mockCommandFactory.createCommand(eq(stubObject), anyVararg())).thenReturn(mockCommand);

        sut.log(stubObject);

        verify(mockCommand, times(1)).setMessage(toString);
    }

    @Test
    public void shouldSetPassedIntsAsMessagesOfCommandsWhenIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockCommandFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockCommandFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyIntFirst, dummyIntSecond);

        verify(mockCommand, times(1)).setMessage(dummyIntFirst + "");
        verify(mockCommand, times(1)).setMessage(dummyIntSecond + "");
    }

    @Test
    public void shouldSetPassedIntsAsMessagesOfCommandsRespectivelyWhenIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockCommandFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockCommandFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyIntFirst, dummyIntSecond);

        InOrder inOrder = inOrder(mockCommand);

        inOrder.verify(mockCommand).setMessage(dummyIntFirst + "");
        inOrder.verify(mockCommand).setMessage(dummyIntSecond + "");
    }

    @Test
    public void shouldSetPassedIntsAsMessagesOfCommandsWhenTwoDimensionalIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockCommandFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockCommandFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(new int[][] {{dummyIntFirst}, {dummyIntSecond}});

        verify(mockCommand, times(1)).setMessage(dummyIntFirst + "");
        verify(mockCommand, times(1)).setMessage(dummyIntSecond + "");
    }

    @Test
    public void shouldSetPassedIntsAsMessagesOfCommandsRespectivelyWhenTwoDimensionalIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockCommandFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockCommandFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(new int[][] {{dummyIntFirst}, {dummyIntSecond}});

        InOrder inOrder = inOrder(mockCommand);

        inOrder.verify(mockCommand).setMessage(dummyIntFirst + "");
        inOrder.verify(mockCommand).setMessage(dummyIntSecond + "");
    }

    @Test
    public void shouldSetPassedIntsAsMessagesOfCommandsWhenFourDimensionalIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockCommandFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockCommandFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(new int[][][][] {{{{dummyIntFirst}}}, {{{dummyIntSecond}}}});

        verify(mockCommand, times(1)).setMessage(dummyIntFirst + "");
        verify(mockCommand, times(1)).setMessage(dummyIntSecond + "");
    }

    @Test
    public void shouldSetPassedIntsAsMessagesOfCommandsRespectivelyWhenFourDimensionalIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockCommandFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockCommandFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(new int[][][][] {{{{dummyIntFirst}}}, {{{dummyIntSecond}}}});

        InOrder inOrder = inOrder(mockCommand);

        inOrder.verify(mockCommand).setMessage(dummyIntFirst + "");
        inOrder.verify(mockCommand).setMessage(dummyIntSecond + "");
    }

    @Test
    public void shouldSetPassedStringsAsMessagesOfCommandsWhenStringArrayIsLogged() throws LoggerException {
        String dummyStringFirst = "test string 1";
        String dummyStringSecond = "test string 2";

        when(mockCommandFactory.createCommand(eq(dummyStringFirst), anyVararg())).thenReturn(mockCommand);
        when(mockCommandFactory.createCommand(eq(dummyStringSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyStringFirst, dummyStringSecond);

        verify(mockCommand, times(1)).setMessage(dummyStringFirst + "");
        verify(mockCommand, times(1)).setMessage(dummyStringSecond + "");
    }

    @Test
    public void shouldSetPassedStringsAsMessagesOfCommandsRespectivelyWhenStringArrayIsLogged() throws LoggerException {
        String dummyStringFirst = "test string 1";
        String dummyStringSecond = "test string 2";

        when(mockCommandFactory.createCommand(eq(dummyStringFirst), anyVararg())).thenReturn(mockCommand);
        when(mockCommandFactory.createCommand(eq(dummyStringSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyStringFirst, dummyStringSecond);

        InOrder inOrder = inOrder(mockCommand);

        inOrder.verify(mockCommand).setMessage(dummyStringFirst + "");
        inOrder.verify(mockCommand).setMessage(dummyStringSecond + "");
    }
}
