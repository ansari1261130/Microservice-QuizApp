package com.microservice.Quiz_Service.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionDto {
    private Long id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
