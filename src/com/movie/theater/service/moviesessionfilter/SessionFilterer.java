package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.MovieSession;
import java.util.List;

public class SessionFilterer {
    private final List<MovieSession> movieSessions;

    public SessionFilterer(List<MovieSession> movieSessions) {
        this.movieSessions = movieSessions;
    }

    public void filter(List<SessionFilter> filterList) {
        filterList.forEach(filter -> filter.filter(movieSessions));
    }


    public void filter(SessionFilter... filterList) {
        filter(List.of(filterList));
    }
}
