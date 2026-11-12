package com.hamzajg.examples.patterns;

/** Observer: something that wants to hear about new values. */
public interface Subscriber<T> {
    void update(T value);
}
