package com.microservice.Question_Service.service;

import com.microservice.Question_Service.dtos.QuestionDto;
import com.microservice.Question_Service.dtos.QuestionUpdateDto;
import com.microservice.Question_Service.dtos.ResponseDto;
import com.microservice.Question_Service.entities.Question;
import com.microservice.Question_Service.mapper.QuestionMapper;
import com.microservice.Question_Service.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            List<Question> questions = questionRepository.findAll();
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepository.save(question);
            return ResponseEntity.ok("Question Added Successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<List<Question>> getALlQuestionByCategory(String category) {
        try {
            List<Question> questions = questionRepository.findQuestionByCategory(category);
            return ResponseEntity.ok(questions);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> deleteQuestion(Long id) {
        try {
            questionRepository.deleteById(id);
            return ResponseEntity.ok("Question Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Question> updateQuestion(Long id, QuestionUpdateDto dto) {
        try {
            Question question = questionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));

            questionMapper.updateQuestionFromDto(dto, question);
            questionRepository.save(question);

            return ResponseEntity.ok(question);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<List<Integer>> QuestionsForQuiz(String category, int noOfQuestions) {
        List<Integer> questions = questionRepository.getQuestionsByCategory(category,noOfQuestions);
        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<List<QuestionDto>> getQuestionsFromId(List<Integer> questionsId) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id: questionsId) {
            Optional<Question> question = questionRepository.findById(Long.valueOf(id));
            if(question.isEmpty()) return ResponseEntity.badRequest().build();
            questions.add(question.get());
        }
        for (Question question: questions) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setId(question.getId());
            questionDto.setQuestion(question.getQuestion());
            questionDto.setOption1(question.getOption1());
            questionDto.setOption2(question.getOption2());
            questionDto.setOption3(question.getOption3());
            questionDto.setOption4(question.getOption4());
            questionDtos.add(questionDto);
        }
        return ResponseEntity.ok(questionDtos);
    }

    public ResponseEntity<Integer> getScore(List<ResponseDto> responses) {
        try {
            int score = 0;
            for (ResponseDto response:responses) {
                Optional<Question> question = questionRepository.findById(response.getId());
                if(response.getResponse().equalsIgnoreCase(question.get().getAnswer())) {
                    score++;
                }
            }
            return ResponseEntity.ok(score);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
