import java.util.*;
import java.util.stream.Collectors;

public class SessionFilterer {
    private List<MovieSession> movieSessions = new ArrayList<>();

    public List<MovieSession> filterByMovie (Movie movie, List<MovieSession> movieSessions){
        List<MovieSession> filteredList = movieSessions.stream().filter(s -> s.getMovie().equals(movie)).collect(Collectors.toList());
        return filteredList;
    }




}
