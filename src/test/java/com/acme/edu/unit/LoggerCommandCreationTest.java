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

public class LoggerCommandCreationTest {
    private Logger sut;
    private CommandFactory mockFactory = mock(CommandFactory.class);
    private LogWriter dummyLogWriter = mock(LogWriter.class);
    private Command mockCommand = mock(Command.class);

    @Before
    public void setUp() throws LoggerException {
        sut = new Logger(mockFactory, dummyLogWriter);
    }

    @Test
    public void shouldCreateIntCommandWhenIntIsLogged() throws LoggerException {
        int dummyInt = 2;

        when(mockFactory.createCommand(eq(dummyInt), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyInt);

        verify(mockFactory, times(1)).createCommand(eq(dummyInt), anyVararg());
    }

    @Test
    public void shouldCreateByteCommandWhenByteIsLogged() throws LoggerException {
        byte dummyByte = 2;

        when(mockFactory.createCommand(eq(dummyByte), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyByte);

        verify(mockFactory, times(1)).createCommand(eq(dummyByte), anyVararg());
    }

    @Test
    public void shouldCreateCharCommandWhenCharIsLogged() throws LoggerException {
        char dummyChar = 'c';

        when(mockFactory.createCommand(eq(dummyChar), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyChar);

        verify(mockFactory, times(1)).createCommand(eq(dummyChar), anyVararg());
    }

    @Test
    public void shouldCreateStringCommandWhenStringIsLogged() throws LoggerException {
        String dummyString = "test string";

        when(mockFactory.createCommand(eq(dummyString), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyString);

        verify(mockFactory, times(1)).createCommand(eq(dummyString), anyVararg());
    }

    @Test
    public void shouldCreateBooleanCommandWhenBooleanIsLogged() throws LoggerException {
        boolean dummyBoolean = false;

        when(mockFactory.createCommand(eq(dummyBoolean), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyBoolean);

        verify(mockFactory, times(1)).createCommand(eq(dummyBoolean), anyVararg());
    }

    @Test
    public void shouldCreateObjectCommandWhenObjectIsLogged() throws LoggerException {
        Object dummyMessage = new Object();

        when(mockFactory.createCommand(eq(dummyMessage), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyMessage);

        verify(mockFactory, times(1)).createCommand(eq(dummyMessage), anyVararg());
    }

    @Test
    public void shouldCreateIntCommandsWhenIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyIntFirst, dummyIntSecond);

        verify(mockFactory, times(1)).createCommand(eq(dummyIntFirst), anyVararg());
        verify(mockFactory, times(1)).createCommand(eq(dummyIntSecond), anyVararg());
    }

    @Test
    public void shouldCreateIntCommandsRespectivelyWhenIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyIntFirst, dummyIntSecond);

        InOrder inOrder = inOrder(mockFactory);

        inOrder.verify(mockFactory).createCommand(eq(dummyIntFirst), anyVararg());
        inOrder.verify(mockFactory).createCommand(eq(dummyIntSecond), anyVararg());
    }

    @Test
    public void shouldCreateIntCommandsWhenTwoDimensionalIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(new int[][] {{dummyIntFirst}, {dummyIntSecond}});

        verify(mockFactory, times(1)).createCommand(eq(dummyIntFirst), anyVararg());
        verify(mockFactory, times(1)).createCommand(eq(dummyIntSecond), anyVararg());
    }

    @Test
    public void shouldCreateIntCommandsRespectivelyWhenTwoDimensionalIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(new int[][] {{dummyIntFirst}, {dummyIntSecond}});

        InOrder inOrder = inOrder(mockFactory);

        inOrder.verify(mockFactory).createCommand(eq(dummyIntFirst), anyVararg());
        inOrder.verify(mockFactory).createCommand(eq(dummyIntSecond), anyVararg());
    }

    @Test
    public void shouldCreateIntCommandsWhenFourDimensionalIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(new int[][][][] {{{{dummyIntFirst}}}, {{{dummyIntSecond}}}});

        verify(mockFactory, times(1)).createCommand(eq(dummyIntFirst), anyVararg());
        verify(mockFactory, times(1)).createCommand(eq(dummyIntSecond), anyVararg());
    }

    @Test
    public void shouldCreateIntCommandsRespectivelyWhenFourDimensionalIntArrayIsLogged() throws LoggerException {
        int dummyIntFirst = 1;
        int dummyIntSecond = 2;

        when(mockFactory.createCommand(eq(dummyIntFirst), anyVararg())).thenReturn(mockCommand);
        when(mockFactory.createCommand(eq(dummyIntSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(new int[][][][] {{{{dummyIntFirst}}}, {{{dummyIntSecond}}}});

        InOrder inOrder = inOrder(mockFactory);

        inOrder.verify(mockFactory).createCommand(eq(dummyIntFirst), anyVararg());
        inOrder.verify(mockFactory).createCommand(eq(dummyIntSecond), anyVararg());
    }

    @Test
    public void shouldCreateStringCommandsWhenStringArrayIsLogged() throws LoggerException {
        String dummyStringFirst = "test string 1";
        String dummyStringSecond = "test string 2";

        when(mockFactory.createCommand(eq(dummyStringFirst), anyVararg())).thenReturn(mockCommand);
        when(mockFactory.createCommand(eq(dummyStringSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyStringFirst, dummyStringSecond);

        verify(mockFactory, times(1)).createCommand(eq(dummyStringFirst), anyVararg());
        verify(mockFactory, times(1)).createCommand(eq(dummyStringFirst), anyVararg());
    }

    @Test
    public void shouldCreateStringCommandsRespectivelyWhenStringArrayIsLogged() throws LoggerException {
        String dummyStringFirst = "test string 1";
        String dummyStringSecond = "test string 2";

        when(mockFactory.createCommand(eq(dummyStringFirst), anyVararg())).thenReturn(mockCommand);
        when(mockFactory.createCommand(eq(dummyStringSecond), anyVararg())).thenReturn(mockCommand);

        sut.log(dummyStringFirst, dummyStringSecond);

        InOrder inOrder = inOrder(mockFactory);

        inOrder.verify(mockFactory).createCommand(eq(dummyStringFirst), anyVararg());
        inOrder.verify(mockFactory).createCommand(eq(dummyStringSecond), anyVararg());
    }
}
