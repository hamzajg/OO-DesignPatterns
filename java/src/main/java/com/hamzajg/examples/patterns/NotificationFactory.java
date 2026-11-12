package com.hamzajg.examples.patterns;

import java.util.Objects;

/**
 * Creates the right {@link Notification} for a channel, so callers never
 * construct a concrete notification themselves.
 */
public final class NotificationFactory {

    private NotificationFactory() {
    }

    public static Notification create(NotificationChannel channel) {
        Objects.requireNonNull(channel, "channel");
        switch (channel) {
            case EMAIL:
                return new EmailNotification();
            case SMS:
                return new SmsNotification();
            default:
                throw new IllegalArgumentException("unknown channel: " + channel);
        }
    }
}
