package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.MovieSession;
import java.util.List;

public interface SessionFilter {

     void filter(List<MovieSession> list);

}
