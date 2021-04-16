import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieSession {
    private LocalDateTime localDateTime;
    private List<Place> reservationState;
    private double priceForSession;
    private Duration duration;
    private Movie movie;



    public MovieSession(Movie movie, LocalDateTime localDateTime, Duration duration) {
        this.movie = movie;
        this.localDateTime = localDateTime;
        reservationState = new ArrayList<>();
        for (int number = 1; number <= 100; number++) {
            reservationState.add(new Place(number));

        }
    }

    public List<Place> getFreePlaces() {
        List<Place> freePlaces = new ArrayList<>();
        reservationState.forEach((place) -> {
            if (!place.isReserved()) {
                freePlaces.add(place);
            }
        });
        return freePlaces;
    }

    public List<Place> getReservedPlaces() {
        List<Place> reservedPlaces = new ArrayList<>();
        reservationState.forEach((place) -> {
            if (place.isReserved()) {
                reservedPlaces.add(place);
            }
        });
        return reservedPlaces;
    }

    public void reserve(Place place) throws AlreadyReservedException{
        Place p = reservationState.get(place.getNumber() - 1);
        if (!p.isReserved()) {
            p.setReserved(true);
        } else {
            throw new AlreadyReservedException();
        }
    }

    public void cancelReservation(Place place) throws NotReservedException{
        Place p = reservationState.get(place.getNumber() - 1);
        if (p.isReserved()) {
            p.setReserved(false);
        } else {
            throw new NotReservedException();
        }
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



}
