package ru.dorogin.surveyapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import ru.dorogin.surveyapi.controllers.exceptions.InvalidFieldChangeException;
import ru.dorogin.surveyapi.controllers.exceptions.SurveyNotFoundException;
import ru.dorogin.surveyapi.entites.Question;
import ru.dorogin.surveyapi.entites.Survey;
import ru.dorogin.surveyapi.repositories.QuestionRepository;
import ru.dorogin.surveyapi.repositories.SurveyRepository;

import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/surveys")
    List<Survey> all(){
        return surveyRepository.findAll();
    }

    @GetMapping("/surveys/{id}")
    Survey getById(@PathVariable Long id){
        return surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(id));
    }

    @PostMapping(value = "/surveys")
    Survey postSurvey(@RequestBody Survey newSurvey){
        return surveyRepository.save(newSurvey);
    }

    @PutMapping("/surveys/{id}")
    Survey replaceSurvey(@RequestBody Survey newSurvey, @PathVariable Long id){
        return surveyRepository.findById(id)
                .map(survey -> {
                    if(newSurvey.getDateStart() != null &&
                            !newSurvey.getDateStart().equals(survey.getDateStart())){
                        throw new InvalidFieldChangeException("Нельзя менять дату начала опроса");
                    }
                    survey.setTitle(newSurvey.getTitle());
                    survey.setDescription(newSurvey.getDescription());
                    survey.setDateStop(newSurvey.getDateStop());
                    return surveyRepository.save(survey);
                })
                .orElseThrow(() -> new SurveyNotFoundException(id));
    }

    @DeleteMapping("/surveys/{id}")
    void deleteEmployee(@PathVariable Long id) {
        try {
            surveyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex){
            throw new SurveyNotFoundException(id);
        }
    }

    @PostMapping("/surveys/{id}/add-question")
    Question addQuestionToSurvey(@RequestBody Question question, @PathVariable Long id){
        return surveyRepository.findById(id)
                .map(survey -> {
                    question.setSurvey(survey);
                    return questionRepository.save(question);
                })
                .orElseThrow(() -> new SurveyNotFoundException(id));
    }
}
