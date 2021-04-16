import java.time.LocalDateTime;

public enum TimeOfDay {
    MORNING, AFTERNOON, EVENING, NIGHT;

    private int start;
    private int end;

    TimeOfDay(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
