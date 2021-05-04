package com.movie.theater.service.moviefilter;

import com.movie.theater.model.Movie;
import java.util.List;
import java.util.Set;

public interface MovieFilter {

    void filter(List<Movie> list);

}
