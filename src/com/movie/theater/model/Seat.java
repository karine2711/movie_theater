package com.movie.theater.model;

import java.io.Serializable;

public class Seat implements Serializable {
    private static final long serialVersionUID = 4L;
    private final int number;
    private boolean isReserved;

    public Seat(int number) {
        this.number = number;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public int getNumber() {
        return number;
    }


    @Override
    public String toString() {
        return "[ " + number +
                "," + (isReserved ? "reserved" : "free") +
                " ]";
    }
}
