namespace CSharp.Patterns;

/// <summary>Decorator: a text that can be rendered.</summary>
public interface IText
{
    string Render();
}

/// <summary>The component being decorated.</summary>
public sealed class PlainText : IText
{
    private readonly string _text;

    public PlainText(string text) => _text = text;

    public string Render() => _text;
}

/// <summary>
/// Base for decorators: it holds the wrapped <see cref="IText"/> and leaves
/// <see cref="Render"/> to the concrete decorator.
/// </summary>
public abstract class TextDecorator : IText
{
    protected TextDecorator(IText inner)
    {
        Inner = inner ?? throw new ArgumentNullException(nameof(inner));
    }

    protected IText Inner { get; }

    public abstract string Render();
}

public sealed class BoldText : TextDecorator
{
    public BoldText(IText inner) : base(inner)
    {
    }

    public override string Render() => $"<b>{Inner.Render()}</b>";
}

public sealed class UpperCaseText : TextDecorator
{
    public UpperCaseText(IText inner) : base(inner)
    {
    }

    public override string Render() => Inner.Render().ToUpperInvariant();
}
