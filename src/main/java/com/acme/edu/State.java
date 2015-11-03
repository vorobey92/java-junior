package com.acme.edu;

import com.acme.edu.commands.Command;

public class State {
    private Command command;

    public void fflush() {
        if (command != null) {
            command.execute();
        }
    }

    public void log(Command newCommand) {
        command = newCommand.merge(command);
    }
}
