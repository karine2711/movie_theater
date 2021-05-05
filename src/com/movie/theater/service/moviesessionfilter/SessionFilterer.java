package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.MovieSession;

import java.util.ArrayList;
import java.util.List;

public class SessionFilterer {
    private final List<MovieSession> movieSessions = new ArrayList<>();

    public SessionFilterer(List<MovieSession> movieSessions) {
        this.movieSessions.addAll(movieSessions);
    }

    public SessionFilterer filter(List<SessionFilter> filterList) {
        filterList.forEach(filter -> filter.filter(movieSessions));
        return this;
    }

    public void filter(SessionFilter... filterList) {
        filter(List.of(filterList));
    }

    public List<MovieSession> getResult() {
        return movieSessions;
    }
}
