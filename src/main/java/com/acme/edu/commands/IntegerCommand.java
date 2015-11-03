package com.acme.edu.commands;

import com.acme.edu.printers.Printer;

public class IntegerCommand extends Command<IntegerCommand> {
    private int maxValue;
    private int minValue;
    private String format;

    public IntegerCommand(Printer printer, String format, int maxValue, int minValue) {
        super(printer);
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.format = format;
    }

    @Override
    public IntegerCommand merge(IntegerCommand oldCommand) {
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
        return String.format(format, getMessage());
    }

    private boolean isSumOutOfRange(String oldMessage) {

        int summand = Integer.parseInt(getMessage());
        int oldSummand = Integer.parseInt(oldMessage);

        return (oldSummand > 0 && maxValue - oldSummand < summand)
                || (oldSummand < 0 && minValue - oldSummand > summand);
    }
}
