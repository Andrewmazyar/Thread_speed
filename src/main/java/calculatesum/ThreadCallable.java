package calculatesum;

import java.util.List;
import java.util.concurrent.Callable;

public class ThreadCallable implements Callable<Integer> {
    private List<Integer> listInt;

    public ThreadCallable(List<Integer> listInt) {
        this.listInt = listInt;
    }

    @Override
    public Integer call() throws Exception {
        Integer sum = listInt
                .stream()
                .reduce(0, Integer::sum);
        System.out.println("number is -> " + sum);
        return sum;
    }
}
