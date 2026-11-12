package com.hamzajg.examples.patterns;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FactoryTest {

    @Test
    public void createsAnEmailNotification() {
        Notification notification = NotificationFactory.create(NotificationChannel.EMAIL);

        assertTrue(notification instanceof EmailNotification);
        assertEquals("email to ada: hi", notification.send("ada", "hi"));
    }

    @Test
    public void createsAnSmsNotification() {
        assertTrue(NotificationFactory.create(NotificationChannel.SMS) instanceof SmsNotification);
        assertEquals("sms to ada: hi", NotificationFactory.create(NotificationChannel.SMS).send("ada", "hi"));
    }

    @Test(expected = NullPointerException.class)
    public void rejectsAMissingChannel() {
        NotificationFactory.create(null);
    }
}
