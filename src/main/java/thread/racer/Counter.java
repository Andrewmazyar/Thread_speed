package thread.racer;

public class Counter {
    private int count;
    private final int MAX_NUMBER = 100;

    public void incrementNumber() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public int getMax() {
        return MAX_NUMBER;
    }
}
