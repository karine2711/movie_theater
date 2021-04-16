import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class MovieFilterer {
    //Todo: Please take a look at builder pattern. I suggest using it for this filterer
    //class to add any kinds of filter combinations and return fully filtered list later
    //I also suggest taking a look at Stream interface as it is quite useful for filtering

    //Todo: Asya
    //Provided a movie get all available sessions
    public List<MovieSession> filterByMovie(Movie movie, List<MovieSession> movieSessions){
        return null;
    }

    //Todo: Asya
    //Provided a movie get all available sessions
    public List<MovieSession> filterByDate(Date date, List<MovieSession> movieSessions){
        return null;
    }

    //Todo: Asya
    //Find All Movie Sessions on Time of Day
    public List<MovieSession> filterByTimeOfDat(TimeOfDay timeOfDay, List<MovieSession> movieSessions){
        movieSessions.stream().filter(movieSession -> {isBetween(movieSession.getLocalDateTime())})
    }


    private boolean isBetween(LocalDateTime time, LocalDateTime start, LocalDateTime end) {
        return time.isAfter(start) && time.isBefore(end);
    }
}
