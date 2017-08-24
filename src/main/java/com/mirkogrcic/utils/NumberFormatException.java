package com.mirkogrcic.utils;

public class NumberFormatException extends java.lang.NumberFormatException {
    public NumberFormatException(String message){
        super(message);
    }

    public NumberFormatException(String message, Throwable throwable){
        super(message);
        this.addSuppressed(throwable);
    }
}
