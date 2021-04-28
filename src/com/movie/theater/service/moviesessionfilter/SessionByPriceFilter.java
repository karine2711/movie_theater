package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.MovieSession;
import java.util.List;
import java.util.stream.Collectors;

public class SessionByPriceFilter implements SessionFilter {
    private final int minPrice;
    private final int maxPrice;

    public SessionByPriceFilter(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public void filter(List<MovieSession> list) {
        List<MovieSession> temp =
                list.stream().filter(s -> s.getPriceForSession() >= minPrice && s.getPriceForSession() <= maxPrice)
                        .collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
    }
}
