package com.movie.theater.service.moviefilter;

import com.movie.theater.model.Director;
import com.movie.theater.model.Movie;
import java.util.List;
import java.util.stream.Collectors;

public class MovieByDirectorFilter implements MovieFilter {
    private final Director director;

    public MovieByDirectorFilter(Director director) {
        this.director = director;
    }

    public MovieByDirectorFilter(String firstName, String lastName) {
        this.director = new Director(firstName, lastName);
    }

    @Override
    public void filter(List<Movie> list) {
        List<Movie> temp = list.stream().filter(s -> s.getDirector().equals(director)).collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
    }
}
