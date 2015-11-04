package com.acme.edu.commands;

import com.acme.edu.printers.Printer;

public class CommandFactory {

    public enum Type {INT, BYTE, STRING, CHAR, BOOLEAN, OBJECT}

    public Command createCommand(Type type, Printer printer) {
        switch (type) {
            case INT:
                return new LogIntegerCommand(printer, "primitive: %s", Integer.MAX_VALUE, Integer.MIN_VALUE);
            case BYTE:
                return new LogIntegerCommand(printer, "primitive: %s", Byte.MAX_VALUE, Byte.MIN_VALUE);
            case STRING:
                return new LogStringCommand(printer, "string: %s", "string: %s (x%d)");
            case CHAR:
                return new LogUnaccumulatedTypeCommand(printer, "char: %s");
            case BOOLEAN:
                return new LogUnaccumulatedTypeCommand(printer, "primitive: %s");
            case OBJECT:
                return new LogUnaccumulatedTypeCommand(printer, "reference: %s");
            default:
                throw new IllegalArgumentException("Unexpected type: " + type);
        }
    }
}
