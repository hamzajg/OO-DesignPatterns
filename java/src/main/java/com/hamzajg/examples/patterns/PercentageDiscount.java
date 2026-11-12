package com.hamzajg.examples.patterns;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Reduces the subtotal by a percentage between 0 and 100. */
public final class PercentageDiscount implements DiscountStrategy {

    private final int percent;

    public PercentageDiscount(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent must be between 0 and 100: " + percent);
        }
        this.percent = percent;
    }

    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        BigDecimal remaining = BigDecimal.valueOf(100 - percent).divide(BigDecimal.valueOf(100));
        return subtotal.multiply(remaining).setScale(2, RoundingMode.HALF_UP);
    }
}
