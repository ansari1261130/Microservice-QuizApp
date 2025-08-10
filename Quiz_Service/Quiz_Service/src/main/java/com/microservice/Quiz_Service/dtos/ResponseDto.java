package com.microservice.Quiz_Service.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {
    private Long id;
    private String response;
}
