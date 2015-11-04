package com.acme.edu;

import com.acme.edu.commands.Command;

public class State {
    private Command bufferedCommand;

    public void fflush() {
        if (bufferedCommand != null) {
            bufferedCommand.execute();
        }
    }

    public void apply(Command newCommand) {
        bufferedCommand = newCommand.merge(bufferedCommand);
    }
}
