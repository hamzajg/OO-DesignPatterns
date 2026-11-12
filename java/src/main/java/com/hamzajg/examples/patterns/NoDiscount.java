package com.hamzajg.examples.patterns;

import java.math.BigDecimal;

/** No reduction at all. */
public final class NoDiscount implements DiscountStrategy {
    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        return subtotal;
    }
}
