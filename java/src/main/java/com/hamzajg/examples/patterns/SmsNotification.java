package com.hamzajg.examples.patterns;

public final class SmsNotification implements Notification {
    @Override
    public String send(String recipient, String message) {
        return "sms to " + recipient + ": " + message;
    }
}
