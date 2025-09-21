package com.atcsimulator.app.core.exceptions;

import com.atcsimulator.app.core.payload.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> notFoundException(BaseException ex){
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                ex.getClientFriendlyMessage(),
                ex.isClientFriendly()
        );
        return ResponseEntity.status(ex.getStatus()).body(error);
    }
}
