package com.mcode.moneymieassessment.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class MMAExceptions extends RuntimeException {
    private final HttpStatus httpStatus;

    public MMAExceptions(String message) {
        super(message);
       this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public MMAExceptions(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
    }
}
