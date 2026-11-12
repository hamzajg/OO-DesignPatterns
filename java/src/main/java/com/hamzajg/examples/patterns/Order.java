package com.hamzajg.examples.patterns;

import java.math.BigDecimal;
import java.util.Objects;

/** The context: it delegates the calculation to its strategy. */
public final class Order {

    private final DiscountStrategy discount;

    public Order(DiscountStrategy discount) {
        this.discount = Objects.requireNonNull(discount, "discount");
    }

    public BigDecimal total(BigDecimal subtotal) {
        return discount.apply(subtotal);
    }
}
