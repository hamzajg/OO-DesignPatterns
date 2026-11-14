# ADR 0001 — Initial architecture

Date: 2026-11-13 (baseline recorded retroactively)
Status: accepted (describes what is built)

## Context

A teaching repo demonstrating four GoF patterns. The distinctive choice is
dual-language: C# and Java implementations of the same patterns for
side-by-side reading.

## Decisions

1. **Same four patterns in two languages.** Strategy, Observer, Decorator,
   Factory — implemented in `CSharp/` (net10.0, xUnit) and `java/` (Gradle,
   JUnit). Rationale: comparison is the pedagogy; divergence between the
   two is a defect, not a feature.
2. **Pattern-per-file, minimal surface.** Each pattern is one file (plus
   its small role types) with no shared infrastructure. Rationale: the
   example must be readable in one screen — KISS as a teaching constraint.
3. **Tests as the spec.** One test class per pattern per language. The
   suite pins behaviour so refactors can't silently change semantics.
4. **Java targets source level 8 on JDK 17.** Broad language compatibility
   for the examples while running on a modern toolchain. Rationale: the
   examples should look like textbook Java, not showcase new features.
5. **net10.0 for C# (README still says 8).** Retargeted during the fleet
   SDK sweep; csproj + CI (`10.0.x`) are the truth, README is stale.
6. **No frameworks.** No DI, no logging, no web — plain console demo +
   tests. Rationale: pattern clarity beats realism.

## Consequences

- Adding a pattern means adding it to BOTH languages to preserve parity.
- "Minimal" is enforced socially — reviewers should reject production-grade
  additions to example files.
- The two modules have separate build systems; CI runs them as parallel
  jobs.
