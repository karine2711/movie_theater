package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.MovieSession;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SessionByDateFilter implements SessionFilter {
    private LocalDate date;

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public void reset(){
        this.date=null;
    }
    @Override
    public void filter(List<MovieSession> list) {
        if (date == null) return;
        List<MovieSession> filteredList =
                list.stream().filter(s -> s.getLocalDateTime().toLocalDate().equals(date)).collect(Collectors.toList());
        list.clear();
        list.addAll(filteredList);
    }
}
