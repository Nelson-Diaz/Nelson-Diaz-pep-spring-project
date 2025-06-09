package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    
    @ExceptionHandler({ClientErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleClientError(ClientErrorException ex) {

    }

    @ExceptionHandler({UserExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleUserError(UserExistsException ex) {

    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleFailedLogin(UnauthorizedException ex) {
        
    }

}
