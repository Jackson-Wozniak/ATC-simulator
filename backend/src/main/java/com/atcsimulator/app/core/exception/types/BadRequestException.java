package com.atcsimulator.app.core.exception.types;

import com.atcsimulator.app.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(String message, String clientFriendlyMessage){
        super(HttpStatus.BAD_REQUEST, message, clientFriendlyMessage);
    }

    public BadRequestException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
