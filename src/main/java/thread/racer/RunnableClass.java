package thread.racer;

public class RunnableClass implements Runnable {
    private Counter counter;

    public RunnableClass(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        while (counter.getCount() < counter.getMax()) {
            System.out.println("Runnable class is running: " + counter.getCount());
            counter.incrementNumber();
        }
    }
}
