package thread.racer;

public class ThreadClass extends Thread {
    private Counter counter;

    public ThreadClass(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        while (counter.getCount() < counter.getMax()) {
            System.out.println("Thread class is running: " + counter.getCount());
            counter.incrementNumber();
        }
    }
}
