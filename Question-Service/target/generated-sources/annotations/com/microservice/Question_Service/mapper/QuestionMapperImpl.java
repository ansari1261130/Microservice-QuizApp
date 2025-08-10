package com.microservice.Question_Service.mapper;

import com.microservice.Question_Service.dtos.QuestionUpdateDto;
import com.microservice.Question_Service.entities.Question;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-10T13:24:26+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public void updateQuestionFromDto(QuestionUpdateDto dto, Question question) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getCategory() != null ) {
            question.setCategory( dto.getCategory() );
        }
        if ( dto.getQuestion() != null ) {
            question.setQuestion( dto.getQuestion() );
        }
        if ( dto.getOption1() != null ) {
            question.setOption1( dto.getOption1() );
        }
        if ( dto.getOption2() != null ) {
            question.setOption2( dto.getOption2() );
        }
        if ( dto.getOption3() != null ) {
            question.setOption3( dto.getOption3() );
        }
        if ( dto.getOption4() != null ) {
            question.setOption4( dto.getOption4() );
        }
        if ( dto.getAnswer() != null ) {
            question.setAnswer( dto.getAnswer() );
        }
    }
}
