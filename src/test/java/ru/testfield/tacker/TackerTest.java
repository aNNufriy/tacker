package ru.testfield.tacker;

import java.util.*;
import java.util.concurrent.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TackerTest extends AbstractTackerTest {

    @Test
    void addTaskGetPackTest() throws InterruptedException, ExecutionException {
        int capacity = 10000;
        Tacker<String> tacker = new Tacker<>(new LinkedBlockingDeque<>(capacity));
        List<Future<?>> futures = addRandomTasks(tacker, capacity);
        int counter = 0;
        for(Future<?> future: futures){
            counter++;
            future.get();
        }
        assertEquals(capacity,counter);
    }
}