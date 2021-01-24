package com.margolin.project.servicemanager.app.main.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class GeneralException extends Exception{

    private String errorMessage;
    private HttpStatus httpStatus;
}
