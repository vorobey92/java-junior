package com.acme.edu.commands;

import com.acme.edu.decorators.DecoratorFactory;
import com.acme.edu.printers.LogWriter;

public class CommandFactory {

    public enum Type {INT, BYTE, STRING, CHAR, BOOLEAN, OBJECT}

    public Command createCommand(Type type, LogWriter... logWriters) {
        DecoratorFactory decoratorFactory = new DecoratorFactory();

        switch (type) {
            case INT:
                return new LogIntegerCommand(
                        decoratorFactory.createDecorator("primitive: %s"),
                        Integer.MAX_VALUE,
                        Integer.MIN_VALUE,
                        logWriters
                );
            case BYTE:
                return new LogIntegerCommand(
                        decoratorFactory.createDecorator("primitive: %s"),
                        Byte.MAX_VALUE,
                        Byte.MIN_VALUE,
                        logWriters
                );
            case STRING:
                return new LogStringCommand(
                        decoratorFactory.createDecorator("string: %s"),
                        decoratorFactory.createDecorator("string: %s (x%d)"),
                        logWriters
                );
            case CHAR:
                return new LogUnaccumulatedTypeCommand(decoratorFactory.createDecorator("char: %s"), logWriters);
            case BOOLEAN:
                return new LogUnaccumulatedTypeCommand(decoratorFactory.createDecorator("primitive: %s"), logWriters);
            case OBJECT:
                return new LogUnaccumulatedTypeCommand(decoratorFactory.createDecorator("reference: %s"), logWriters);
            default:
                throw new IllegalArgumentException("Unexpected type: " + type);
        }
    }
}
