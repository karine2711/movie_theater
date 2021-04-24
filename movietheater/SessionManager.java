import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SessionManager {
    private List<MovieSession> movieSessions = new ArrayList<>();

    public void addSession(MovieSession session) {
        LocalDateTime start = session.getLocalDateTime();
        LocalDateTime end = session.getEndTime();
        List<MovieSession> overlaps = movieSessions.stream().filter(s -> overlaps(s, session)).collect(Collectors.toList());
        if (overlaps.isEmpty()) {
            movieSessions.add(session);
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

    //Todo: Asya: Implement this
    private MovieSession filter(MovieFilterer filterer){
        return filterer.filter(movieSessions);
    }

}
