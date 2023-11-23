package com.study.dreamCrud;

public class NotFoundException extends RuntimeException{
    public NotFoundException(final String message) {
        this(message, null);
    }

    public NotFoundException(final String message, Throwable cause) {
        super(message, cause, true, false);
    }
}