package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
public class SessionByDateFilter implements SessionFilter {
    private final LocalDate date;

    public SessionByDateFilter(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    public SessionByDateFilter(LocalDate date) {
        this.date = date;
    }

    @Override
    public void filter(List<MovieSession> list) {
        List<MovieSession> filteredList =
                list.stream().filter(s -> s.getLocalDateTime().toLocalDate().equals(date)).collect(Collectors.toList());
        list.clear();
        list.addAll(filteredList);
    }
}
