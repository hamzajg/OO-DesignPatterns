using CSharp.Patterns;
using Xunit;

namespace CSharp.Tests;

public class DecoratorTests
{
    [Fact]
    public void PlainTextRendersItself()
    {
        Assert.Equal("hello", new PlainText("hello").Render());
    }

    [Fact]
    public void BoldWrapsTheInnerText()
    {
        Assert.Equal("<b>hello</b>", new BoldText(new PlainText("hello")).Render());
    }

    [Fact]
    public void UpperCaseTransformsTheInnerText()
    {
        Assert.Equal("HELLO", new UpperCaseText(new PlainText("hello")).Render());
    }

    [Fact]
    public void DecoratorsComposeInOrder()
    {
        IText boldOutside = new BoldText(new UpperCaseText(new PlainText("hello")));
        IText upperOutside = new UpperCaseText(new BoldText(new PlainText("hello")));

        Assert.Equal("<b>HELLO</b>", boldOutside.Render());
        Assert.Equal("<B>HELLO</B>", upperOutside.Render());
    }

    [Fact]
    public void ADecoratorNeedsAnInnerText()
    {
        Assert.Throws<ArgumentNullException>(() => new BoldText(null!));
    }
}
