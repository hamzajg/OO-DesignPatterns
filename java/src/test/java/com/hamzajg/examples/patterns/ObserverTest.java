package com.hamzajg.examples.patterns;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObserverTest {

    @Test
    public void notifiesEverySubscriber() {
        Subject<String> subject = new Subject<>();
        List<String> received = new ArrayList<>();
        subject.subscribe(value -> received.add("A:" + value));
        subject.subscribe(value -> received.add("B:" + value));

        subject.publish("hello");

        assertEquals(Arrays.asList("A:hello", "B:hello"), received);
    }

    @Test
    public void unsubscribeStopsNotifications() {
        Subject<String> subject = new Subject<>();
        List<String> received = new ArrayList<>();
        Subscriber<String> first = received::add;
        subject.subscribe(first);
        subject.subscribe(value -> received.add("second:" + value));

        assertTrue(subject.unsubscribe(first));
        subject.publish("hello");

        assertEquals(Arrays.asList("second:hello"), received);
        assertEquals(1, subject.observerCount());
    }

    @Test
    public void subscriberMayLeaveWhileBeingNotified() {
        Subject<String> subject = new Subject<>();
        List<String> received = new ArrayList<>();
        LeavingSubscriber leaving = new LeavingSubscriber(subject, received);
        subject.subscribe(leaving);
        subject.subscribe(value -> received.add("staying:" + value));

        // The copy the subject publishes over is what makes leaving here safe.
        subject.publish("one");
        subject.publish("two");

        assertEquals(Arrays.asList("leaving:one", "staying:one", "staying:two"), received);
        assertFalse(subject.unsubscribe(leaving));
    }

    @Test(expected = NullPointerException.class)
    public void rejectsANullSubscriber() {
        new Subject<String>().subscribe(null);
    }

    /** Leaves after the first notification, while the subject is still publishing. */
    private static final class LeavingSubscriber implements Subscriber<String> {

        private final Subject<String> subject;
        private final List<String> received;

        private LeavingSubscriber(Subject<String> subject, List<String> received) {
            this.subject = subject;
            this.received = received;
        }

        @Override
        public void update(String value) {
            received.add("leaving:" + value);
            subject.unsubscribe(this);
        }
    }
}
