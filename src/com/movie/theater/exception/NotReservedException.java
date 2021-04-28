package com.movie.theater.exception;

public class NotReservedException extends Exception {
    public NotReservedException(){
        super ("This place is not reserved.");
    }

    public NotReservedException(String message){
        super(message);
    }
}
