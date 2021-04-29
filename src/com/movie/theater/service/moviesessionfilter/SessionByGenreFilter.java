package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.Genre;
import com.movie.theater.model.MovieSession;
import java.util.List;
import java.util.stream.Collectors;

public class SessionByGenreFilter implements SessionFilter {

    private final Genre genre;

    public SessionByGenreFilter(Genre genre) {
        this.genre = genre;
    }

    @Override
    public void filter(List<MovieSession> list) {
        List<MovieSession> temp =
                list.stream().filter(movieSession -> movieSession.getMovie().getGenre().equals(genre)).collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
    }
}
