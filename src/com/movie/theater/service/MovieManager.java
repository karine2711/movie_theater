package com.movie.theater.service;

import com.movie.theater.exception.AlreadyInMovieListException;
import java.util.*;
import com.movie.theater.model.Director;
import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;

public class MovieManager {

    private List<Movie> movieList= new ArrayList<>();
    private List<Movie> directorList= new ArrayList<>();


    public void addMovie (String name, Director director, Genre genre) throws AlreadyInMovieListException {
        Movie movie = new Movie (name, director, genre);
        addMovie(movie);
    }

    public void addMovie (Movie movie) throws AlreadyInMovieListException{
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
