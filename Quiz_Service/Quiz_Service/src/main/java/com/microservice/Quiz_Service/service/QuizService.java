package com.microservice.Quiz_Service.service;

import com.microservice.Quiz_Service.dtos.QuestionDto;
import com.microservice.Quiz_Service.dtos.ResponseDto;
import com.microservice.Quiz_Service.entities.Quiz;
import com.microservice.Quiz_Service.feign.QuizInterface;
import com.microservice.Quiz_Service.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String categoryName, Integer numQuestions, String title) {
        try {
            List<Integer> questionDtos = quizInterface.getQuestionsForQuiz(categoryName, numQuestions).getBody();
            if(questionDtos !=null) {
                Quiz quiz = new Quiz();
                quiz.setTitle(title);
                quiz.setQuestionId(questionDtos);
                quizRepository.save(quiz);
                return ResponseEntity.ok("Quiz Created Successfully");
            }
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }

    public ResponseEntity<List<QuestionDto>> generateQuestionsFromId(Integer id) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(Long.valueOf(id));
            List<Integer> questionIds = quiz.get().getQuestionId();
            List<QuestionDto> questionDtos = quizInterface.getQuestionsFromId(questionIds).getBody();
            return ResponseEntity.ok(questionDtos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<ResponseDto> responseDto) {
        try {
            return quizInterface.getScore(responseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(0);
        }
    }
}
