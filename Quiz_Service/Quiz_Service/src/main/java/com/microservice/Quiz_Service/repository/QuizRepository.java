package com.microservice.Quiz_Service.repository;

import com.microservice.Quiz_Service.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

    Optional<Quiz> getQuizById(Long id);
}
