package com.movie.theater.service.moviefilter;

import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieByGenreFilter implements MovieFilter {
    private final Set<Genre> genres = new HashSet<>();

    public MovieByGenreFilter() {
    }

    @Override
    public void filter(List<Movie> list) {
        if (genres.isEmpty()) return;
        List<Movie> temp =
                list.stream().filter(s -> genres.contains(s.getGenre())).collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieByGenreFilter that = (MovieByGenreFilter) o;
        return genres.equals(that.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genres);
    }

    @Override
    public String toString() {
        return "Filter by Genres: " + genres;
    }
}
