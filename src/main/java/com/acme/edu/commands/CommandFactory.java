package com.acme.edu.commands;

import com.acme.edu.printers.Printer;

public class CommandFactory {

    public enum Command {INT, BYTE, STRING, CHAR, BOOLEAN, OBJECT}

    public static com.acme.edu.commands.Command createCommand(Command command, Printer printer) {
        switch (command) {
            case INT:
                return new IntegerCommand(printer, "primitive: %s", Integer.MAX_VALUE, Integer.MIN_VALUE);
            case BYTE:
                return new IntegerCommand(printer, "primitive: %s", Byte.MAX_VALUE, Byte.MIN_VALUE);
            case STRING:
                return new StringCommand(printer, "string: %s", "string: %s (x%d)");
            case CHAR:
                return new UnaccumulatingCommand(printer, "char: %s");
            case BOOLEAN:
                return new UnaccumulatingCommand(printer, "primitive: %s");
            case OBJECT:
                return new UnaccumulatingCommand(printer, "reference: %s");
            default:
                throw new IllegalArgumentException("Unexpected type: " + command);
        }
    }

    private CommandFactory() {

    }


}
