package com.hamzajg.examples.patterns;

import java.util.Locale;

public final class UpperCaseText extends TextDecorator {

    public UpperCaseText(Text inner) {
        super(inner);
    }

    @Override
    public String render() {
        return inner.render().toUpperCase(Locale.ROOT);
    }
}
