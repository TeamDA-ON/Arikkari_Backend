package com.daon.arikkari.domain.question.wrong.repository;

import com.daon.arikkari.domain.question.wrong.domain.Wrong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WrongRepository extends JpaRepository<Wrong, Long> {
    List<Wrong> findAllByEmail(String email);

    Long countByEmail(String email);
}
