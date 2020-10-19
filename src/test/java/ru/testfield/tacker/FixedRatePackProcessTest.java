package ru.testfield.tacker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixedRatePackProcessTest extends AbstractTackerTest {

    @Test
    public void processPackTest() throws InterruptedException {
        final int capacity = 10000;
        Tacker<String> tacker = new Tacker<>();
        addRandomTasks(tacker, capacity);
        List<String> result = new ArrayList<>(capacity);
        Consumer<String> consumer = result::add;
        new FixedRatePackProcessor<>(tacker,100, consumer);
        Thread.sleep(1000);
        assertEquals(capacity,result.size());
    }
}
