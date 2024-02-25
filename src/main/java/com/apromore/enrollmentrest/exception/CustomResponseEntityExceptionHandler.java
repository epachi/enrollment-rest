package com.apromore.enrollmentrest.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                httpServletRequest.getMethod() + ":" + httpServletRequest.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                httpServletRequest.getMethod() + ":" + httpServletRequest.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentEnrollmentException.class)
    public final ResponseEntity<ErrorDetails> handleStudentEnrollmentException(StudentEnrollmentException ex, WebRequest request) {
        HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                httpServletRequest.getMethod() + ":" + httpServletRequest.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
