package com.acme.edu.commands;

import com.acme.edu.decorators.DecoratorFactory;
import com.acme.edu.printers.Printer;

public class CommandFactory {

    public enum Type {INT, BYTE, STRING, CHAR, BOOLEAN, OBJECT}

    public Command createCommand(Type type, Printer... printers) {
        DecoratorFactory decoratorFactory = new DecoratorFactory();

        switch (type) {
            case INT:
                return new LogIntegerCommand(
                        decoratorFactory.createDecorator("primitive: %s"), Integer.MAX_VALUE, Integer.MIN_VALUE,
                        printers
                        );
            case BYTE:
                return new LogIntegerCommand(
                        decoratorFactory.createDecorator("primitive: %s"), Byte.MAX_VALUE, Byte.MIN_VALUE,
                        printers
                        );
            case STRING:
                return new LogStringCommand(
                        decoratorFactory.createDecorator("string: %s"),
                        decoratorFactory.createDecorator("string: %s (x%d)"),
                        printers
                );
            case CHAR:
                return new LogUnaccumulatedTypeCommand(decoratorFactory.createDecorator("char: %s"), printers);
            case BOOLEAN:
                return new LogUnaccumulatedTypeCommand(decoratorFactory.createDecorator("primitive: %s"), printers);
            case OBJECT:
                return new LogUnaccumulatedTypeCommand(decoratorFactory.createDecorator("reference: %s"), printers);
            default:
                throw new IllegalArgumentException("Unexpected type: " + type);
        }
    }
}
