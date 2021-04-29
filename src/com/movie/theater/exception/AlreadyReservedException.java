package com.movie.theater.exception;

public class AlreadyReservedException extends Exception {
    public AlreadyReservedException(){
        super ("Place is already reserved! Please choose another one.");
    }

    public AlreadyReservedException(String message){
        super(message);
    }
}
