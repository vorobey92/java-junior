package com.acme.edu.decorators;

public class DecoratorFactory {
    public Decorator createDecorator(String format) {
        return new Decorator(format);
    }
}
