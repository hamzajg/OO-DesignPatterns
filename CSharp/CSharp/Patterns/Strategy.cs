namespace CSharp.Patterns;

/// <summary>
/// Strategy: put each discount rule behind the same interface so an
/// <see cref="Order"/> can use any of them without knowing which one it has.
/// </summary>
public interface IDiscountStrategy
{
    decimal Apply(decimal subtotal);
}

/// <summary>No reduction at all.</summary>
public sealed class NoDiscount : IDiscountStrategy
{
    public decimal Apply(decimal subtotal) => subtotal;
}

/// <summary>Reduces the subtotal by a percentage between 0 and 100.</summary>
public sealed class PercentageDiscount : IDiscountStrategy
{
    private readonly decimal _percent;

    public PercentageDiscount(decimal percent)
    {
        if (percent is < 0 or > 100)
        {
            throw new ArgumentOutOfRangeException(nameof(percent), percent, "must be between 0 and 100");
        }

        _percent = percent;
    }

    public decimal Apply(decimal subtotal) => subtotal * (1 - _percent / 100);
}

/// <summary>The context: it delegates the calculation to its strategy.</summary>
public sealed class Order
{
    private readonly IDiscountStrategy _discount;

    public Order(IDiscountStrategy discount)
    {
        _discount = discount ?? throw new ArgumentNullException(nameof(discount));
    }

    public decimal Total(decimal subtotal) => _discount.Apply(subtotal);
}
