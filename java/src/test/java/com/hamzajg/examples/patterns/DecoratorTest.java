package com.hamzajg.examples.patterns;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecoratorTest {

    @Test
    public void plainTextRendersItself() {
        assertEquals("design patterns", new PlainText("design patterns").render());
    }

    @Test
    public void boldWrapsTheInnerText() {
        assertEquals("<b>design patterns</b>", new BoldText(new PlainText("design patterns")).render());
    }

    @Test
    public void decoratorsCompose() {
        Text text = new BoldText(new UpperCaseText(new PlainText("design patterns")));

        assertEquals("<b>DESIGN PATTERNS</b>", text.render());
    }

    @Test
    public void theOrderOfDecoratorsMatters() {
        Text text = new UpperCaseText(new BoldText(new PlainText("hi")));

        assertEquals("<B>HI</B>", text.render());
    }
}
