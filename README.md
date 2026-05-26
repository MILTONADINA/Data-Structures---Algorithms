# Data Structures & Algorithms — Java Portfolio

Ten standalone Java modules covering core data structures and algorithms, each set up as its own Maven sub-project with JUnit tests. Coursework from CS undergraduate work, refactored into a unified multi-module Maven layout for reproducible builds.

---

## Modules

| Directory                                          | Topic                | Notes                                                                     |
| -------------------------------------------------- | -------------------- | ------------------------------------------------------------------------- |
| [`avl/`](./avl)                                   | AVL Tree             | Self-balancing binary search tree with single + double rotations          |
| [`bst/`](./bst)                                   | Binary Search Tree   | Insert / search / delete with traversal output                             |
| [`city-graph-navigator/`](./city-graph-navigator) | Directed Graph       | City-routing DAG, file-driven adjacency, path queries                     |
| [`complexity/`](./complexity)                     | Big-O Demonstrations | Empirical timing across loop nests for complexity-class illustration      |
| [`doubly-linked-list/`](./doubly-linked-list)     | Doubly Linked List   | Insertion at head/tail/index, deletion, bidirectional traversal           |
| [`graph/`](./graph)                               | Generic Graph        | Adjacency-list directed graph with BFS/DFS                                |
| [`hash-table/`](./hash-table)                     | Hash Table           | Open addressing — linear probing and double hashing                       |
| [`postfix/`](./postfix)                           | Postfix Evaluator    | Stack-based RPN evaluator with input validation                            |
| [`recursion/`](./recursion)                       | Recursion            | Factorial, Fibonacci, and other classic recursive patterns                 |
| [`core/`](./core)                                 | Shared utilities     | Helpers and common test scaffolding used across modules                    |

---

## Build

Maven multi-module — every module can be built independently or together from the repo root.

```bash
# Build every module + run all tests
./mvnw -B verify

# Build a single module (example: AVL)
./mvnw -B -pl avl verify
```

The reactor `pom.xml` at the root wires the modules together; each module has its own `pom.xml` with module-specific dependencies and JUnit configuration.

---

## Documentation

- [`system_map.md`](./system_map.md) — module-by-module index with entry points and per-module purpose.
- [`audit_report.md`](./audit_report.md) — Phase 2 audit notes from the refactor (hardcoded paths, packaging, missing build system) and how each was addressed.
- [`baseline_health.md`](./baseline_health.md) — pre-refactor baseline showing the original build status before Maven adoption.

---

## License

MIT — see [`LICENSE`](./LICENSE).
