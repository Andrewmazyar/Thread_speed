package thread.racer;

import org.apache.log4j.Logger;

public class RunnableClass implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(RunnableClass.class);
    private Counter counter;

    public RunnableClass(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        while (counter.getCount() < counter.getMax()) {
            System.out.println("Runnable class is running: " + counter.getCount());
            LOGGER.info("Runnable class is running: " + counter.getCount());
            counter.incrementNumber();
        }
    }
}
