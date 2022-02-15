package ru.dorogin.surveyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dorogin.surveyapi.entites.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
