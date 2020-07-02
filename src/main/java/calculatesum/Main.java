package calculatesum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {
    private static final int THREADS = 4;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            integerList.add((int) (Math.random() * 10));
        }
        List<ThreadCallable> threadCallables = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i = i + 250_000) {
            threadCallables.add(new ThreadCallable(integerList.subList(i, i + 250_000)));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        int resultExecutor = 0;
        for (Future<Integer> integerFuture : executorService.invokeAll(threadCallables)) {
            resultExecutor += integerFuture.get();
        }
        System.out.println("Executor Service sum: " + resultExecutor);
        System.out.println("-----------------------------------------------");
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREADS);
        int resultJoin = 0;
        for (Future<Integer> integerFuture : forkJoinPool.invokeAll(threadCallables)) {
            resultJoin += integerFuture.get();
        }
        System.out.println("Fork Join Pull sum: " + resultJoin);
    }
}
