package com.acme.edu.commands;

import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.Printer;
import com.acme.edu.printers.PrinterException;

public class LogStringCommand extends Command<LogStringCommand> {
    private int lengthOfStringsSequence;
    private Decorator oneStringSequenceDecorator;
    private Decorator mulitpleStringSequenceDecorator;
    
    public LogStringCommand(Printer printer,
                            Decorator oneStringSequenceDecorator,
                            Decorator mulitpleStringSequenceDecorator) {
        super(printer);
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

    @Override
    public LogStringCommand merge(LogStringCommand oldCommand) throws PrinterException {
        if (oldCommand == null || oldCommand.getMessage() == null) {
            return this;
        }

        if (oldCommand.getMessage().equals(getMessage())) {
            setLengthOfStringsSequence(getLengthOfStringsSequence() + oldCommand.getLengthOfStringsSequence());
        } else {
            oldCommand.execute();
        }
        
        return this;
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
}
