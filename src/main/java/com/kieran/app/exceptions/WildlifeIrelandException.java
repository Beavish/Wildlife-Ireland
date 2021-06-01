package com.kieran.app.exceptions;

public class WildlifeIrelandException extends RuntimeException {
    public WildlifeIrelandException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public WildlifeIrelandException(String exMessage) {
        super(exMessage);
    }
}