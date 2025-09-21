package com.atcsimulator.app.core.exceptions.types;

import com.atcsimulator.app.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(String message, String clientFriendlyMessage){
        super(HttpStatus.BAD_REQUEST, message, clientFriendlyMessage);
    }

    public BadRequestException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
