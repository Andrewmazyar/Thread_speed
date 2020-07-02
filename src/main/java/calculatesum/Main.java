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
        ThreadCallable threadCallable = new ThreadCallable(integerList);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futuresExecutor = executorService.invokeAll(List.of(threadCallable));
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        List<Future<Integer>> futuresJoin = forkJoinPool.invokeAll(List.of(threadCallable));
    }
}
