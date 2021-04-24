import java.util.*;

public class MovieManager {

    ArrayList<Movie> movieList= new ArrayList<>();

    public void addMovie (String name, Director director, Genre genre) throws AlreadyInMovieListException {
        Movie movie = new Movie (name, director, genre);

        if (movieList.contains(movie)) {
            movieList.add(movie);
        } else {
            throw new AlreadyInMovieListException();
        }
    }

    public void deleteMovie (String name, Director director, Genre genre){
        Movie movie = new Movie (name, director, genre);
        movieList.remove(movie);

    }


}
