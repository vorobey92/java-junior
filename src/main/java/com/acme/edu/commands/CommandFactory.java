package com.acme.edu.commands;

import com.acme.edu.printers.LogWriter;

public interface CommandFactory {
    Command createCommand(Object message, LogWriter... LogWriters);
}
