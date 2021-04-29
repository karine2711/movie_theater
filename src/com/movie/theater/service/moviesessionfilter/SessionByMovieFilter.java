package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import java.util.List;
import java.util.stream.Collectors;

public class SessionByMovieFilter implements SessionFilter {
    private final Movie movie;

    public SessionByMovieFilter(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void filter(List<MovieSession> list) {
        List<MovieSession> temp =
                list.stream().filter((s) -> s.getMovie().equals(movie)).collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
    }


}
