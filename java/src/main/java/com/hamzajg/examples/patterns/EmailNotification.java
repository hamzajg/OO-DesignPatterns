package com.hamzajg.examples.patterns;

public final class EmailNotification implements Notification {
    @Override
    public String send(String recipient, String message) {
        return "email to " + recipient + ": " + message;
    }
}
