package com.movie.theater.service;

import com.movie.theater.exception.AlreadyInMovieListException;
import com.movie.theater.model.Director;
import com.movie.theater.model.Movie;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class MovieManager {

    public static final String MOVIE_LIST_FILE = "src/resources/movie-list.txt";
    public static final String MOVIE_LIST_DIR = "src/resources/movies/";

    private static final MovieManager MOVIE_MANAGER = new MovieManager();
    public ArrayList<Movie> MOVIE_LIST;

    private MovieManager() {
        File file = new File(MOVIE_LIST_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            MOVIE_LIST = SerializationUtil.deserializeMovies();
        } catch (EOFException e) {
            MOVIE_LIST = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize Movie Manager!");
        }
    }

    public static MovieManager getMovieManager() {
        return MOVIE_MANAGER;
    }

    public void addMovie(Movie movie) throws AlreadyInMovieListException, IOException {
        if (!MOVIE_LIST.contains(movie)) {
            MOVIE_LIST.add(movie);
        } else {
            throw new AlreadyInMovieListException();
        }
        SerializationUtil.serializeMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        MOVIE_LIST.remove(movie);
        File moviesDir = new File(MOVIE_LIST_DIR);
        File sessionsDir = new File("src/resources/sessions");
        Arrays.stream(moviesDir.listFiles()).filter(f -> f.getName().startsWith(movie.getName().replaceAll(" ", "_"))).forEach(f -> f.delete());
        Arrays.stream(sessionsDir.listFiles()).filter(f -> f.getName().startsWith(movie.getName().replaceAll(" ", "_"))).forEach(f -> f.delete());
        try {
            SerializationUtil.deserializeSessions();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public List<Movie> getMovieList() {
        return MOVIE_LIST;
    }

    public List<Director> getDirectorList() {
        return MOVIE_LIST.stream().map(Movie::getDirector).collect(Collectors.toList());
    }

}
