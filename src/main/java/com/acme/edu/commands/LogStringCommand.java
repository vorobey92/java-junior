package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;

public class LogStringCommand extends Command {
    private int lengthOfStringsSequence;
    private Decorator oneStringSequenceDecorator;
    private Decorator mulitpleStringSequenceDecorator;
    
    public LogStringCommand(Decorator oneStringSequenceDecorator,
                            Decorator mulitpleStringSequenceDecorator,
                            LogWriter... logWriters) {
        super(logWriters);
        setLengthOfStringsSequence(0);
        this.oneStringSequenceDecorator = oneStringSequenceDecorator;
        this.mulitpleStringSequenceDecorator = mulitpleStringSequenceDecorator;
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);

        if (getMessage() == null) {
            setLengthOfStringsSequence(0);
            return;
        }

        setLengthOfStringsSequence(1);
    }

    public int getLengthOfStringsSequence() {
        return lengthOfStringsSequence;
    }

    public void setLengthOfStringsSequence(int lengthOfStringsSequence) {
        this.lengthOfStringsSequence = lengthOfStringsSequence;
    }

    @Override
    protected String getFormattedString() {
        Decorator decorator = getLengthOfStringsSequence() > 1
                ? mulitpleStringSequenceDecorator
                : oneStringSequenceDecorator;

        return decorator.decorate(getMessage(), getLengthOfStringsSequence());
    }

    @Override
    protected Command mergeWithCommandOfSameClass(Command oldCommand) throws LoggingException {
        LogStringCommand logStringCommand = (LogStringCommand) oldCommand;
        if (getMessage().equals(logStringCommand.getMessage())) {
            setLengthOfStringsSequence(getLengthOfStringsSequence() + logStringCommand.getLengthOfStringsSequence());
        } else {
            logStringCommand.execute();
        }

        return this;
    }
}
