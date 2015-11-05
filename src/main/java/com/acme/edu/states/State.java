package com.acme.edu.states;

import com.acme.edu.commands.Command;
import com.acme.edu.printers.PrinterException;

public class State {
    private Command bufferedCommand;

    public void flush() throws PrinterException {
        if (bufferedCommand != null) {
            bufferedCommand.execute();
        }
    }

    public void apply(Command newCommand) throws PrinterException {
        bufferedCommand = newCommand.merge(bufferedCommand);
    }
}
