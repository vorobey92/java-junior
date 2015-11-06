package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.exception.LogException;
import com.acme.edu.state.*;
import org.junit.Test;

import static  org.mockito.Mockito.*;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(Mapper.class)

public class LoggerTest {

    private State state;

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

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfArray() throws LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((int[]) null);
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

    @Test(expected = LogException.class)
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

}
