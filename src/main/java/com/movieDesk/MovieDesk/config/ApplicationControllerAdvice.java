package com.movieDesk.MovieDesk.config;

import com.movieDesk.MovieDesk.exeption.UserNameOrPasswordIvalidExeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UserNameOrPasswordIvalidExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFound(UserNameOrPasswordIvalidExeption ex){
        return ex.getMessage();
    }

}
