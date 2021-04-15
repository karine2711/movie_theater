import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class MovieTheater {
    private List<Movie> movies;

    public static void main(String[] args) {
        MovieSession session = new MovieSession(new Movie("gdg"), LocalDateTime.now(), Duration.ofHours(2));
        Movie movie = new Movie("movie");
//        movie.sessionList=List.of(session);

        session.setLocalDateTime(LocalDateTime.now());
        System.out.println(session.getFreePlaces().size());
        session.reserve(new Place(1));
        System.out.println(session.getFreePlaces().size());
        session.reserve(new Place(1));
    }
}
