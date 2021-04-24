import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SessionFilterer {
    private List<MovieSession> movieSessions = new ArrayList<>();

    public SessionFilterer(List<MovieSession> movieSessions) {
        this.movieSessions = movieSessions;
    }

    public SessionFilterer filterByMovie(Movie movie) {
        this.movieSessions = movieSessions.stream().filter(s -> s.getMovie().equals(movie)).collect(Collectors.toList());
        return this;
    }

    public SessionFilterer filterByDate(LocalDate date) {
        this.movieSessions =
                movieSessions.stream().filter(s -> s.getLocalDateTime().toLocalDate().equals(date)).collect(Collectors.toList());
        return this;
    }

    public SessionFilterer filterByGenre(Genre genre) {
        this.movieSessions =
                movieSessions.stream().filter(s -> s.getMovie().getGenre().equals(genre)).collect(Collectors.toList());
        return this;
    }

    public SessionFilterer filterByPrice(double minPrice, double maxPrice) {
        this.movieSessions =
                movieSessions.stream().filter(s -> s.getPriceForSession()>=minPrice && s.getPriceForSession()<=maxPrice).collect(Collectors.toList());
        return this;
    }
    public List<MovieSession> getFilteredResult(){
        return movieSessions;
    }

}
