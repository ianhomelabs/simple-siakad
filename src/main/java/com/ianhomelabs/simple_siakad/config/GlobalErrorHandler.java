package com.ianhomelabs.simple_siakad.config;

import com.ianhomelabs.simple_siakad.exception.BadRequestException;
import com.ianhomelabs.simple_siakad.exception.BaseErrorDetail;
import com.ianhomelabs.simple_siakad.exception.DataNotFoundException;
import com.ianhomelabs.simple_siakad.exception.InternalServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler {
    public ResponseEntity<BaseErrorDetail> handleBadRequestException(BadRequestException exception, WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<BaseErrorDetail> handleDataNotFoundException(DataNotFoundException exception, WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<BaseErrorDetail> handleInternalServerException(InternalServerException exception, WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
