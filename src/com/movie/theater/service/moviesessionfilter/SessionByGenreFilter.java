package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.Genre;
import com.movie.theater.model.MovieSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//similar to MovieByGenre filter
public class SessionByGenreFilter implements SessionFilter {
    private final Set<Genre> genres = new HashSet<>();

//    private final Genre genre;

    public SessionByGenreFilter(Genre genre) {
        genres.add(genre);
    }

    public SessionByGenreFilter() {
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
    }

    public void reset() {
        genres.clear();
    }

    @Override
    public void filter(List<MovieSession> list) {
        if (genres.isEmpty()) return;
        List<MovieSession> temp =
                list.stream().filter(s -> genres.contains(s.getMovie().getGenre())).collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
    }
}
