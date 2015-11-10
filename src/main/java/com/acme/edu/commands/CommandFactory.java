package com.acme.edu.commands;

import com.acme.edu.writers.LogWriter;

/**
 * Defines a factory API that enables users to obtain a Command object.
 */
public interface CommandFactory {

    /**
     * Creates a new instance of a Command using the passed message and LogWriters. The created Command
     * is specific to the message's class. The created Command contains the passed message.
     * @param message the message for which a Command is created
     * @param logWriters an array of LogWriters which will be used for writing a message
     * @return the Command object
     */
    Command createCommand(Object message, LogWriter... logWriters);
}
