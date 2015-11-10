package com.acme.edu.commands;

import com.acme.edu.writers.LogWriter;

public interface CommandFactory {
    Command createCommand(Object message, LogWriter... LogWriters);
}
