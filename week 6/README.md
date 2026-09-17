# Java Programming - Week 6 (Tutorial 6)

This branch contains the solutions for **Tutorial 6** (31/08/2026) of the **Advanced Programming Practice (APP - 21CSC203P)** laboratory course.

All programs have been implemented strictly following object-oriented architecture in Java, formatted cleanly with **no comment lines**.

---

## 📚 Core OOP Concepts Covered

- **Runtime Polymorphism**: Invoking overridden subclass methods dynamically through superclass/interface references.
- **Interfaces & Pluggable Architecture**: Designing decoupled systems using interface contracts (`Payment`).
- **Interface Inheritance**: Extending interfaces (`AdvancedSecurity extends BasicSecurity`) to build layered capabilities.
- **Marker / Tagging Interfaces**: Using empty interfaces (`Confidential`, `OnlineTransaction`) to categorize classes and query them at runtime via `instanceof`.
- **Java Packages & Modularity**: Encapsulating related classes in dedicated packages (`student`, `course`, `banking.account`, `banking.payment`).

---

## 📂 Programs Included

### 1. Vehicle Rental System (`VehicleRental.java`)
- **Concept**: Subclassing & Runtime Polymorphism.
- **Description**: Models a `Vehicle` base class with `Car` (`numberOfDoors`) and `Bike` (`hasGear`) subclasses. Uses a `Vehicle` reference to invoke `displayDetails()` polymorphically.
- **How to Compile & Run**:
  ```bash
  cd "week 6"
  javac VehicleRental.java
  java VehicleRental
  ```

---

### 2. E-Commerce Payment Gateway (`PaymentApp.java`)
- **Concept**: Interface Contracts & Dynamic Dispatch.
- **Description**: Defines a `Payment` interface with `pay(double amount)`. Implemented by `CreditCardPayment`, `UPIPayment`, and `NetBankingPayment`. A `Payment` interface reference invokes the specific payment channel.
- **How to Compile & Run**:
  ```bash
  cd "week 6"
  javac PaymentApp.java
  java PaymentApp
  ```

---

### 3. Layered Banking Security (`BankingSecurity.java`)
- **Concept**: Interface Inheritance.
- **Description**: Extends `BasicSecurity` (`login`, `logout`) into `AdvancedSecurity` adding biometric capabilities (`fingerprintAuth`, `faceRecognitionAuth`). Implemented inside `BankingApplication`.
- **How to Compile & Run**:
  ```bash
  cd "week 6"
  javac BankingSecurity.java
  java BankingSecurity
  ```

---

### 4. Document Management & Classification (`DocumentManagement.java`)
- **Concept**: Tagging / Marker Interface & `instanceof`.
- **Description**: Defines an empty marker interface `Confidential`. Classes like `MedicalRecord` and `FinancialReport` implement it, while `PublicNotice` and `UserManual` do not. The system scans documents and uses `instanceof` to classify confidentiality.
- **How to Compile & Run**:
  ```bash
  cd "week 6"
  javac DocumentManagement.java
  java DocumentManagement
  ```

---

### 5. College Management System with Packages (`q5/`)
- **Concept**: Packages, Imports & Namespaces.
- **Description**:
  - `student.Student` (`student/Student.java`): Manages student profiles.
  - `course.Course` (`course/Course.java`): Manages academic courses.
  - `Main.java`: Imports both packages and presents student-course mappings.
- **How to Compile & Run**:
  ```bash
  cd "week 6/q5"
  javac student/Student.java course/Course.java Main.java
  java Main
  ```

---

### 6. Enterprise Online Banking Architecture (`q6/`)
- **Concept**: Multi-package Architecture, Polymorphism, Marker Interfaces.
- **Description**:
  - Package `banking.account`: `Account` superclass, `SavingsAccount`, and `CurrentAccount`.
  - Package `banking.payment`: `Payment` interface, `SecurePayment` interface, `OnlineTransaction` marker interface, `UPIPayment`, and `CardPayment`.
  - `Main.java`: Demonstrates account polymorphism, secure payments, and metadata tagging checks.
- **How to Compile & Run**:
  ```bash
  cd "week 6/q6"
  javac banking/account/*.java banking/payment/*.java Main.java
  java Main
  ```
