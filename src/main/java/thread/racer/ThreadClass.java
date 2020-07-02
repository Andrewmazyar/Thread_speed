package thread.racer;

import org.apache.log4j.Logger;

public class ThreadClass extends Thread {
    private static final Logger LOGGER = Logger.getLogger(ThreadClass.class);
    private Counter counter;

    public ThreadClass(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        while (counter.getCount() < counter.getMax()) {
            System.out.println("Thread class is running: " + counter.getCount());
            LOGGER.info("Thread class is running: " + counter.getCount());
            counter.incrementNumber();
        }
    }
}
