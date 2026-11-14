# AGENTS.md — OO-DesignPatterns

Guidance for AI agents (and humans) working in this repository.

## Project

Small, tested examples of object-oriented design patterns — the same four
patterns (Strategy, Observer, Decorator, Factory) implemented side by side
in **C# on .NET 10** (`CSharp/`) and **Java on Gradle** (`java/`), so the
two languages can be read against each other. Each pattern is one file,
kept to the minimum the idea needs, pinned by tests.
Stack: C# `net10.0` (xUnit), Java 17 toolchain targeting source 8 (JUnit),
Gradle wrapper in `java/`.

## Commands

Verified 2026-11-13 against CI (`.github/workflows/ci.yml`) and README.

| Task | Command |
|---|---|
| C# tests | `dotnet test CSharp/CSharp.sln --configuration Release` (18 tests) |
| C# demo | `dotnet run --project CSharp/CSharp` |
| Java tests | `cd java && ./gradlew test --console=plain` |
| Java demo | `cd java && ./gradlew run` (check `build.gradle` for the run task) |

Requires: .NET 10 SDK (CI `10.0.x`), JDK 17 for the Java module.

## Architecture

Two parallel modules, one per language, same four patterns:

```
CSharp/
  CSharp/Patterns/    Strategy.cs, Observer.cs, Decorator.cs, Factory.cs
  CSharp/Program.cs   demo printing each pattern's behaviour
  CSharp.Tests/       StrategyTests, ObserverTests, DecoratorTests, FactoryTests
java/
  src/main/java/com/hamzajg/examples/patterns/
    Strategy: DiscountStrategy, NoDiscount, PercentageDiscount, Order
    Observer: Subject<T>, Subscriber<T>
    Decorator: Text, PlainText, TextDecorator, BoldText, UpperCaseText
    Factory: NotificationChannel, Notification, Sms/EmailNotification,
             NotificationFactory
    Demo.java          main() printing the same demo output as Program.cs
  src/test/java/.../patterns/   one JUnit test class per pattern
```

## Conventions

- **Pattern-per-file**, minimal moving parts — an example should be
  readable in one screen. Don't add production concerns (DI, logging,
  persistence) to illustrate a pattern.
- **C# and Java versions mirror each other** — same pattern names, same
  roles. When changing one side, consider whether the other should follow
  to keep them comparable.
- Tests are the spec: xUnit on C#, JUnit on Java; every pattern has a
  dedicated test class.
- KISS: no frameworks beyond the test runners; `java` module targets
  source level 8 deliberately (broad compatibility for the examples).

## Landmines

- **Default branch is `master`, not `main`.**
- README says ".NET 8 SDK" but csproj/CI are `net10.0` — trust the csproj.
- Java module source level is 8 while the toolchain is JDK 17 — don't use
  post-8 language features in `java/`.
- `CSharp/obj` may contain stale net8.0 artifacts — the target is net10.0.
- Two languages in one repo: scope CI/build commands to the right module
  (`CSharp/CSharp.sln` vs `java/`).

## Agent rules

- Branch from `master`; name branches `feat/*`, `fix/*`, `chore/*`, `docs/*`.
- Docs-only changes still go through a PR; never commit to `master` directly.
- Verify before claiming: run the relevant side's test command
  (`dotnet test` for C#, `./gradlew test` for Java) and report the real
  result.
- Keep changes minimal and consistent with surrounding code. No drive-by
  refactors.
- Update AGENTS.md / docs/ARCHITECTURE.md when adding a pattern or changing
  module layout.
