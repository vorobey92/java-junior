package com.acme.edu.decorators;

public class Decorator {
    private String format;

    public Decorator(String format) {
        this.format = format;
    }

    public String decorate(Object... objectsToBeDecorated) {
        return String.format(format, objectsToBeDecorated);
    }
}
