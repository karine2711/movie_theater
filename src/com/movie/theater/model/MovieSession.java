package com.movie.theater.model;

import com.movie.theater.exception.AlreadyReservedException;
import com.movie.theater.exception.NotReservedException;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieSession implements Filterable, Serializable {
    private LocalDateTime localDateTime;
    private ArrayList<Seat> reservationState;
    private double priceForSession;
    private Duration duration;
    private Movie movie;



    public MovieSession(Movie movie, LocalDateTime localDateTime, Duration duration, double priceForSession) {
        this.movie = movie;
        this.localDateTime = localDateTime;
        reservationState = new ArrayList<>();
        for (int number = 1; number <= 100; number++) {
            reservationState.add(new Seat(number));

        }
        this.priceForSession=priceForSession;
    }

    public List<Seat> getFreePlaces() {
        List<Seat> freePlaces = new ArrayList<>();
        reservationState.forEach((place) -> {
            if (!place.isReserved()) {
                freePlaces.add(place);
            }
        });
        return freePlaces;
    }

    public List<Seat> getReservedPlaces() {
        List<Seat> reservedPlaces = new ArrayList<>();
        reservationState.forEach((place) -> {
            if (place.isReserved()) {
                reservedPlaces.add(place);
            }
        });
        return reservedPlaces;
    }

    public void reserve(int seat) throws AlreadyReservedException {
        Seat p = reservationState.get(seat - 1);
        if (!p.isReserved()) {
            p.setReserved(true);
        } else {
            throw new AlreadyReservedException("Place "+seat+ " is already reserved!");
        }
    }

    public void cancelReservation(int seatNumber) throws NotReservedException {
        Seat p = reservationState.get(seatNumber - 1);
        if (p.isReserved()) {
            p.setReserved(false);
        } else {
            throw new NotReservedException();
        }
    }

    public boolean isReserved(int placeNumber){
        Seat p = reservationState.get(placeNumber - 1);
        return p.isReserved();
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public double getPriceForSession() {
        return priceForSession;
    }

    public void setPriceForSession(double priceForSession) {
        this.priceForSession = priceForSession;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getEndTime() {
        return localDateTime.plus(duration);
    }

    @Override
    public String toString() {
        return movie.getName()+" on " + localDateTime.getDayOfMonth() + "th of "+ localDateTime.getMonth().name().toLowerCase()+". Price: " + priceForSession;
    }
}
