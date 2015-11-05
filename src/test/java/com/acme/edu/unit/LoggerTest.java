package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.Mapper;
import com.acme.edu.exception.LogException;
import com.acme.edu.exception.NullMessageException;
import com.acme.edu.exception.PreviousStateIsNullException;
import com.acme.edu.state.StateFactory;
import com.acme.edu.printer.Printable;
import com.acme.edu.state.DefaultState;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import com.acme.edu.state.StringState;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static  org.mockito.Mockito.*;
import static org.junit.Assert.*;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(Mapper.class)

public class LoggerTest {

    private State state;

    @Test
    @Ignore
    public void shouldReturnDefaultStateWhenTryingToGetDefaultState() throws NullMessageException, LogException, PreviousStateIsNullException {
//        Printable printMock = mock(Printable.class);
        StateFactory factoryMock = mock(StateFactory.class);
//        Mapper mapperMock =
        State stateMock = mock(IntState.class);
        Logger sut = new Logger(factoryMock);

//        when(factoryMock.getDefaultState()).thenReturn(new DefaultState(printMock));
//        Mockito.when(Mapper.fromArrayToString()).thenReturn("fff");
        when(factoryMock.getIntState(stateMock)).thenReturn(stateMock);
//        sut.log(new int[]{1, 2});
        sut.log(5);
//        sut.close();
        verify(factoryMock).getIntState(stateMock);

//        verify()
    }

    @Test(expected = NullMessageException.class)
    public void should() throws NullMessageException, LogException {
        StateFactory factoryMock = mock(StateFactory.class);
        Logger sut = new Logger(factoryMock);

        sut.log((int[]) null);


    }

}
