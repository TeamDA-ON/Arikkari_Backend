package com.daon.arikkari.domain.question.shortanswerquestion.repository;

import com.daon.arikkari.domain.question.shortanswerquestion.domain.ShortAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortAnswerQuestionRepository extends JpaRepository<ShortAnswerQuestion, Long> {
    @Query("SELECT count(id) FROM ShortAnswerQuestion")
    Long findMaxId();
}
