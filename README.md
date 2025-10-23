# 👨‍👩‍👧‍👦 Family Tree System (Java, OOP)

A console-based **Family Tree Management System** implemented in Java.  
This program models and manipulates family relationships — including parents, children, spouses, and generations — using clear OOP structure and design patterns.

---

## 🧩 Project Overview
This model allows users to:
- Add people (Adults or Minors) with birth/death years and gender  
- Define parent–child relationships  
- Manage marriages and divorces  
- Traverse and render **ancestors** and **descendants** up to a given generation  
- Display individual information and relationships  

It uses **object-oriented design** and **strategy-based traversal and rendering** for flexible tree exploration.

---

## ⚙️ Key Features
- ✅ Add persons with automatic ID generation (`P001`, `P002`, …)
- ✅ Distinguish between `Adult` and `Minor` automatically based on age
- ✅ Enforce constraints:
  - No cycles (a person cannot be their own ancestor)
  - Max two parents per child
  - Adults only can marry
- ✅ Different traversal strategies:
  - Depth-first (`DFSTraversal`)
  - Breadth-first (`BFSTraversal`)
  - Ancestor-based (`AncestorTraversal`)
- ✅ Flexible renderers:
  - `IndentedTreeRenderer` → prints hierarchy in tree form  
  - `LineRenderer` → prints flat list

---

## 🏗️ OOP Concepts and Design Patterns Used

| Concept / Pattern | Implementation |
|--------------------|----------------|
| **Encapsulation** | Private fields with controlled getters/setters in `Person` |
| **Inheritance** | `Adult` and `Minor` inherit from abstract `Person` |
| **Polymorphism** | `TraversalStrategy` and `Renderer` interfaces have multiple implementations |
| **Factory Pattern** | `PersonFactory` dynamically creates `Adult` or `Minor` |
| **Singleton Pattern** | `PersonFactory` has a single shared instance |
| **Strategy Pattern** | `FamilyTree` can swap out traversal and rendering strategies dynamically |
| **Composition / Aggregation** | `FamilyTree` manages multiple `Person` objects and their relationships |
| **Exception Handling** | Robust validation and `IllegalArgumentException` for rule enforcement |

---
UML DIAGRAMM
<img width="1778" height="881" alt="image" src="https://github.com/user-attachments/assets/f1f3824f-dce3-4a15-bf5e-42898c653b6c" />
## 🧭 How to Run

### 🖥️ Run via IntelliJ IDEA or Terminal

1. Compile and run:
   ```bash
   javac CLI.java
   java CLI

  ```shell
  EXAMPLE COMMANDS
  ----------------------------------
  ADD_PERSON "John Doe" MALE 1980
  ADD_PERSON "Jane Smith" FEMALE 1982
  ADD_PERSON "Alice Doe" FEMALE 2010
  ADD_PARENT_CHILD P001 P003
  ADD_PARENT_CHILD P002 P003
  MARRY P001 P002 2005
  SHOW P001
  DESCENDANTS P001 2
  ANCESTORS P003 2
  SIBLINGS P003
  EXIT


  EXAMPLE OUTPUTS
  ---------------
  -> P001
  -> P002
  -> P003
  OK
  OK
  OK
  P001 | John Doe | MALE | b.1980 | spouse=P002 | children=1
  - P001 John Doe (b.1980)
    - P003 Alice Doe (b.2010)
  - P002 Jane Smith (b.1982)
    - P003 Alice Doe (b.2010)```


