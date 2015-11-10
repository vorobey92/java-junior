package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;

/**
 * LogIntegerCommand is subclass of AccumulatingCommand and implements behaviour of accumulating of
 * integral-type messages with bounds checking. If this command mergers with another command of LogIntegerCommand
 * class, this command is returned with a new message which is equal sum of the message of this command and the
 * message of another command. If the new message may come out of bounds, another command and this command are
 * executed respectively instead of addition of their messages and this command is returned.
 */
public class LogStringCommand extends AccumulatingCommand {
    private int lengthOfStringsSequence;
    private Decorator oneStringSequenceDecorator;
    private Decorator mulitpleStringSequenceDecorator;
    
    public LogStringCommand(Decorator oneStringSequenceDecorator,
                            Decorator mulitpleStringSequenceDecorator,
                            LogWriter... logWriters) {
        super(logWriters);
        this.lengthOfStringsSequence = 0;
        this.oneStringSequenceDecorator = oneStringSequenceDecorator;
        this.mulitpleStringSequenceDecorator = mulitpleStringSequenceDecorator;
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);

        if (getMessage() == null) {
            lengthOfStringsSequence = 0;
            return;
        }

        lengthOfStringsSequence = 1;
    }

    public int getLengthOfStringsSequence() {
        return lengthOfStringsSequence;
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
            lengthOfStringsSequence = getLengthOfStringsSequence() + logStringCommand.getLengthOfStringsSequence();
        } else {
            logStringCommand.execute();
        }

        return this;
    }
}
