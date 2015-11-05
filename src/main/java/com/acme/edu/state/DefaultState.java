package com.acme.edu.state;

import com.acme.edu.exception.LogException;
import com.acme.edu.printer.Printable;

/**
 * Default state for not Int(Byte) or String state.
 */
public class DefaultState extends State {

    /**
     *
     * @param printer determines stream for logging
     */
    public DefaultState(Printable printer) {
        super(printer);
    }

    /**
     *
     * @param message String that will be logged
     */
    @Override
    public void log(String message) throws LogException {
        print(message);
    }

    /**
     * Release buffers, but for DefaultState nothing to log.
     */
    @Override
    public void flush() {
// leak of Abstraction
    }
}
