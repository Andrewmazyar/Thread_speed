package calculatesum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            integerList.add((int) (Math.random() * 10));
        }
        List<ThreadCallable> threadCallables = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i = i + 250_000) {
            threadCallables.add( new ThreadCallable(integerList.subList(i, i + 250_000)));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futuresExecutor = executorService
                .invokeAll(List.of(threadCallables.get(0),
                        threadCallables.get(1),
                        threadCallables.get(2),
                        threadCallables.get(3)));
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        List<Future<Integer>> futuresJoin = forkJoinPool
                .invokeAll(List.of(threadCallables.get(0),
                        threadCallables.get(1),
                        threadCallables.get(2),
                        threadCallables.get(3)));
    }
}
