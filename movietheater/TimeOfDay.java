import java.time.LocalDateTime;

public enum TimeOfDay {
    MORNING(9, 12), AFTERNOON(13, 16), EVENING(17, 22), NIGHT(22, 5);

    private int start;
    private int end;

    TimeOfDay(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
