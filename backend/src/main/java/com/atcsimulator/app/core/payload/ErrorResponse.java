package com.atcsimulator.app.core.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String clientMessage;
    private boolean isClientFriendly;
    private String callingMethod;
    private Instant timestamp;

    public ErrorResponse(String message, String clientMessage, boolean isClientFriendly){
        this.message = message;
        this.isClientFriendly = isClientFriendly;
        this.clientMessage = clientMessage;
        this.timestamp = Instant.now();
    }
}
