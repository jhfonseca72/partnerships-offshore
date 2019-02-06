package com.vivid.partnerships.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpError {
    private final String exception;
    private final String message;
}