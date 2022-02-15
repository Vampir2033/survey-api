package ru.dorogin.surveyapi.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dorogin.surveyapi.entites.AnswerOption;
import ru.dorogin.surveyapi.entites.Question;
import ru.dorogin.surveyapi.entites.QuestionType;
import ru.dorogin.surveyapi.entites.Survey;
import ru.dorogin.surveyapi.repositories.AnswerOptionRepository;
import ru.dorogin.surveyapi.repositories.QuestionRepository;
import ru.dorogin.surveyapi.repositories.SurveyRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
public class BdInitialization {
    private static final Logger log = LoggerFactory.getLogger(BdInitialization.class);

    @Bean
    CommandLineRunner fillSurveyBd(SurveyRepository surveyRepository,
                                   QuestionRepository questionRepository,
                                   AnswerOptionRepository answerOptionRepository){
        return (args) -> {
            Survey survey = new Survey("Опрос 1",
                    "Описание опроса 1",
                    new Date(System.currentTimeMillis()),
                    new Date(System.currentTimeMillis())
            );
            surveyRepository.save(survey);
            List<Question> questionList = Arrays.asList(
                    new Question(survey, "Вопрос 1", QuestionType.WITH_TEXT_ANSWER),
                    new Question(survey, "Вопрос 2", QuestionType.WITH_ONE_ANSWER),
                    new Question(survey, "Вопрос 3", QuestionType.WITH_SEVERAL_ANSWERS)
            );
            questionRepository.saveAll(questionList);

            List<AnswerOption> answerOptions = new ArrayList<>(){{
                add(new AnswerOption(questionList.get(1), "Ответ 1 на вопрос 2"));
                add(new AnswerOption(questionList.get(1), "Ответ 2 на вопрос 2"));
                add(new AnswerOption(questionList.get(1), "Ответ 3 на вопрос 2"));
                add(new AnswerOption(questionList.get(2), "Ответ 1 на вопрос 3"));
                add(new AnswerOption(questionList.get(2), "Ответ 2 на вопрос 3"));
                add(new AnswerOption(questionList.get(2), "Ответ 3 на вопрос 3"));
            }};
            answerOptionRepository.saveAll(answerOptions);


        };

    }
}
