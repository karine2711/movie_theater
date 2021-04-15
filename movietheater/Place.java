public class Place {
    private final int number;
    private boolean isReserved;


    public Place(int number) {
        this.number = number;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "[ " + number +
                "," + (isReserved ? "reserved" : "free") +
                " ]";
    }
}
