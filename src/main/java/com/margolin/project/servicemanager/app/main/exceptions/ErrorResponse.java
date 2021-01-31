package com.margolin.project.servicemanager.app.main.exceptions;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class ErrorResponse
{
    public ErrorResponse(String message, List<String> details, Map<String, Set<String>> map) {
        super();
        this.message = message;
        this.details = details;
        this.inputErrors = map;
    }
    private String message;
    private List<String> details;
    Map<String, Set<String>> inputErrors;
}