package com.acme.edu.states;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.commands.Command;

public class State {
    private Command bufferedCommand;

    public void flush() throws LoggingException {
        if (bufferedCommand != null) {
            bufferedCommand.execute();
        }
    }

    public void apply(Command newCommand) throws LoggingException {
        bufferedCommand = newCommand.merge(bufferedCommand);
    }
}
