package com.hamzajg.examples.patterns;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class StrategyTest {

    @Test
    public void noDiscountLeavesTheSubtotalAlone() {
        assertEquals(new BigDecimal("100.00"), new NoDiscount().apply(new BigDecimal("100.00")));
    }

    @Test
    public void percentageDiscountTakesThePercentageOff() {
        assertEquals(new BigDecimal("90.00"), new PercentageDiscount(10).apply(new BigDecimal("100.00")));
    }

    @Test
    public void percentageDiscountRoundsToTheNearestCent() {
        assertEquals(new BigDecimal("17.99"), new PercentageDiscount(10).apply(new BigDecimal("19.99")));
    }

    @Test
    public void orderDelegatesToItsStrategy() {
        Order order = new Order(new PercentageDiscount(25));

        assertEquals(new BigDecimal("75.00"), order.total(new BigDecimal("100.00")));
    }

    @Test
    public void strategyCanBeSwappedWithoutTouchingTheOrder() {
        assertEquals(new BigDecimal("100.00"),
                new Order(new NoDiscount()).total(new BigDecimal("100.00")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsAPercentageAboveOneHundred() {
        new PercentageDiscount(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsANegativePercentage() {
        new PercentageDiscount(-1);
    }
}
