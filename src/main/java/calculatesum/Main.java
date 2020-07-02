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
    private static final int LENGTH_LIST = 1_000_000;
    private static final int ITERATION_STEP = 250_000;
    private static final int START_ITERATION = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Integer> integerList = new ArrayList<>();
        for (int i = START_ITERATION; i < LENGTH_LIST; i++) {
            integerList.add((int) (Math.random() * 10));
        }
        List<ThreadCallable> threadCallables = new ArrayList<>();
        for (int i = START_ITERATION; i < LENGTH_LIST; i = i + ITERATION_STEP) {
            threadCallables.add(new ThreadCallable(integerList.subList(i, i + ITERATION_STEP)));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        int resultExecutor = START_ITERATION;
        for (Future<Integer> integerFuture : executorService.invokeAll(threadCallables)) {
            resultExecutor += integerFuture.get();
        }
        System.out.println("Executor Service sum: " + resultExecutor);
        System.out.println("-----------------------------------------------");
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREADS);
        int resultJoin = START_ITERATION;
        for (Future<Integer> integerFuture : forkJoinPool.invokeAll(threadCallables)) {
            resultJoin += integerFuture.get();
        }
        System.out.println("Fork Join Pull sum: " + resultJoin);
    }
}
