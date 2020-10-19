package ru.testfield.tacker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tacker<T> {

    private final Lock getPackLock = new ReentrantLock();

    private final BlockingQueue<T> blockingQueue;

    public Tacker(BlockingQueue<T> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void addValue(T value) {
        try {
            blockingQueue.put(value);
        } catch (InterruptedException e) {
            System.out.println("Unable to add value to blocking queue: interrupted");
        }
    }

    public Collection<T> getPack() {
        getPackLock.lock();
        int packSize = blockingQueue.size();
        Collection<T> pack = new ArrayList<>(packSize);
        try {
            for (int i=0; i<packSize; i++) {
                try {
                    pack.add(blockingQueue.take());
                } catch (InterruptedException e) {
                    System.out.println("Unable to retrieve value from blocking queue: interrupted");
                    break;
                }
            }
        }finally {
            getPackLock.unlock();
        }
        return pack;
    }
}