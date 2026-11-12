# OO-DesignPatterns

Small, tested examples of object-oriented design patterns, in C# on .NET 8 and
in Java on Gradle. Each pattern lives in its own file, keeps the moving parts to
the minimum the idea needs, and is pinned by tests — xunit on the C# side, JUnit
on the Java side.

## Patterns

### C#

| Pattern | Code | What the example shows |
|---|---|---|
| **Strategy** | [`Patterns/Strategy.cs`](CSharp/CSharp/Patterns/Strategy.cs) | `Order` delegates the total to an `IDiscountStrategy`, so a discount rule can be swapped (or added) without touching `Order`. |
| **Observer** | [`Patterns/Observer.cs`](CSharp/CSharp/Patterns/Observer.cs) | `Subject<T>` keeps a list of `ISubscriber<T>`s and notifies each on `Publish`; subscribers can join and leave at any time, including while being notified. |
| **Decorator** | [`Patterns/Decorator.cs`](CSharp/CSharp/Patterns/Decorator.cs) | `BoldText` and `UpperCaseText` wrap an `IText` and add behaviour without subclassing every combination. |
| **Factory** | [`Patterns/Factory.cs`](CSharp/CSharp/Patterns/Factory.cs) | `NotificationFactory.Create` returns the right `INotification` for a channel, so callers never construct a concrete notification. |

### Java

The Java module is the same four patterns, ported to the JDK so the two
languages can be read side by side.

| Pattern | Code | What the example shows |
|---|---|---|
| **Strategy** | [`DiscountStrategy.java`](java/src/main/java/com/hamzajg/examples/patterns/DiscountStrategy.java) | `Order` delegates the total to a `DiscountStrategy`, so a discount rule can be swapped without touching `Order`. |
| **Observer** | [`Subject.java`](java/src/main/java/com/hamzajg/examples/patterns/Subject.java) | `Subject<T>` notifies every `Subscriber<T>` on `publish`; subscribers join and leave at any time, including while being notified. |
| **Decorator** | [`TextDecorator.java`](java/src/main/java/com/hamzajg/examples/patterns/TextDecorator.java) | `BoldText` and `UpperCaseText` wrap a `Text` and add behaviour without subclassing every combination. |
| **Factory** | [`NotificationFactory.java`](java/src/main/java/com/hamzajg/examples/patterns/NotificationFactory.java) | `NotificationFactory.create` returns the right `Notification` for a channel. |

## Requirements

- [.NET 8 SDK](https://dotnet.microsoft.com/download/dotnet/8.0)
- JDK 17 (the Java module targets source level 8)

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

```bash
cd java && ./gradlew test
```

The Java suite has 18 tests (7 strategy, 4 observer, 4 decorator, 3 factory).
Run its demo with:

```bash
cd java && ./gradlew classes
java -cp build/classes/java/main com.hamzajg.examples.patterns.Demo
```

## Layout

```
CSharp/
  CSharp/            console demo
    Patterns/        one file per pattern
    Program.cs       runs every example
  CSharp.Tests/      xunit tests, one file per pattern
  CSharp.sln
java/
  src/main/java/com/hamzajg/examples/patterns/   the four patterns, one file per type
    Demo.java                                    runs every example
  src/test/java/com/hamzajg/examples/patterns/   JUnit tests, one file per pattern
  build.gradle
  gradlew, gradle/wrapper/
```

## Adding a pattern

1. Add a file under `CSharp/CSharp/Patterns/` and/or
   `java/src/main/java/com/hamzajg/examples/patterns/`.
2. Add a matching test file under `CSharp/CSharp.Tests/` and/or
   `java/src/test/java/com/hamzajg/examples/patterns/`.
3. Call it from `Program.cs` / `Demo.java` if it is worth showing in the demo.
4. Run `dotnet test CSharp/CSharp.sln` and `cd java && ./gradlew test`.

## Continuous integration

[`.github/workflows/ci.yml`](.github/workflows/ci.yml) runs two jobs for every
push and pull request to `master`: `dotnet test CSharp/CSharp.sln
--configuration Release` on .NET 8, and `./gradlew test` in `java/` on JDK 17.

## License

See [LICENSE](LICENSE).
