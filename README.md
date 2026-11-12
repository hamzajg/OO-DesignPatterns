# OO-DesignPatterns

Small, tested examples of object-oriented design patterns in C# on .NET 8.
Each pattern lives in its own file, keeps the moving parts to the minimum the
idea needs, and is pinned by xunit tests.

## Patterns

| Pattern | Code | What the example shows |
|---|---|---|
| **Strategy** | [`Patterns/Strategy.cs`](CSharp/CSharp/Patterns/Strategy.cs) | `Order` delegates the total to an `IDiscountStrategy`, so a discount rule can be swapped (or added) without touching `Order`. |
| **Observer** | [`Patterns/Observer.cs`](CSharp/CSharp/Patterns/Observer.cs) | `Subject<T>` keeps a list of `ISubscriber<T>`s and notifies each on `Publish`; subscribers can join and leave at any time, including while being notified. |
| **Decorator** | [`Patterns/Decorator.cs`](CSharp/CSharp/Patterns/Decorator.cs) | `BoldText` and `UpperCaseText` wrap an `IText` and add behaviour without subclassing every combination. |
| **Factory** | [`Patterns/Factory.cs`](CSharp/CSharp/Patterns/Factory.cs) | `NotificationFactory.Create` returns the right `INotification` for a channel, so callers never construct a concrete notification. |

## Requirements

- [.NET 8 SDK](https://dotnet.microsoft.com/download/dotnet/8.0)

## Run the demo

```bash
dotnet run --project CSharp/CSharp
```

```
Strategy
  100.00 with 10% off = 90.00
Observer
  A received hello
  B received hello
Decorator
  <b>DESIGN PATTERNS</b>
Factory
  email to ada: hi
  sms to ada: hi
```

## Run the tests

```bash
dotnet test CSharp/CSharp.sln
```

The suite has 18 tests (5 strategy, 4 observer, 5 decorator, 4 factory). The
test project is [`CSharp/CSharp.Tests`](CSharp/CSharp.Tests), which references
the demo project and therefore tests the same code the demo runs.

## Layout

```
CSharp/
  CSharp/            console demo
    Patterns/        one file per pattern
    Program.cs       runs every example
  CSharp.Tests/      xunit tests, one file per pattern
  CSharp.sln
java/                leftover Gradle wrapper from the original layout (no sources)
```

## Adding a pattern

1. Add a file under `CSharp/CSharp/Patterns/`.
2. Add a matching test file under `CSharp/CSharp.Tests/`.
3. Call it from `Program.cs` if it is worth showing in the demo.
4. Run `dotnet test CSharp/CSharp.sln`.

## Continuous integration

[`.github/workflows/ci.yml`](.github/workflows/ci.yml) runs
`dotnet test CSharp/CSharp.sln --configuration Release` on .NET 8 for every push
and pull request to `master`.

## License

See [LICENSE](LICENSE).
