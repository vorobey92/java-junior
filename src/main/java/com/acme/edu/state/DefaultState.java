package com.acme.edu.state;

import com.acme.edu.exception.PrintException;
import com.acme.edu.printer.Printable;

/**
 * Default state for not Int(Byte) or String state.
 */
public class DefaultState extends State {

    /**
     *
     * @param printers determines streams for logging
     */
    public DefaultState(Printable... printers) {
        super(printers);
    }

    /**
     *
     * @param message String that will be logged
     */
    @Override
    public void log(String message) throws PrintException {
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
