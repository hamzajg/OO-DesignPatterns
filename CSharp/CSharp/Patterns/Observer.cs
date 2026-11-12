namespace CSharp.Patterns;

/// <summary>Observer: something that wants to hear about new values.</summary>
public interface ISubscriber<in T>
{
    void Update(T value);
}

/// <summary>
/// The subject keeps a list of observers and notifies each of them when a
/// value is published. Observers can join and leave at any time.
/// </summary>
public sealed class Subject<T>
{
    private readonly List<ISubscriber<T>> _observers = new();

    public int ObserverCount => _observers.Count;

    public void Subscribe(ISubscriber<T> observer)
    {
        if (observer is null)
        {
            throw new ArgumentNullException(nameof(observer));
        }

        _observers.Add(observer);
    }

    public bool Unsubscribe(ISubscriber<T> observer) => _observers.Remove(observer);

    public void Publish(T value)
    {
        // Copy so an observer may unsubscribe while being notified.
        foreach (var observer in _observers.ToArray())
        {
            observer.Update(value);
        }
    }
}
