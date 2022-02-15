package ru.dorogin.surveyapi.controllers.exceptions;

public class InvalidFieldChangeException extends RuntimeException {
    public InvalidFieldChangeException(String s){
        super(s);
    }
}
