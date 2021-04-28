package com.movie.theater.service;

import com.movie.theater.exception.AlreadyInMovieListException;
import com.movie.theater.model.Director;
import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class MovieManager {

    private final ArrayList<Movie> MOVIE_LIST;
    private List<Movie> directorList = new ArrayList<>();
    private static final String MOVIE_LIST_FILE = "src\\resources\\movie-list.txt";
    private static final MovieManager MOVIE_MANAGER = new MovieManager();

    private MovieManager() {
        try {
            MOVIE_LIST = (ArrayList<Movie>) SerializationUtil.readFromFile(MOVIE_LIST_FILE);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize Movie Manager!");
        }
    }

    public static MovieManager getMovieManager() {
        return MOVIE_MANAGER;
    }

    public void addMovie(String name, Director director, Genre genre) throws AlreadyInMovieListException, IOException {
        Movie movie = new Movie(name, director, genre);
        addMovie(movie);
        SerializationUtil.writeToFile(MOVIE_LIST_FILE, MOVIE_LIST);
    }

    public void addMovie(Movie movie) throws AlreadyInMovieListException, IOException {
        if (MOVIE_LIST.contains(movie)) {
            MOVIE_LIST.add(movie);
        } else {
            throw new AlreadyInMovieListException();
        }
        SerializationUtil.writeToFile(MOVIE_LIST_FILE, MOVIE_LIST);
    }

    public void deleteMovie(String name, Director director, Genre genre) throws IOException {
        Movie movie = new Movie(name, director, genre);
        MOVIE_LIST.remove(movie);
        SerializationUtil.writeToFile(MOVIE_LIST_FILE, MOVIE_LIST);
    }


}
