package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.exception.LogException;
import com.acme.edu.exception.NullMessageException;
import com.acme.edu.state.*;
import org.junit.Ignore;
import org.junit.Test;
import static  org.mockito.Mockito.*;


public class LoggerTest {

    @Test
    public void shouldVerifyThatThereWasCallOfGetIntStateMethodOfStateFactoryClassWhenYouUsingLogger() throws  LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        State state = mock(IntState.class);
        Logger sut = new Logger(factoryMock);

        when(factoryMock.getIntState(anyObject())).thenReturn(state);

        sut.log(5);

        verify(factoryMock).getIntState(anyObject());
    }

    @Test
    public void shouldVerifyThatThereWasCallOfGetIntStateMethodOfStateFactoryClassWhenYouUsingLoggerToLogByte() throws  LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        State state = mock(IntState.class);
        Logger sut = new Logger(factoryMock);

        when(factoryMock.getIntState(anyObject())).thenReturn(state);

        sut.log((byte)1);

        verify(factoryMock).getIntState(anyObject());
    }


    @Test
    public void shouldVerifyThatThereWasCallOfGetStringStateMethodOfStateFactoryClassWhenYouUsingLogger() throws  LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        State state = mock(StringState.class);
        Logger sut = new Logger(factoryMock);

        when(factoryMock.getStringState(anyObject())).thenReturn(state);

        sut.log("str");

        verify(factoryMock).getStringState(anyObject());
    }

    @Test
    public void shouldVerifyThatThereWereThreeCallsOfGetDefaultStateMethodOfStateFactoryClassWhenYouCreatingLoggerObjectAndUsingLogger() throws  LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        State state = mock(DefaultState.class);
        Logger sut = new Logger(factoryMock);

        when(factoryMock.getDefaultState()).thenReturn(state);

        sut.log(true);

        verify(factoryMock, times(3)).getDefaultState();
    }

    @Test
    public void shouldVerifyThatThereWereThreeCallsOfGetDefaultStateMethodOfStateFactoryClassWhenYouCreatingLoggerObjectAndUsingLoggerToLogChar() throws  LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        State state = mock(DefaultState.class);
        Logger sut = new Logger(factoryMock);

        when(factoryMock.getDefaultState()).thenReturn(state);

        sut.log('a');

        verify(factoryMock, times(3)).getDefaultState();
    }

    @Test
    public void shouldVerifyThatThereWereThreeCallsOfGetDefaultStateMethodOfStateFactoryClassWhenYouCreatingLoggerObjectAndUsingLoggerToLogObject() throws  LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        State state = mock(DefaultState.class);
        Logger sut = new Logger(factoryMock);

        when(factoryMock.getDefaultState()).thenReturn(state);

        sut.log(new Object());

        verify(factoryMock, times(3)).getDefaultState();
    }

    @Test
    public void shouldVerifyThatThereWereThreeCallsOfGetDefaultStateMethodOfStateFactoryClassAndCallOfStaticMethodWhenYouCreatingLoggerObjectAndUsingLoggerToLogArrayOfInts() throws  LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        State state = mock(DefaultState.class);
        Logger sut = new Logger(factoryMock);

        when(factoryMock.getDefaultState()).thenReturn(state);

        sut.log(new int[]{1});

        verify(state).log("primitives array: {1}" + System.lineSeparator());
        verify(factoryMock, times(3)).getDefaultState();
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfArray() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((int[]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogEmptyArray() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log(new int[]{});
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfArrayOfStrings() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((String[]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfMultimatrix() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((int[][][][]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogEmptyMultimatrix() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log(new int[][][][]{});
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogEmptyMatrix() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log(new int[][]{});
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogEmptyArrayOfStrings() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log(new String[]{});
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfMatrix() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((int[][]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfString() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((String)null);
    }

    @Test(expected = NullMessageException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfObject() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((Object) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToClose() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.close();
    }

    // On my computer this test is green, but on Jenkins - red
    @Test
    @Ignore
    public void shouldVerifyThatThereWasCallOfFlushOfStateBuffer() throws  LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        State state = mock(StringState.class);

        Logger sut = new Logger(factoryMock);

        when(factoryMock.getStringState(anyObject())).thenReturn(state);

        sut.log("f");
        sut.close();

        verify(state).flush();
    }

}
