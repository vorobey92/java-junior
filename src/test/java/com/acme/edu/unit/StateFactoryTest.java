package com.acme.edu.unit;

import com.acme.edu.state.StateFactory;
import com.acme.edu.printer.Printable;
import com.acme.edu.state.DefaultState;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import com.acme.edu.state.StringState;
import org.junit.Test;
import static  org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class StateFactoryTest {

    @Test
    public void shouldReturnDefaultStateWhenTryingToGetDefaultState(){
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);

        assertEquals(DefaultState.class,factory.getDefaultState().getClass());
    }

    @Test
    public void shouldReturnIntStateWhenTryingToGetIntStateWithAFactoryGetMethodWithParameterIntState(){
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);
        State intStub = mock(IntState.class);

        assertEquals(IntState.class, factory.getIntState(intStub).getClass());
    }

    @Test
    public void shouldReturnIntStateWhenTryingToGetIntStateWithAFactoryGetMethodWithParameterNull(){
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);

        assertEquals(IntState.class, factory.getIntState(null).getClass());
    }

    @Test
    public void shouldReturnIntStateWhenTryingToGetIntStateWithAFactoryGetMethodWithParameterStringState(){
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);
        State stringStub = mock(StringState.class);

        assertEquals(IntState.class, factory.getIntState(stringStub).getClass());
    }

    @Test
    public void shouldReturnStringStateWhenTryingToGetStringStateWithAFactoryGetMethodWithParameterIntState(){
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);
        State intStub = mock(IntState.class);

        assertEquals(StringState.class, factory.getStringState(intStub).getClass());
    }

    @Test
    public void shouldReturnStringStateWhenTryingToGetStringStateWithAFactoryGetMethodWithParameterNull(){
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);

        assertEquals(StringState.class, factory.getStringState(null).getClass());
    }

    @Test
    public void shouldReturnStringStateWhenTryingToGetStringStateWithAFactoryGetMethodWithParameterStringState(){
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);
        State stringStub = mock(StringState.class);

        assertEquals(StringState.class, factory.getStringState(stringStub).getClass());
    }


    @Test
    public void shouldFlushWhenIntStateNotEqualPreviousDefaultState() {
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);
        State intStub = mock(IntState.class);

        factory.getStringState(intStub);

        verify(intStub).flush();
    }

    @Test
    public void shouldFlushWhenStringStateNotEqualPreviousDefaultState() {
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);
        State stringStub = mock(StringState.class);

        factory.getIntState(stringStub);

        verify(stringStub).flush();
    }
}
