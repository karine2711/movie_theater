import java.time.LocalDateTime;

public enum TimeOfDay {
    MORNING, AFTERNOON, EVENING, NIGHT;

    //TODO: Add a constructor which will receive start and end hours for each time of day and assign to corresponding instance variables

    private String[] arr;
    private int inter;
    private int intra;

    TimeOfDay(LocalDateTime start, LocalDateTime end) {
        if (start.isBefore(12:00)){

        }

    }
}
