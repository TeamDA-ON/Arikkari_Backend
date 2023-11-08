package com.daon.arikkari.domain.question.correct.repository;

import com.daon.arikkari.domain.question.correct.domain.Correct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorrectRepository extends JpaRepository<Correct, Long> {
    List<Correct> findAllByEmail(String email);
    Long countByEmail(String email);
}
