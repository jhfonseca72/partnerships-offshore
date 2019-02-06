package com.vivid.partnerships.interview.controllers;

import com.vivid.partnerships.interview.dto.HttpError;
import com.vivid.partnerships.interview.execptions.EventException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
@RequestMapping(produces = "application/json")
public class ExceptionController {

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(EventException.class)
    public ResponseEntity<HttpError> notFoundException(final EventException e) {
        return error(e, HttpStatus.CONFLICT, e.getMessage());
    }

    /**
     * @param exception
     * @param httpStatus
     * @param logRef
     * @return
     */
    private ResponseEntity<HttpError> error(final Exception exception, final HttpStatus httpStatus,
                                            final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new HttpError(logRef, message), httpStatus);
    }
}
