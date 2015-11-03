package com.acme.edu;

import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.Printable;
import com.acme.edu.state.EmptyState;
import com.acme.edu.state.IntState;
import com.acme.edu.state.State;
import com.acme.edu.state.StringState;

/**
 * Factory for states
 */
public class StateFactory {

    private State intState;
    private State stringState;
    private State emptyState;
    private Printable printer;

    /**
     * Creating objects for all states.
     * @param printer determines stream for output
     */
    public StateFactory(Printable printer) {
        printer = new ConsolePrinter();
        intState = new IntState(printer);
        stringState = new StringState(printer);
        emptyState = new EmptyState(printer);
    }


    /**
     * if last state wasn't string state
     * method will call flush() for last state
     *
     * @param lastState previous state
     * @return returns string state
     */
    public State getStringState(State lastState) {
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
    public State getIntState(State lastState) {
        if (lastState != null && lastState != intState) {
            lastState.flush();
        }
        return intState;
    }

    /**
     * 
     * @return empty state
     */
    public State getEmptyState(){
        return emptyState;
    }

}
