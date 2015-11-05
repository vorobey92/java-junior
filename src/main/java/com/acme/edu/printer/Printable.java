package com.acme.edu.printer;

import com.acme.edu.exception.CanNotPrintException;

/**
 * Interface with print() and println() methods
 * In realisation of interface Author can chose stream for logging
 */
public interface Printable {
    /**
     *
     * @param message String message
     */
    public void print(String message) throws CanNotPrintException;

    /**
     * Prints a String and then terminate the line.
     * @param message The <code>String</code> to be printed
     */
    public void println(String message)throws CanNotPrintException;

}
