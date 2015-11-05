package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.Mapper;
import com.acme.edu.exception.NullMessageException;
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
    public void shouldReturnDefaultStateWhenTryingToGetDefaultState() throws NullMessageException {
        Printable printMock = mock(Printable.class);
        StateFactory factoryMock = mock(StateFactory.class);
//        Mapper mapperMock =
//        state = new DefaultState(printMock);
        Logger sut = new Logger(printMock);

//        when(factoryMock.getDefaultState()).thenReturn(new DefaultState(printMock));
//        Mockito.when(Mapper.fromArrayToString()).thenReturn("fff");

        sut.log(new int[]{1, 2});
        sut.log(5);
//        sut.close();

        verify(factoryMock).getDefaultState();
//        verify()
    }

    @Test
    @Ignore
    public void should() throws NullMessageException {
        Printable printMock = mock(Printable.class);
        Logger sut = new Logger(printMock);

        sut.log((int[]) null);

    }

}
