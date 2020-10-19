package ru.testfield.tacker;

import java.util.Collection;

public abstract class PackProcessor<T> {

    private final Tacker<T> tacker;

    public PackProcessor(Tacker<T> tacker){
        this.tacker = tacker;
    }

    protected void processPack() {
        Collection<T> pack = this.tacker.getPack();
        System.out.println("Pack size: " + pack.size());
        for(T value: pack) {
            processValue(value);
        }
    }

    protected abstract void processValue(T value);
}