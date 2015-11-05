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
        lengthOfStringsSequence = 0;
        this.oneStringSequenceDecorator = oneStringSequenceDecorator;
        this.mulitpleStringSequenceDecorator = mulitpleStringSequenceDecorator;
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
        lengthOfStringsSequence = 1;
    }

    @Override
    public LogStringCommand merge(LogStringCommand oldCommand) throws PrinterException {
        if (oldCommand == null || oldCommand.getMessage() == null) {
            return this;
        }

        if (oldCommand.getMessage().equals(getMessage())) {
            lengthOfStringsSequence += oldCommand.lengthOfStringsSequence;
        } else {
            oldCommand.execute();
        }
        
        return this;
    }

    @Override
    protected String getFormattedString() {
        Decorator decorator = lengthOfStringsSequence > 1
                ? mulitpleStringSequenceDecorator
                : oneStringSequenceDecorator;

        return decorator.decorate(getMessage(), lengthOfStringsSequence);
    }
}
