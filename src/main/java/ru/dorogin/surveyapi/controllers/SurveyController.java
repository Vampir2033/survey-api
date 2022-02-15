package ru.dorogin.surveyapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dorogin.surveyapi.controllers.exceptions.InvalidFieldChangeException;
import ru.dorogin.surveyapi.controllers.exceptions.SurveyNotFoundException;
import ru.dorogin.surveyapi.entites.Survey;
import ru.dorogin.surveyapi.repositories.SurveyRepository;

import java.util.List;
import java.util.Objects;

@RestController
public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping("/surveys")
    List<Survey> all(){
        return surveyRepository.findAll();
    }

    @GetMapping("/surveys/{id}")
    Survey getById(@PathVariable Long id){
        return surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(id));
    }

    @PostMapping("/surveys")
    Survey postSurvey(@RequestBody Survey newSurvey){
        return surveyRepository.save(newSurvey);
    }

    @PutMapping("/surveys/{id}")
    Survey replaceSurvey(@RequestBody Survey newSurvey, @PathVariable Long id){
        return surveyRepository.findById(id)
                .map(survey -> {
                    if(newSurvey.getDateStart() != null ||
                            !Objects.equals(newSurvey.getDateStart(), survey.getDateStart())){
                        throw new InvalidFieldChangeException("Нельзя менять дату начала опроса");
                    }
                    survey.setTitle(newSurvey.getTitle());
                    survey.setDescription(newSurvey.getDescription());
                    survey.setDateStop(newSurvey.getDateStop());
                    return surveyRepository.save(survey);
                })
                .orElseThrow(() -> new SurveyNotFoundException(id));
    }
}
