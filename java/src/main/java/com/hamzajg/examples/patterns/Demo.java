package com.hamzajg.examples.patterns;

import java.math.BigDecimal;

/** Runs every Java example, mirroring the C# demo's output. */
public final class Demo {

    private Demo() {
    }

    public static void main(String[] args) {
        System.out.println("Strategy");
        Order order = new Order(new PercentageDiscount(10));
        System.out.println("  100.00 with 10% off = " + order.total(new BigDecimal("100.00")));

        System.out.println("Observer");
        Subject<String> subject = new Subject<>();
        subject.subscribe(value -> System.out.println("  A received " + value));
        subject.subscribe(value -> System.out.println("  B received " + value));
        subject.publish("hello");

        System.out.println("Decorator");
        Text text = new BoldText(new UpperCaseText(new PlainText("design patterns")));
        System.out.println("  " + text.render());

        System.out.println("Factory");
        for (NotificationChannel channel : NotificationChannel.values()) {
            System.out.println("  " + NotificationFactory.create(channel).send("ada", "hi"));
        }
    }
}
