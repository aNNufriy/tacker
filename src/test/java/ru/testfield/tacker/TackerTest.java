package ru.testfield.tacker;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

class TackerTest {

    @Test
    void addTaskGetPackTest() throws InterruptedException, ExecutionException {
        Tacker<String> tacker = new Tacker<>(new LinkedBlockingDeque<>());
        List<Future<?>> futures = addRandomTasks(tacker, 10000);
        for(Future<?> future: futures){
            future.get();
        }

        Consumer<String> consumer = System.out::println;

        new FixedRatePackProcessor<>(tacker,500, consumer);

        Thread.sleep(10000);
    }

    private List<Future<?>> addRandomTasks(Tacker<String> tacker, int capacity) {
        Runnable callableTask = ()->tacker.addValue(UUID.randomUUID().toString());
        ExecutorService executorService = Executors.newWorkStealingPool();
        var futures = new ArrayList<Future<?>>();
        for(int i=0; i<capacity; i++) {
            futures.add(executorService.submit(callableTask));
        }
        return futures;
    }
}