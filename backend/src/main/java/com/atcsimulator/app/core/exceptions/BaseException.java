package com.atcsimulator.app.core.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public abstract class BaseException extends RuntimeException{
    private HttpStatus status;
    private boolean isClientFriendly;
    private String clientFriendlyMessage;
    private String callingMethod;

    public BaseException(HttpStatus status,
                         String message,
                         String clientFriendlyMessage){
        super(message);
        this.status = status;
        this.isClientFriendly = true;
        this.clientFriendlyMessage = clientFriendlyMessage;
    }

    public BaseException(HttpStatus status,
                         String message){
        super(message);
        this.status = status;
        this.isClientFriendly = false;
        this.clientFriendlyMessage = message;
    }
}
