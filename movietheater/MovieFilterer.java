import java.util.*;
import java.util.stream.Collectors;

public class MovieFilterer {

    private List<Movie> movies = new ArrayList<>();

    public MovieFilterer(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieFilterer filterByDirector(Director director) {
        this.movies = movies.stream().filter(s -> s.getDirector().equals(director)).collect(Collectors.toList());
        return this;
    }

    public MovieFilterer filterByGenre(Genre genre) {
        this.movies =
                movies.stream().filter(s -> s.getGenre().equals(genre)).collect(Collectors.toList());
        return this;
    }

}
