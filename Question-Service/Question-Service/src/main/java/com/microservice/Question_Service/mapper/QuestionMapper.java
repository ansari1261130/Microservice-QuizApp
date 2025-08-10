package com.microservice.Question_Service.mapper;

import com.microservice.Question_Service.entities.Question;
import com.microservice.Question_Service.dtos.QuestionUpdateDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuestionFromDto(QuestionUpdateDto dto, @MappingTarget Question question);
}

