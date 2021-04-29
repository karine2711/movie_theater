package com.movie.theater.service;

import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SessionManager {
    private final ArrayList<MovieSession> SESSION_LIST;
    private static final String SESSION_LIST_FILE = "src\\resources\\session-list.txt";
    private static final SessionManager SESSION_MANAGER = new SessionManager();

    private SessionManager() {
        try {
            SESSION_LIST = (ArrayList<MovieSession>) SerializationUtil.readFromFile(SESSION_LIST_FILE);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize Movie Manager!");
        }
    }

    public static SessionManager getSessionManager() {
        return SESSION_MANAGER;
    }

    public void addSession(MovieSession session) throws IOException {
        LocalDateTime start = session.getLocalDateTime();
        LocalDateTime end = session.getEndTime();
        List<MovieSession> overlaps = SESSION_LIST.stream().filter(s -> overlaps(s, session)).collect(Collectors.toList());
        if (overlaps.isEmpty()) {
            SESSION_LIST.add(session);
            SerializationUtil.writeToFile(SESSION_LIST_FILE, SESSION_LIST);
        } else {
            StringBuilder builder = new StringBuilder("Your movie overlaps with { \n");
            for (MovieSession movieSession : overlaps) {
                builder.append(movieSession.getMovie().getName())
                        .append(" - from ")
                        .append(movieSession.getLocalDateTime()).append(" to ")
                        .append(movieSession.getEndTime()).append(" \n");
            }
            builder.append("}");

            throw new RuntimeException(builder.toString());
        }
    }

    public void addSession(Movie movie, LocalDateTime localDateTime,
                           Duration duration, double priceForSession) throws IOException {
        MovieSession session = new MovieSession(movie, localDateTime,
                duration, priceForSession);
        addSession(session);
    }

    public void deleteSession(Movie movie, LocalDateTime localDateTime,
                              Duration duration, double priceForSession) throws IOException {
        MovieSession session = new MovieSession(movie, localDateTime,
                duration, priceForSession);
        SESSION_LIST.remove(session);
        SerializationUtil.writeToFile(SESSION_LIST_FILE, SESSION_LIST);
    }


    private boolean overlaps(MovieSession session1, MovieSession session2) {
        LocalDateTime start1 = session1.getLocalDateTime();
        LocalDateTime end1 = start1.plus(session1.getDuration());
        LocalDateTime start2 = session2.getLocalDateTime();
        LocalDateTime end2 = start2.plus(session2.getDuration()); //Change variable names
        boolean firstStartInTheMiddleOfSecond = isBetween(start1, start2, end2) || isBetween(end1, start2, end2);
        boolean secondStartInTheMiddleOfFirst = isBetween(start2, start1, end1) || isBetween(end2, start1, end1);
        boolean overlaps = start1.equals(start2) || firstStartInTheMiddleOfSecond || secondStartInTheMiddleOfFirst;
        return overlaps;
    }

    private boolean isBetween(LocalDateTime time, LocalDateTime start, LocalDateTime end) {
        return time.isAfter(start) && time.isBefore(end);
    }


}
