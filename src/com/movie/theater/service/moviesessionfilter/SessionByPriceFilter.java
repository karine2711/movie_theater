package com.movie.theater.service.moviesessionfilter;

import com.movie.theater.model.MovieSession;

import java.util.List;
import java.util.stream.Collectors;

public class SessionByPriceFilter implements SessionFilter {
    private int minPrice = 100;
    private int maxPrice = 9990;

    @Override
    public void filter(List<MovieSession> list) {
        List<MovieSession> temp =
                list.stream().filter(s -> s.getPriceForSession() >= minPrice && s.getPriceForSession() <= maxPrice)
                        .collect(Collectors.toList());
        list.clear();
        list.addAll(temp);
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void reset() {
        minPrice = 0;
        maxPrice = 9990;
    }


}
