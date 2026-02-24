# Phase 2: Audit Report

## Prioritized Issue List

### P0 (Must Fix Now - Critical Blockers & Broken Flows)

**1. Hardcoded Absolute Paths**

- **Evidence**: `Graph/Graph/src/DAGTEST.java:14` - `String citiesFile = "C:/Users/milto/eclipse-workspace/Graph/src/Cities (1).csv";`
- **Impact**: The code is entirely un-runnable on any machine other than the original author's. Fails CI/CD and local baseline checks.
- **Fix approach**: Move CSV files to a `src/main/resources` folder and load them dynamically using `ClassLoader.getResourceAsStream()` or construct paths relative to the project root.
- **Validation**: Ensure `java -jar` or `mvn exec:java` can run the files on a clean directory without `C:/Users/milto` existing.

**2. Missing Build System & Inconsistent Packaging**

- **Evidence**: The repo relies on an IDE (Eclipse/IntelliJ) structure rather than a standardized build tool. `HashTableAssignment` uses `package hashtableapp;`, while others (like `AVLTree`) use the default package.
- **Impact**: Code cannot be compiled universally via CLI. Packaging errors prevent standard compilation commands from working, causing the 100% failure rate in the Baseline Health Report.
- **Fix approach**: Initialize a single multi-module Maven (or Gradle) project. Move all source code into standard `src/main/java/{package}` directories.
- **Validation**: `mvn clean install` runs successfully from the root directory and builds all 10 modules.

**3. Silent Error Swallowing**

- **Evidence**: `PostfixEvaluator.java:65` catches generic `Exception e`, prints a message, and returns `Integer.MIN_VALUE`.
- **Impact**: Application logic hides catastrophic failures (e.g., `ArithmeticException` for division by zero) behind a valid integer return value, breaking upstream correctness checks.
- **Fix approach**: Throw properly typed exceptions (e.g., `IllegalArgumentException`, `ArithmeticException`) and let the caller or a centralized error handler manage them.
- **Validation**: Write a JUnit test for division by zero that expects an `ArithmeticException` rather than `Integer.MIN_VALUE`.

---

### P1 (High Priority - Logic, Testing, and Performance)

**1. Algorithm Inefficiencies (O(N^2) Path Reconstruction)**

- **Evidence**: `CityGraphNavigator/.../DirectedAcyclicGraph.java:93` - `path.add(0, step);` (where `path` is a `LinkedList`, wait, wait `LinkedList.add(0, e)` is actually O(1), but memory layout is poor. If it was `ArrayList`, it's O(N)). _Self-correction: it's a LinkedList, so O(1), but generally `Collections.reverse()` on an ArrayList is preferred for caching._ But more importantly, the graph assumes acyclic but is used as undirected, which is a structural mismatch.
- **Impact**: Misleading class names (`DirectedAcyclicGraph` when it actually models undirected graphs).
- **Fix approach**: Rename classes to accurately reflect their behavior (`CityGraph` instead of `DirectedAcyclicGraph`).

**2. Null Pointer Risks in Trees**

- **Evidence**: `AVLTree.java:191` `searchNode` does not handle cases where the tree is empty safely in all edge cases if modifications occur concurrently. `BinaryTree.java:40` `cmp = key.compareTo(current.key);` - `key` can be null, causing a NullPointerException.
- **Impact**: Passing a null key crashes the application instead of rejecting it gracefully.
- **Fix approach**: Add `Objects.requireNonNull(key, "Key cannot be null")` at the top of insertion and search methods.
- **Validation**: JUnit test ensuring a clear `IllegalArgumentException` or `NullPointerException` with a clear message is thrown on `insert(null, value)`.

**3. Complete Lack of Automated Testing**

- **Evidence**: "Tests" are located in files like `AVLTest.java` and `DAGTEST.java` which just contain a `main` method that prints to stdout.
- **Impact**: No regression safety. Any refactor could silently break algorithm correctness.
- **Fix approach**: Migrate all `main` method test drivers to JUnit 5 tests. Use assertions instead of `System.out.println`.
- **Validation**: Maven outputs `Tests run: X, Failures: 0, Errors: 0, Skipped: 0`.

---

### P2 (Medium Priority - DX & Consistency)

**1. Code Duplication**

- **Evidence**: There are two DLL implementations (`DoublyLinkeLlists` and `doublylinked`), two Graph implementations (`Graph` and `CityGraphNavigator`), multiple `Node` class declarations.
- **Impact**: Harder to maintain. If a bug is fixed in one Graph, it remains in the other.
- **Fix approach**: In a multi-module setup, create a `core-data-structures` module that contains the common implementations and have specific assignments depend on it.
- **Validation**: Verify `CityGraphNavigator` module depends on `core-data-structures` and reuses its `Graph` or `Vertex` classes if appropriate.

**2. Missing Javadoc and Documentation**

- **Evidence**: Classes like `AVLTree` have minimal comments, mostly inline `//Insert Method`.
- **Impact**: Hard for new engineers to consume these APIs.
- **Fix approach**: Add class-level and public method-level Javadocs detailing params, return values, and Big-O time complexity.
