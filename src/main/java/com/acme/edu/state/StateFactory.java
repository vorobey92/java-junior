package com.acme.edu.state;

import com.acme.edu.exception.StateException;
import com.acme.edu.printer.Printable;

/**
 * Factory for states
 */
public class StateFactory {

    private State intState;
    private State stringState;
    private State defaultState;

    /**
     * Creating objects for all states.
     * @param printer determines stream for output
     */
    public StateFactory(Printable printer) {
        intState = new IntState(printer);
        stringState = new StringState(printer);
        defaultState = new DefaultState(printer);
    }


    /**
     * if last state wasn't string state
     * method will call flush() for last state
     *
     * @param lastState previous state
     * @return returns string state
     */
    public State getStringState(State lastState) throws StateException {
        if (lastState != null && lastState != stringState) {
            lastState.flush();
        }
        return  stringState;
    }

    /**
     * if last state wasn't int state
     * method will call flush() for last state
     *
     * @param lastState previous state
     * @return returns int state
     */
    public State getIntState(State lastState) throws StateException {
        if (lastState != null && lastState != intState) {
            lastState.flush();
        }
        return intState;
    }

    /**
     *
     * @return empty state
     */
    public State getDefaultState(){
        return defaultState;
    }

}
