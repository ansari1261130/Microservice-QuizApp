package com.microservice.Quiz_Service.controller;

import com.microservice.Quiz_Service.dtos.QuestionDto;
import com.microservice.Quiz_Service.dtos.QuizDto;
import com.microservice.Quiz_Service.dtos.ResponseDto;
import com.microservice.Quiz_Service.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;
    @RequestMapping("/create")
    public ResponseEntity<String> generateQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @RequestMapping("/getQuestions/{id}")
    public ResponseEntity<List<QuestionDto>> generateQuestionsFromId(@PathVariable Integer id) {
        return quizService.generateQuestionsFromId(id);
    }

    @PostMapping("/getScore/{id}")
    public ResponseEntity<Integer> getScore(@PathVariable Integer id ,@RequestBody List<ResponseDto> responseDto) {
        return quizService.calculateScore(id,responseDto);
    }
}
