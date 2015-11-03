package com.acme.edu.Commands;

public class PrefixCommand implements Command {
    @Override
    public String decorate(String decorator, String stringToBeDecorated) {
        return decorator + stringToBeDecorated;
    }
}
