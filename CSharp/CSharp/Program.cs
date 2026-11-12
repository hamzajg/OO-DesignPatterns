using CSharp.Patterns;

Console.WriteLine("Strategy");
var order = new Order(new PercentageDiscount(10));
Console.WriteLine($"  100.00 with 10% off = {order.Total(100m):0.00}");

Console.WriteLine("Observer");
var subject = new Subject<string>();
subject.Subscribe(new PrintObserver("A"));
subject.Subscribe(new PrintObserver("B"));
subject.Publish("hello");

Console.WriteLine("Decorator");
IText text = new BoldText(new UpperCaseText(new PlainText("design patterns")));
Console.WriteLine($"  {text.Render()}");

Console.WriteLine("Factory");
foreach (var channel in Enum.GetValues<NotificationChannel>())
{
    Console.WriteLine($"  {NotificationFactory.Create(channel).Send("ada", "hi")}");
}

internal sealed class PrintObserver : ISubscriber<string>
{
    private readonly string _name;

    public PrintObserver(string name) => _name = name;

    public void Update(string value) => Console.WriteLine($"  {_name} received {value}");
}
