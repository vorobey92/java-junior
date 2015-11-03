package com.acme.edu.commands;

import com.acme.edu.printers.Printer;

public class StringCommand extends Command<StringCommand> {
    private int lengthOfStringsSequence;
    private String oneStringSequenceFormat;
    private String mulitpleStringSequenceFormat;
    
    public StringCommand(Printer printer, String oneStringSequenceFormat, String mulitpleStringSequenceFormat) {
        super(printer);
        lengthOfStringsSequence = 0;
        this.oneStringSequenceFormat = oneStringSequenceFormat;
        this.mulitpleStringSequenceFormat = mulitpleStringSequenceFormat;
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
        lengthOfStringsSequence = 1;
    }

    @Override
    public StringCommand merge(StringCommand oldCommand) {
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
        String format = lengthOfStringsSequence > 1 ? mulitpleStringSequenceFormat : oneStringSequenceFormat;

        return String.format(format, getMessage(), lengthOfStringsSequence);
    }
}
