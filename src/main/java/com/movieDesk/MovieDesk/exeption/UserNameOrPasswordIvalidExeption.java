package com.movieDesk.MovieDesk.exeption;

public class UserNameOrPasswordIvalidExeption extends RuntimeException {

    public UserNameOrPasswordIvalidExeption(String message) {
        super(message);
    }

}
