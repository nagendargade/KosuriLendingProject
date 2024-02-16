package com.example.kosuriTask.exceptionHandling;

public class ExceptionHandling extends  RuntimeException{
    public ExceptionHandling(String message) {
        super(message);
    }
    public String getErrorMessage() {
        return getMessage();
    }
}
