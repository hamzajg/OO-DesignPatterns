package com.hamzajg.examples.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The subject keeps a list of observers and notifies each of them when a value
 * is published. Observers can join and leave at any time.
 */
public final class Subject<T> {

    private final List<Subscriber<T>> observers = new ArrayList<>();

    public int observerCount() {
        return observers.size();
    }

    public void subscribe(Subscriber<T> observer) {
        observers.add(Objects.requireNonNull(observer, "observer"));
    }

    public boolean unsubscribe(Subscriber<T> observer) {
        return observers.remove(observer);
    }

    /** Copy first, so an observer may unsubscribe while being notified. */
    public void publish(T value) {
        for (Subscriber<T> observer : new ArrayList<>(observers)) {
            observer.update(value);
        }
    }
}
