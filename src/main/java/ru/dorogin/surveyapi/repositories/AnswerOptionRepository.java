package ru.dorogin.surveyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dorogin.surveyapi.entites.AnswerOption;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
}
