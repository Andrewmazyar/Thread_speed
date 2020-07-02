package thread.racer;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread runnable = new Thread(new RunnableClass(counter));
        Thread thread = new ThreadClass(counter);
        runnable.start();
        thread.start();
    }

}
