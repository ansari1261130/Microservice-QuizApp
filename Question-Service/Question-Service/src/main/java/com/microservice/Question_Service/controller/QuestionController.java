package com.microservice.Question_Service.controller;

import com.microservice.Question_Service.dtos.QuestionDto;
import com.microservice.Question_Service.dtos.QuestionUpdateDto;
import com.microservice.Question_Service.dtos.ResponseDto;
import com.microservice.Question_Service.entities.Question;
import com.microservice.Question_Service.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    private final Environment environment;

    @GetMapping("getAllQuestion")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("getAllQuestionByCategory/{category}")
    public ResponseEntity<List<Question>> getAllQuestionByCategory(@PathVariable String category) {
        return questionService.getALlQuestionByCategory(category);
    }

    @DeleteMapping("deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Long id) {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody QuestionUpdateDto dto) {
        return questionService.updateQuestion(id,dto);
    }

    @GetMapping("getQuestionsForQuiz")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestBody String category, @RequestBody int noOfQuesions) {
        return questionService.QuestionsForQuiz(category,noOfQuesions);
    }
    @PostMapping("getQuestionsFromId")
    public ResponseEntity<List<QuestionDto>> getQuestionsFromId(@RequestBody List<Integer>questionsId) {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionsId);
    }
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<ResponseDto> responses) {
        return questionService.getScore(responses);
    }
}
