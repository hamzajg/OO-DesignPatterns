package com.hamzajg.examples.patterns;

/** Factory: the product the caller works with. */
public interface Notification {
    String send(String recipient, String message);
}
