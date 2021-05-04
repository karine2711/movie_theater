package com.movie.theater.exception;

public class OverlappingException extends RuntimeException {

    public OverlappingException() {
    }

    public OverlappingException(String message) {
        super(message);
    }
}
