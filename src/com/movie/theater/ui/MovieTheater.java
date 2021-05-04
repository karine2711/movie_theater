package com.movie.theater.ui;

import com.movie.theater.model.Director;
import com.movie.theater.model.Genre;
import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import com.movie.theater.service.SerializationUtil;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieTheater {
    private List<Movie> movies;

//    public static void main(String... args) {
//        SessionManager manager=SessionManager.getSessionManager();
//        System.out.println(manager.getSESSION_LIST());
//    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Movie movie1 = new Movie("movie 1", new Director("name1", "surname1"), Genre.ACTION);
        Movie movie2 = new Movie("movie 2", new Director("name2", "surname2"), Genre.ROMANCE);

        Movie movie3 = new Movie("movie 2", new Director("name2", "surname2"), Genre.ROMANCE);
        Movie movie4 = new Movie("movie 2", new Director("name2", "surname2"), Genre.ROMANCE);
        Movie movie5 = new Movie("movie 2", new Director("name2", "surname2"), Genre.ROMANCE);
        Movie movie6 = new Movie("movie 2", new Director("name2", "surname2"), Genre.ROMANCE);
        Movie movie7 = new Movie("movie 2", new Director("name2", "surname2"), Genre.ROMANCE);

        List<MovieSession> sessions = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("sldljldjvlvmlkblkblbljgljgoru roi lk gh gi hei ghi hgwi oighoi ", new Director("sdds", "fss"), Genre.COMEDY));
        for (int i = 0; i < 5; i++) {
            Movie movie = new Movie("movie" + i, new Director("name" + i, "surname1"), Genre.values()[i % 5]);
            movies.add(movie);
            sessions.add(new MovieSession(movie, LocalDateTime.of(2021, 5, i % 28 + 1, i % 24, i % 60), Duration.ofHours(2), i * 100));
        }


        MovieSession session1 = new MovieSession(movie1, LocalDateTime.now(),
                Duration.ofHours(2), 15);
//        MovieSession session2 =
//                new MovieSession(new Movie("movie 2", new Director("name2", "surname2"), Genre.ROMANCE), LocalDateTime.now(),
//                        Duration.ofHours(1), 20);
//        MovieSession session3 = new MovieSession(movie1,
//                LocalDateTime.of(2021, 2, 21, 15, 23), Duration.ofHours(2), 50);
//        MovieSession session4 = new MovieSession(movie1,
//                LocalDateTime.now(), Duration.ofHours(2), 100);
//
//        ArrayList<MovieSession> sessions =
//                new ArrayList<MovieSession>(List.of(session1, session2, session3, session4));
//        System.out.println(sessions);
//        SessionFilterer filterer = new SessionFilterer(sessions);
//        SessionByDateFilter byDateFilter=new SessionByDateFilter(LocalDate.now());
////        SessionByPriceFilter byPriceFilter=new SessionByPriceFilter(18,51);
//        filterer.filter(byDateFilter);
//        System.out.println(sessions);


//        ArrayList<MovieSession> movieSessions = (ArrayList<MovieSession>) SerializationUtil.readFromFile("src\\resources\\test.txt");

        SerializationUtil.writeToFile("src/resources/movie-list.txt", new ArrayList<>(movies));
//        System.out.println(movieSessions);
//        movieSessions.add(session1);
//        SerializationUtil.writeToFile("src\\resources\\test.txt", movieSessions);
//        System.out.println(sessions);
    }
}
