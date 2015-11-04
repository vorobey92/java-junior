package com.acme.edu.commands;

import com.acme.edu.decorators.DecoratorFactory;
import com.acme.edu.printers.Printer;

public class CommandFactory {

    public enum Type {INT, BYTE, STRING, CHAR, BOOLEAN, OBJECT}

    public Command createCommand(Type type, Printer printer) {
        DecoratorFactory decoratorFactory = new DecoratorFactory();

        switch (type) {
            case INT:
                return new LogIntegerCommand(
                        printer, decoratorFactory.createDecorator("primitive: %s"), Integer.MAX_VALUE, Integer.MIN_VALUE
                );
            case BYTE:
                return new LogIntegerCommand(
                        printer, decoratorFactory.createDecorator("primitive: %s"), Byte.MAX_VALUE, Byte.MIN_VALUE
                );
            case STRING:
                return new LogStringCommand(
                        printer,
                        decoratorFactory.createDecorator("string: %s"),
                        decoratorFactory.createDecorator("string: %s (x%d)")
                );
            case CHAR:
                return new LogUnaccumulatedTypeCommand(printer, decoratorFactory.createDecorator("char: %s"));
            case BOOLEAN:
                return new LogUnaccumulatedTypeCommand(printer, decoratorFactory.createDecorator("primitive: %s"));
            case OBJECT:
                return new LogUnaccumulatedTypeCommand(printer, decoratorFactory.createDecorator("reference: %s"));
            default:
                throw new IllegalArgumentException("Unexpected type: " + type);
        }
    }
}
