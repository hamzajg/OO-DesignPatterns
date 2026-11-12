package com.hamzajg.examples.patterns;

import java.util.Objects;

/** Base for decorators: it holds the wrapped {@link Text} and adds behaviour. */
public abstract class TextDecorator implements Text {

    protected final Text inner;

    protected TextDecorator(Text inner) {
        this.inner = Objects.requireNonNull(inner, "inner");
    }
}
