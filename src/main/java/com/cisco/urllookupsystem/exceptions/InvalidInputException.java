package com.cisco.urllookupsystem.exceptions;

import com.cisco.urllookupsystem.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class InvalidInputException extends NullPointerException {

    public InvalidInputException(String exception) {
        new NullPointerException();
    }

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
        ErrorResponse error = new ErrorResponse("Please request parametrs and request url", "Request parametrs are either wrong or misiing");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
