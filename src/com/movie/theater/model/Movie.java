package com.movie.theater.model;

import java.io.Serializable;
import java.util.Objects;

public class Movie implements Serializable {
    private String name;
    private Director director;
    private Genre genre;

    //TODO: Add
    private static final long serialVersionUID=1L;

    public Movie(String name, Director director, Genre genre) {
        this.name = name;
        this.director = new Director(director);
        this.genre = genre;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    @Override
    public String toString() {
        return name + " is a "+ genre + " directed by " + director + genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, director, genre);
    }
}
