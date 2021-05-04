package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class SessionByMovieFilter implements SessionFilter {
    private final Set<Movie> movies = new HashSet<>();

    public SessionByMovieFilter(Movie movie) {
        movies.add(movie);
    }

    public SessionByMovieFilter() {

    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }


    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }


    @Override
    public void filter(List<MovieSession> list) {
        if (movies.isEmpty()) return;
        List<MovieSession> temp =
                list.stream().filter((MovieSession m) -> movies.contains(m.getMovie()))
                        .collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
    }


}
