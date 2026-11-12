using CSharp.Patterns;
using Xunit;

namespace CSharp.Tests;

public class FactoryTests
{
    [Fact]
    public void EmailChannelProducesAnEmailNotification()
    {
        var notification = NotificationFactory.Create(NotificationChannel.Email);

        Assert.IsType<EmailNotification>(notification);
        Assert.Equal("email to ada: hi", notification.Send("ada", "hi"));
    }

    [Fact]
    public void SmsChannelProducesAnSmsNotification()
    {
        var notification = NotificationFactory.Create(NotificationChannel.Sms);

        Assert.IsType<SmsNotification>(notification);
        Assert.Equal("sms to ada: hi", notification.Send("ada", "hi"));
    }

    [Fact]
    public void AnUnknownChannelIsRejected()
    {
        Assert.Throws<ArgumentOutOfRangeException>(
            () => NotificationFactory.Create((NotificationChannel)99));
    }
}
