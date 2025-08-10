package com.microservice.Question_Service.repositories;

import com.microservice.Question_Service.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findQuestionByCategory(String category);

    @Query(value = "SELECT q.id FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :noOfQuestions", nativeQuery = true)
    List<Integer> getQuestionsByCategory(@Param("category") String category, @Param("noOfQuestions") int noOfQuestions);

}

