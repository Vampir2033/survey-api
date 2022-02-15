package ru.dorogin.surveyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorogin.surveyapi.entites.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
