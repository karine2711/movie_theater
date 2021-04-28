package com.movie.theater.service.moviefilter;

import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import java.util.List;
import java.util.stream.Collectors;

public class MovieByGenreFilter implements MovieFilter {
    private final Genre genre;

    public MovieByGenreFilter(Genre genre) {
        this.genre = genre;
    }

    @Override
    public void filter(List<Movie> list) {
        List<Movie> temp =
                list.stream().filter(s -> s.getGenre().equals(genre)).collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
    }
}
