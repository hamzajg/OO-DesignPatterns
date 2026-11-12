namespace CSharp.Patterns;

public enum NotificationChannel
{
    Email,
    Sms,
}

/// <summary>Factory: the product the caller works with.</summary>
public interface INotification
{
    string Send(string recipient, string message);
}

public sealed class EmailNotification : INotification
{
    public string Send(string recipient, string message) => $"email to {recipient}: {message}";
}

public sealed class SmsNotification : INotification
{
    public string Send(string recipient, string message) => $"sms to {recipient}: {message}";
}

/// <summary>
/// Creates the right <see cref="INotification"/> for a channel, so callers
/// never construct a concrete notification themselves.
/// </summary>
public static class NotificationFactory
{
    public static INotification Create(NotificationChannel channel) => channel switch
    {
        NotificationChannel.Email => new EmailNotification(),
        NotificationChannel.Sms => new SmsNotification(),
        _ => throw new ArgumentOutOfRangeException(nameof(channel), channel, "unknown channel"),
    };
}
