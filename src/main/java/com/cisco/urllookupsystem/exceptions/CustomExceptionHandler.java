package com.cisco.urllookupsystem.exceptions;

import com.cisco.urllookupsystem.utility.UrlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked", "rawtypes"})
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleUrlNotFoundException(UrlRecordNotFound ex, WebRequest request) {
        ErrorMessage exceptionResponse = new ErrorMessage(UrlConstants.ERROR_RESPONSE_MESSAGE, UrlConstants.ERROR_RESPONSE_DETAILS);
        return new ResponseEntity<ErrorMessage>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ErrorMessage> handleBadRequestException(InvalidInputException ex, WebRequest request) {
        ErrorMessage exceptionResponse = new ErrorMessage(UrlConstants.ERROR_RESPONSE_INVALID_MESSAGE, UrlConstants.ERROR_RESPONSE_INVALID_DETAILS);
        return new ResponseEntity<ErrorMessage>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}