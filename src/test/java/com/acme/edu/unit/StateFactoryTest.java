package com.acme.edu.unit;

import com.acme.edu.StateFactory;
import com.acme.edu.printer.Printable;
import com.acme.edu.state.EmptyState;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import com.acme.edu.state.StringState;
import org.junit.Test;
import static  org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class StateFactoryTest {

    @Test
    public void shouldReturnRightState(){
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);
        State intStub = mock(IntState.class);
        State stringStub = mock(StringState.class);

        assertEquals(EmptyState.class,factory.getEmptyState().getClass());
        assertEquals(IntState.class, factory.getIntState(intStub).getClass());
        assertEquals(IntState.class, factory.getIntState(stringStub).getClass());
        assertEquals(StringState.class, factory.getStringState(intStub).getClass());
        assertEquals(StringState.class, factory.getStringState(stringStub).getClass());
    }

    @Test
    public void shouldFlushWhenGetStateNotEqualLastState() {
        Printable mock = mock(Printable.class);
        StateFactory factory = new StateFactory(mock);
        State intStub = mock(IntState.class);
        State stringStub = mock(StringState.class);

        factory.getIntState(stringStub);
        factory.getStringState(intStub);

        verify(intStub).flush();
        verify(stringStub).flush();
    }
}
