package ru.dorogin.surveyapi.controllers.exceptions;

public class SurveyNotFoundException extends RuntimeException {
    public SurveyNotFoundException(Long id){
        super("Не удалось найти опрос с идентификатором " + id);
    }
}
