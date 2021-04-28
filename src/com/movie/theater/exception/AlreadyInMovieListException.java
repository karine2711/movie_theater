package com.movie.theater.exception;

public class AlreadyInMovieListException extends Exception {
    public AlreadyInMovieListException(){
        super ("The movie you want to add is already in the list. Try another one.");
    }

    public AlreadyInMovieListException(String message){
        super(message);
    }
}
