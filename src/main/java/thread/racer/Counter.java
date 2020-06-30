package thread.racer;

public class Counter {
    private int count;
    private int maxNumber = 100;

    public void incrementNumber() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public int getMax() {
        return maxNumber;
    }
}
