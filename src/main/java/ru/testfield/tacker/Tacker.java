package ru.testfield.tacker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tacker<T> {

    private final Lock retrieveLock = new ReentrantLock();

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

    public List<T> getPack() {
        List<T> pack = new ArrayList<>(blockingQueue.size());
        retrieveLock.lock();
        try {
            while(blockingQueue.size()>0){
                try {
                    pack.add(blockingQueue.take());
                } catch (InterruptedException e) {
                    break;
                }
            }
        }finally {
            retrieveLock.unlock();
        }
        return pack;
    }
}