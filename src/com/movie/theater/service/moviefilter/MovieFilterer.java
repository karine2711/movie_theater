package com.movie.theater.service.moviefilter;

import com.movie.theater.model.Movie;
import java.util.List;

public class MovieFilterer {

    private final List<Movie> movies;

    public MovieFilterer(List<Movie> movies) {
        this.movies = movies;
    }

    public void filter(List<MovieFilter> filterList) {
        filterList.forEach(filter -> filter.filter(movies));
    }


    public void filter(MovieFilter... filterList) {
        filter(List.of(filterList));
    }

}
