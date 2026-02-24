# Baseline Health Report

## Execution Strategy

A custom PowerShell script (`test_all.ps1`) was created to compile the `.java` files for each module using `javac -d bin @sources.txt` and execute the recognized entry point using `java -cp bin <EntryPoint>`.

## Pass/Fail Status

Currently, compilation **FAILS** systematically across all modules in the automated baseline check.

| Module              | Compile | Execute |
| ------------------- | ------- | ------- |
| AVL                 | FAIL    | SKIP    |
| BST_Assignment      | FAIL    | SKIP    |
| CityGraphNavigator  | FAIL    | SKIP    |
| Complexity          | FAIL    | SKIP    |
| DoublyLinkeLlists   | FAIL    | SKIP    |
| Graph               | FAIL    | SKIP    |
| HashTableAssignment | FAIL    | SKIP    |
| Postfix             | FAIL    | SKIP    |
| Recursion           | FAIL    | SKIP    |
| doublylinked        | FAIL    | SKIP    |

## Logs & Diagnostics Summary

**Exact Error:**

```text
Start-Process: D:\Data-Structures---Algorithms\test_all.ps1:22
This command cannot be run due to the error: The system cannot find the file specified.
```

**Root Causes & Execution Gaps:**

1. **Missing Dev Dependencies**: `javac` is either not installed or not exposed in the system `PATH` of the current environment.
2. **Missing Build Standardization**: The repository relies on developers manually importing zip files into an IDE (Eclipse/IntelliJ) rather than providing a CLI build mechanism (like Maven `pom.xml` or Gradle `build.gradle`).
3. **Packaging Issues**: Classes in `HashTableAssignment` use package declarations, requiring correct directory structures during compilation, which unstructured `javac src/*.java` commands struggle with.
4. **No Automated Tests**: There are no JUnit or TestNG suites. "Tests" consist of manual print statement validations in `main()` methods.

## Conclusion

The repository currently has a **fail-by-default** developer experience (DX) for CLI and CI/CD operations. Evolving it to production-readiness will first require bootstrapping a proper build system (e.g., Maven) and structuring the code uniformly.
