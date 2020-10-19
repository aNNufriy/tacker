package ru.testfield.tacker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AbstractTackerTest {
    protected List<Future<?>> addRandomTasks(Tacker<String> tacker, int capacity) {
        Runnable callableTask = ()->tacker.addValue(UUID.randomUUID().toString());
        ExecutorService executorService = Executors.newWorkStealingPool();
        var futures = new ArrayList<Future<?>>();
        for(int i=0; i<capacity; i++) {
            futures.add(executorService.submit(callableTask));
        }
        return futures;
    }
}
