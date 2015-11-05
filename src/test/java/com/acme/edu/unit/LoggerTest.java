package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.exception.LogException;
import com.acme.edu.exception.NullMessageException;
import com.acme.edu.exception.StateIsNullException;
import com.acme.edu.state.StateFactory;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import org.junit.Ignore;
import org.junit.Test;

import static  org.mockito.Mockito.*;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(Mapper.class)

public class LoggerTest {

    private State state;

    @Test
    @Ignore
    public void shouldReturnDefaultStateWhenTryingToGetDefaultState() throws NullMessageException, LogException, StateIsNullException {
        StateFactory factoryMock = mock(StateFactory.class);
        State state = mock(IntState.class);
        Logger sut = new Logger(factoryMock);

        sut.log(5);

        verify(factoryMock).getIntState(state);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfArray() throws NullMessageException, LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((int[]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfArrayOfStrings() throws NullMessageException, LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((String[]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfMultimatrix() throws NullMessageException, LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((int[][][][]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfMatrix() throws NullMessageException, LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((int[][]) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfString() throws NullMessageException, LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((String)null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToLogNullInsteadOfObject() throws NullMessageException, LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((Object) null);
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenTryingToClose() throws NullMessageException, LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.close();
    }

}
