package com.acme.edu.commands;

import com.acme.edu.decorators.DecoratorFactory;
import com.acme.edu.printers.LogWriter;

public class CommandFactoryImpl implements CommandFactory {
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