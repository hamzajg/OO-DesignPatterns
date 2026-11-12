package com.hamzajg.examples.patterns;

public final class BoldText extends TextDecorator {

    public BoldText(Text inner) {
        super(inner);
    }

    @Override
    public String render() {
        return "<b>" + inner.render() + "</b>";
    }
}
