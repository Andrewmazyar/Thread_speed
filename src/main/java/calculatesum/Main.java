package calculatesum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            integerList.add((int) (Math.random() * 10));
        }
        List<ThreadCallable> threadCallables = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i = i + 250_000) {
            threadCallables.add(new ThreadCallable(integerList.subList(i, i + 250_000)));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futuresExecutor = executorService.invokeAll(threadCallables);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        List<Future<Integer>> futuresJoin = forkJoinPool.invokeAll(threadCallables);
        int resultExecutor = futuresExecutor.get(0).get()
                + futuresExecutor.get(1).get()
                + futuresExecutor.get(2).get()
                + futuresExecutor.get(3).get();
        System.out.println("Executor sum: " + resultExecutor);
        System.out.println("-----------------------------------------------");
        int resultJoin = futuresJoin.get(0).get()
                + futuresJoin.get(1).get()
                + futuresJoin.get(2).get()
                + futuresJoin.get(3).get();
        System.out.println("Join sum: " + resultJoin);
    }
}
