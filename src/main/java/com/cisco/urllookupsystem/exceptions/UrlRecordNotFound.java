package com.cisco.urllookupsystem.exceptions;

import com.cisco.urllookupsystem.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UrlRecordNotFound extends RuntimeException
{
    public UrlRecordNotFound(String exception) {
        new Exception();
    }

    @ExceptionHandler(UrlRecordNotFound.class)
    public final ResponseEntity<Object> handleUrlNotFoundException(UrlRecordNotFound ex) {
        ErrorResponse error = new ErrorResponse("Record Not Found", "This domain has no record for any of the urls");
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}