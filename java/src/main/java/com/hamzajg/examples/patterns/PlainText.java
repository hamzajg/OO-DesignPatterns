package com.hamzajg.examples.patterns;

import java.util.Objects;

/** The component being decorated. */
public final class PlainText implements Text {

    private final String text;

    public PlainText(String text) {
        this.text = Objects.requireNonNull(text, "text");
    }

    @Override
    public String render() {
        return text;
    }
}
