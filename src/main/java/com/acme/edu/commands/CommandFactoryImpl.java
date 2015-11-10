package com.acme.edu.commands;

import com.acme.edu.decorators.DecoratorFactory;
import com.acme.edu.writers.LogWriter;

/**
 * An CommandFactory implementation
 */
public class CommandFactoryImpl implements CommandFactory {

    /**
     * Creates a new instance of a Command using the passed message and LogWriters. The created Command
     * is specific to the message's class. The created Command contains the passed message.
     * @param message the message for which a Command is created
     * @param logWriters an array of LogWriters which will be used for writing a message
     * @return the Command object
     */
    @Override
    public Command createCommand(Object message, LogWriter... logWriters) {
        DecoratorFactory decoratorFactory = new DecoratorFactory();

        if (message instanceof Integer) {
            return new LogIntegerCommand(
                    decoratorFactory.createDecorator("primitive: %s"),
                    Integer.MAX_VALUE,
                    Integer.MIN_VALUE,
                    logWriters
            );
        }

        if (message instanceof Byte) {
            return new LogIntegerCommand(
                    decoratorFactory.createDecorator("primitive: %s"),
                    Byte.MAX_VALUE,
                    Byte.MIN_VALUE,
                    logWriters
            );
        }

        if (message instanceof String) {
            return new LogStringCommand(
                    decoratorFactory.createDecorator("string: %s"),
                    decoratorFactory.createDecorator("string: %s (x%d)"),
                    logWriters
            );
        }

        if (message instanceof Character) {
            return new LogUnaccumulatedTypeCommand(decoratorFactory.createDecorator("char: %s"), logWriters);
        }

        if (message instanceof Boolean) {
            return new LogUnaccumulatedTypeCommand(decoratorFactory.createDecorator("primitive: %s"), logWriters);
        }

        return new LogUnaccumulatedTypeCommand(decoratorFactory.createDecorator("reference: %s"), logWriters);
    }
}