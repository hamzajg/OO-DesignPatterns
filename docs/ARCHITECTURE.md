# Architecture — OO-DesignPatterns

Current-state map. Descriptive, not aspirational; see `docs/adr/` for why.

## Shape

```
OO-DesignPatterns/
├── CSharp/                    .NET 10 module (xUnit)
│   ├── CSharp/Patterns/       one file per pattern
│   ├── CSharp/Program.cs      console demo
│   └── CSharp.Tests/          one test class per pattern
└── java/                      Gradle module (JUnit), source level 8
    └── src/main|test/java/com/hamzajg/examples/patterns/
```

## The four patterns (identical in both languages)

| Pattern | Roles | Idea |
|---|---|---|
| Strategy | `Order` + `IDiscountStrategy`/`DiscountStrategy` (`NoDiscount`, `PercentageDiscount`) | Swap discount rule without touching `Order` |
| Observer | `Subject<T>` + `ISubscriber<T>`/`Subscriber<T>` | Publish/subscribe; join/leave even during notify |
| Decorator | `IText`/`Text` + `TextDecorator` (`BoldText`, `UpperCaseText`) | Wrap behaviour without subclass explosion |
| Factory | `NotificationFactory`/`NotificationChannel` → `Sms`/`EmailNotification` | Caller asks for a channel, never constructs concretes |

## Design rules

- Each pattern file contains only the types the idea needs — no base
  "framework" folder, no shared utils.
- `Program.cs` (C#) and `Demo.java` (Java) print identical demo output so
  the two implementations can be diffed by behaviour.
- Tests pin behaviour per pattern; they are the contract.

## Why two languages

The repo's purpose is comparison: same pattern, same roles, two ecosystems.
Changes that break the parallel (different pattern sets, divergent role
names) undermine the repo's reason to exist.
