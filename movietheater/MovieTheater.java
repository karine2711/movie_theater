import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MovieTheater {
    private List<Movie> movies;

    public static void main(String[] args) {
        Movie movie1 = new Movie("movie 1", new Director("name1", "surname1"), Genre.ACTION);
        MovieSession session1 = new MovieSession(movie1, LocalDateTime.now(),
                Duration.ofHours(2), 15);
        MovieSession session2 =
                new MovieSession(new Movie("movie 2", new Director("name2", "surname2"), Genre.ROMANCE), LocalDateTime.now(),
                        Duration.ofHours(1), 20);
        MovieSession session3 = new MovieSession(movie1,
                LocalDateTime.of(2021, 2, 21, 15, 23), Duration.ofHours(2), 50);
        MovieSession session4 = new MovieSession(movie1,
             LocalDateTime.now(), Duration.ofHours(2), 100);

        List<MovieSession> sessions = List.of(session1, session2, session3);
        SessionFilterer filterer = new SessionFilterer(sessions);
        System.out.println("Before filter: " + sessions);
        List<MovieSession> filteredSessions = filterer
                .filterByGenre(Genre.ACTION)
                .filterByDate(LocalDate.now())
                .filterByPrice(5,50)
                .getFilteredResult();
        System.out.println("After filter: " + filteredSessions);
    }
}
