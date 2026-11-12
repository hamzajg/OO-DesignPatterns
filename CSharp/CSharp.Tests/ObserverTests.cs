using CSharp.Patterns;
using Xunit;

namespace CSharp.Tests;

public class ObserverTests
{
    private sealed class RecordingObserver : ISubscriber<string>
    {
        public List<string> Values { get; } = new();

        public void Update(string value) => Values.Add(value);
    }

    [Fact]
    public void EverySubscriberReceivesTheValue()
    {
        var subject = new Subject<string>();
        var first = new RecordingObserver();
        var second = new RecordingObserver();
        subject.Subscribe(first);
        subject.Subscribe(second);

        subject.Publish("one");

        Assert.Equal(new[] { "one" }, first.Values);
        Assert.Equal(new[] { "one" }, second.Values);
    }

    [Fact]
    public void AnUnsubscribedObserverStopsReceivingValues()
    {
        var subject = new Subject<string>();
        var observer = new RecordingObserver();
        subject.Subscribe(observer);
        subject.Publish("one");

        var removed = subject.Unsubscribe(observer);
        subject.Publish("two");

        Assert.True(removed);
        Assert.Equal(new[] { "one" }, observer.Values);
        Assert.Equal(0, subject.ObserverCount);
    }

    [Fact]
    public void UnsubscribingAnUnknownObserverReturnsFalse()
    {
        var subject = new Subject<string>();

        Assert.False(subject.Unsubscribe(new RecordingObserver()));
    }

    [Fact]
    public void AnObserverMayUnsubscribeWhileBeingNotified()
    {
        var subject = new Subject<string>();
        var observer = new UnsubscribingObserver(subject);
        subject.Subscribe(observer);

        subject.Publish("one");
        subject.Publish("two");

        Assert.Equal(new[] { "one" }, observer.Values);
    }

    private sealed class UnsubscribingObserver : ISubscriber<string>
    {
        private readonly Subject<string> _subject;

        public UnsubscribingObserver(Subject<string> subject) => _subject = subject;

        public List<string> Values { get; } = new();

        public void Update(string value)
        {
            Values.Add(value);
            _subject.Unsubscribe(this);
        }
    }
}
