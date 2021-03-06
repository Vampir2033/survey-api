package ru.dorogin.surveyapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.dorogin.surveyapi.controllers.exceptions.InvalidFieldChangeException;
import ru.dorogin.surveyapi.controllers.exceptions.SurveyNotFoundException;

@ControllerAdvice
public class SurveyAdvice {

    @ResponseBody
    @ExceptionHandler(SurveyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String SurveyNotFoundHandler(SurveyNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidFieldChangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String SurveyInvalidFieldChange(InvalidFieldChangeException ex){
        return ex.getMessage();
    }

}
