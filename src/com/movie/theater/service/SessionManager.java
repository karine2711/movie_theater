package com.movie.theater.service;

import com.movie.theater.exception.OverlappingException;
import com.movie.theater.model.MovieSession;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SessionManager {
    public static final String SESSION_LIST_FILE = "src/resources/session-list.txt";
    private static final SessionManager SESSION_MANAGER = new SessionManager();
    public final ArrayList<MovieSession> SESSION_LIST;

    private SessionManager() {
        try {
            SESSION_LIST = SerializationUtil.deserializeSessions();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize Session Manager!");
        }
    }

    public static SessionManager getSessionManager() {
        return SESSION_MANAGER;
    }

    public List<MovieSession> getSessionList() {
        return SESSION_LIST;
    }

    public void addSession(MovieSession session) throws IOException {
        List<MovieSession> overlaps = SESSION_LIST
                .stream()
                .filter(s -> overlaps(s, session))
                .collect(Collectors.toList());
        if (overlaps.isEmpty()) {
            SESSION_LIST.add(session);
            SerializationUtil.serializeSession(session);
        } else {
            StringBuilder builder = new StringBuilder("Your movie overlaps with { \n");
            for (MovieSession movieSession : overlaps) {
                builder.append(movieSession.getMovie().getName())
                        .append(" - from ")
                        .append(movieSession.getLocalDateTime()).append(" to ")
                        .append(movieSession.getEndTime()).append(" \n");
            }
            builder.append("}");
            throw new OverlappingException(builder.toString());
        }
    }

    public void deleteSession(MovieSession session) throws IOException {
        SESSION_LIST.remove(session);
        File file = new File("src/resources/sessions/" + session.getFileName());
        if (file.exists()) file.delete();
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
