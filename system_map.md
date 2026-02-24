# System Map: Data Structures & Algorithms

## Overview

This repository is a collection of 10 independent Java projects, each demonstrating fundamental data structures and algorithms. There is no unified application architecture; rather, the repository functions as a portfolio of isolated academic/technical exercises.

## Modules Inventory

Each module was provided as a ZIP archive and contains a standalone `src/` directory with its own entry point (driver/test class).

1. **AVL** (`AVLTest.java`): Self-balancing AVL Tree implementation.
2. **BST_Assignment** (`Test.java`): Binary Search Tree with standard operations.
3. **CityGraphNavigator** (`DAGTest.java`): Directed Acyclic Graph representing cities and paths.
4. **Complexity** (`ComplexityAssignment.java`): Loop-based Big-O complexity demonstrations.
5. **DoublyLinkeLlists** (`DoublyLinkedListTest.java`): Custom doubly linked list implementation.
6. **Graph** (`DAGTEST.java`): Generic directed acyclic graph implementation.
7. **HashTableAssignment** (`hashtableapp.Test.java`): Hash table variations (Linear Probing, Double Hashing).
8. **Postfix** (`PostfixTest.java`): Stack-based postfix expression evaluator.
9. **Recursion** (`RecursionAssignment.java`): Core recursive problem implementations.
10. **doublylinked** (`DoublyLinkedListTest.java`): Alternate doubly linked list structure.

## Core Flows & Execution Strategy

- **Flow**: User executes the `main` method in the respective entry point class. The program typically runs predefined test cases directly against the data structure and prints the output to the console.
- **Dependencies**: No external libraries, build systems (Maven/Gradle), or package managers are used. It relies purely on the Java Standard Edition (SE).

## Architecture & Findings

- **Missing Build System**: There is no overarching build configuration to compile or test the projects systematically.
- **Inconsistent Packaging**: Some classes specify `package` declarations (e.g., `hashtableapp`), while most sit in the default package.
- **Redundancy**: There are multiple graph (`Graph`, `CityGraphNavigator`) and linked list variations (`DoublyLinkeLlists`, `doublylinked`).

This map serves as a baseline for converting this collection into a structured, unified, and professionally maintainable Java repository.
