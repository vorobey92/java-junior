package com.acme.edu.commands;

import com.acme.edu.businessexceptions.LoggingException;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.LogWriter;

/**
 * LogIntegerCommand is subclass of AccumulatingCommand and implements behaviour of accumulating of
 * String-type messages by counting the total number of successively merged LogIntegerCommands which carry
 * the same message. If this command merges with another command of LogStringCommand class which has the same
 * message, this command is returned and its counter is increased by the counter of the merged command. If
 * another command has a different message, this command is returned without any modifications and another
 * command is executed.
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

    /**
     * Sets a passed String as the command's message. Invocation of this method drops currently accumulated
     * data if this message has so.
     * @param message a String to be set as the command's message
     */
    @Override
    public void setMessage(String message) {
        super.setMessage(message);

        if (getMessage() == null) {
            lengthOfStringsSequence = 0;
            return;
        }

        lengthOfStringsSequence = 1;
    }

    /**
     * @return the number of accumulated string messages
     */
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
