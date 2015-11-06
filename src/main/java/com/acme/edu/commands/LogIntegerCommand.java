package com.acme.edu.commands;

import com.acme.edu.decorators.Decorator;
import com.acme.edu.printers.Printer;
import com.acme.edu.printers.PrinterException;

public class LogIntegerCommand extends Command<LogIntegerCommand> {
    private int maxValue;
    private int minValue;
    private Decorator decorator;

    public LogIntegerCommand(Decorator decorator, int maxValue, int minValue, Printer... printers) {
        super(printers);
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.decorator = decorator;
    }

    @Override
    public LogIntegerCommand merge(LogIntegerCommand oldCommand) throws PrinterException{
        if (oldCommand == null || oldCommand.getMessage() == null) {
            return this;
        }

        if (isSumOutOfRange(oldCommand.getMessage())) {
            oldCommand.execute();
            execute();
            setMessage(null);

            return this;
        }

        setMessage(
                Integer.toString(Integer.parseInt(getMessage()) + Integer.parseInt(oldCommand.getMessage()))
        );

        return this;
    }

    @Override
    protected String getFormattedString() {
        return decorator.decorate(getMessage());
    }

    private boolean isSumOutOfRange(String oldMessage) {

        int summand = Integer.parseInt(getMessage());
        int oldSummand = Integer.parseInt(oldMessage);

        return (oldSummand > 0 && maxValue - oldSummand < summand)
                || (oldSummand < 0 && minValue - oldSummand > summand);
    }
}
