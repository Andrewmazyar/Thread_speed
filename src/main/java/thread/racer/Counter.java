package thread.racer;

public class Counter {
    private static final int MAX_BOUND = 100;
    private int count;

    public void incrementNumber() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public int getMax() {
        return MAX_BOUND;
    }
}
