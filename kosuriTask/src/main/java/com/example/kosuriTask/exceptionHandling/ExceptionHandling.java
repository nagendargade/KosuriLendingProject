package com.example.kosuriTask.exceptionHandling;

public class ExceptionHandling extends  RuntimeException{
    private final String errorMessage;

    public ExceptionHandling(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
