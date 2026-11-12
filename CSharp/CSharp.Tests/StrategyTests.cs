using CSharp.Patterns;
using Xunit;

namespace CSharp.Tests;

public class StrategyTests
{
    [Fact]
    public void NoDiscountReturnsTheSubtotal()
    {
        Assert.Equal(100m, new NoDiscount().Apply(100m));
    }

    [Fact]
    public void PercentageDiscountReducesTheSubtotal()
    {
        Assert.Equal(90m, new PercentageDiscount(10).Apply(100m));
    }

    [Theory]
    [InlineData(-1)]
    [InlineData(101)]
    public void PercentageDiscountRejectsOutOfRangeValues(decimal percent)
    {
        Assert.Throws<ArgumentOutOfRangeException>(() => new PercentageDiscount(percent));
    }

    [Fact]
    public void OrderDelegatesToItsStrategy()
    {
        var order = new Order(new PercentageDiscount(25));

        Assert.Equal(75m, order.Total(100m));
    }

    [Fact]
    public void OrderRejectsANullStrategy()
    {
        Assert.Throws<ArgumentNullException>(() => new Order(null!));
    }
}
