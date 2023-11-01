package com.daon.arikkari.domain.question.multiplechoicequestion.repository;

import com.daon.arikkari.domain.question.multiplechoicequestion.domain.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long> {
    @Query("SELECT MAX(id) FROM MultipleChoiceQuestion ")
    Long findMaxId();

}
