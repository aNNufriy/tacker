package ru.testfield.tacker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TackerTest {

    @Test
    void addTaskTest() throws InterruptedException, ExecutionException {
        int capacity = 10000;
        Tacker<String> tacker = new Tacker<>(new LinkedBlockingDeque<>(capacity));
        Runnable callableTask = ()->tacker.addValue(UUID.randomUUID().toString());
        ExecutorService executorService = Executors.newWorkStealingPool();
        List<Future<?>> futures = new ArrayList<>();
        for(int i=0; i<capacity; i++) {
            futures.add(executorService.submit(callableTask));
        }
        for(Future<?> future: futures){
            future.get();
        }
        assertEquals(capacity, tacker.getPack().size());
    }
}