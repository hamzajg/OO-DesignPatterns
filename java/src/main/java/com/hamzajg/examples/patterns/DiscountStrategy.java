package com.hamzajg.examples.patterns;

import java.math.BigDecimal;

/** Strategy: one discount rule, behind an interface the order depends on. */
public interface DiscountStrategy {
    BigDecimal apply(BigDecimal subtotal);
}
