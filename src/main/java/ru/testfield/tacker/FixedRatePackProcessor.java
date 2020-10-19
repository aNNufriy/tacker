package ru.testfield.tacker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class FixedRatePackProcessor<T> extends PackProcessor<T> {

    private final ScheduledExecutorService scheduledExecutorService;
    private final Consumer<T> consumer;

    public FixedRatePackProcessor(Tacker<T> tacker, long rateMillis, Consumer<T> consumer) {
        super(tacker);
        this.scheduledExecutorService = this.createExecutor();
        this.consumer = consumer;
        this.scheduledExecutorService.scheduleAtFixedRate(this::processPack,0, rateMillis, TimeUnit.MILLISECONDS);
    }

    protected ScheduledExecutorService createExecutor() {
        int parallelism = 10 * Runtime.getRuntime().availableProcessors();
        return Executors.newScheduledThreadPool(parallelism);
    }

    @Override
    protected void processValue(T value) {
        consumer.accept(value);
    }
}
