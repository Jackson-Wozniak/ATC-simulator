package com.atcsimulator.app.core.exceptions.types;

import com.atcsimulator.app.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message, String clientFriendlyMessage){
        super(HttpStatus.NOT_FOUND, message, clientFriendlyMessage);
    }

    public NotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
