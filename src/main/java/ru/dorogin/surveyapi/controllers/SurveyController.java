package ru.dorogin.surveyapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dorogin.surveyapi.entites.Survey;
import ru.dorogin.surveyapi.repositories.SurveyRepository;

import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping("/surveys")
    List<Survey> all(){
        return surveyRepository.findAll();
    }
}
