# Java Programming - Week 5 (Tutorial 5)

This branch contains the solutions for **Tutorial 5** (21/08/2026) of the **Advanced Programming Practice (APP - 21CSC203P)** laboratory course.

All source code has been implemented adhering to object-oriented programming standards in Java, with clean formatting and no extraneous comment lines.

---

## 📚 Core OOP Concepts Covered

- **Encapsulation**: Restricting direct access to sensitive fields (`private balance`) using access mutators and methods.
- **Inheritance & Method Overriding**: Establishing parent-child class hierarchies and redefining polymorphic behavior across specialized subclasses.
- **Interfaces & Abstraction**: Defining abstract contracts for pluggable systems (`PaymentMethod`, `Product`, `Shape`).
- **Polymorphism**: Dynamic method dispatch across heterogenous object arrays and runtime type resolution.
- **Object Arrays & Custom Sorting**: Managing collections of domain entities and ranking them with multi-level criteria.

---

## 📂 Programs Included

### 1. Bank Account Management (`BankAccount.java`)
- **Concept**: Encapsulation & Access Modifiers.
- **Description**: Implements a `BankAccount` class where account number, account holder name, and balance are encapsulated. Direct balance modification is prevented. Deposits and withdrawals are processed with strict boundary and validation checks.
- **How to Compile & Run**:
  ```bash
  cd "week 5"
  javac BankAccount.java
  java BankAccount
  ```

---

### 2. University Employee Hierarchy (`Employee.java`)
- **Concept**: Inheritance & Method Overriding.
- **Description**: Implements a base `Employee` class extended by `Professor`, `LabAssistant`, and `AdministrativeStaff`. Each child class overrides the `calculateSalary()` method to calculate compensation according to specific DA, HRA, and specialized allowance rules.
- **How to Compile & Run**:
  ```bash
  cd "week 5"
  javac Employee.java
  java Employee
  ```

---

### 3. E-Commerce Payment Gateway (`PaymentSystem.java`)
- **Concept**: Interfaces & Polymorphism.
- **Description**: Models a flexible payment gateway using a `PaymentMethod` interface with a `makePayment(double amount)` contract. Supports `UPIPayment`, `CreditCardPayment`, and `NetBankingPayment`. The payment method is selected interactively and executed polymorphically.
- **How to Compile & Run**:
  ```bash
  cd "week 5"
  javac PaymentSystem.java
  java PaymentSystem
  ```

---

### 4. Product Category & Discount Engine (`Product.java`)
- **Concept**: Abstract Classes & Dynamic Dispatch.
- **Description**: Features an abstract `Product` base class with category subclasses for `Electronics` (15% discount), `Clothing` (20% discount), and `Books` (10% discount). Calculates the final discounted price polymorphically across an array of products.
- **How to Compile & Run**:
  ```bash
  cd "week 5"
  javac Product.java
  java Product
  ```

---

### 5. Geometric Shape Area Calculator (`Shape.java`)
- **Concept**: Abstract Methods & Polymorphism.
- **Description**: Defines an abstract `Shape` class with an abstract `calculateArea()` method. Subclasses `Circle`, `Rectangle`, and `Triangle` accept interactive dimensions and calculate precise geometric areas.
- **How to Compile & Run**:
  ```bash
  cd "week 5"
  javac Shape.java
  java Shape
  ```

---

### 6. Campus Placement Drive Shortlisting (`Candidate.java`)
- **Concept**: Object Arrays, Aggregation & Custom Priority Sorting.
- **Description**: Manages candidate evaluations across Aptitude, Technical, and Communication assessments. Computes total composite scores and shortlists the Top $K$ candidates.
- **Ranking Rules**:
  1. Higher overall score receives higher rank.
  2. If scores are tied, the candidate with the **smaller Candidate ID** takes higher priority.
- **Input Format**:
  ```text
  N K
  CandidateID Name Aptitude Technical Communication
  ...
  ```
- **Test Case 1 Verification**:
  - **Input**:
    ```text
    5 3
    101 Arun 80 90 70
    102 Priya 90 85 80
    103 Ravi 70 95 75
    104 Divya 85 85 85
    105 Kiran 80 80 70
    ```
  - **Output**:
    ```text
    102 Priya 255
    104 Divya 255
    101 Arun 240
    ```
- **How to Compile & Run**:
  ```bash
  cd "week 5"
  javac Candidate.java
  java Candidate
  ```

---

## 🛠️ Quick Execution Guide

To compile and verify all programs from the repository root:

```bash
# Compile all programs
javac "week 5"/*.java

# Run Candidate program test
java -cp "week 5" Candidate
```
