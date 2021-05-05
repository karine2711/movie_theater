package com.movie.theater.service.moviefilter;

import com.movie.theater.model.Director;
import com.movie.theater.model.Movie;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieByDirectorFilter implements MovieFilter {
    private final Set<Director> directors = new HashSet<>();

    public MovieByDirectorFilter() {
    }

    @Override
    public void filter(List<Movie> list) {
        if (directors.isEmpty()) return;
        List<Movie> temp = list.stream()
                .filter(s -> directors.contains(s.getDirector()))
                .collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
    }

    public void addDirector(Director director) {
        directors.add(director);
    }

    public void removeDirector(Director director) {
        directors.remove(director);
    }

    public void reset() {
        directors.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieByDirectorFilter that = (MovieByDirectorFilter) o;
        return directors.equals(that.directors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directors);
    }

    @Override
    public String toString() {
        return "Filter by Director: " +
                directors;
    }
}
