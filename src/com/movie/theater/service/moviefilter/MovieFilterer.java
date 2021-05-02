package com.movie.theater.service.moviefilter;

import com.movie.theater.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieFilterer {

    private final List<Movie> movies = new ArrayList<>();

    public MovieFilterer(List<Movie> movies) {
        this.movies.addAll(movies);
    }

    public MovieFilterer filter(List<MovieFilter> filterList) {
        filterList.forEach(filter -> filter.filter(movies));
        return this;
    }

    public List<Movie> getResult(){
        return movies;
    }


    public void filter(MovieFilter... filterList) {
        filter(List.of(filterList));
    }

}
