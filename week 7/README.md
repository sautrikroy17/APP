# Java Programming - Week 7 (Tutorial 7)

This branch contains the solutions for **Tutorial 7** (17/09/2026) of the **Advanced Programming Practice (APP - 21CSC203P)** laboratory course.

All programs are implemented strictly following clean object-oriented and concurrent programming practices in Java, formatted with **no comment lines**.

---

## 📚 Core Java Concepts Covered

- **Java Packages & Modular Architecture**: Partitioning codebases into domain-specific packages (`student`, `course`, `doctor`, `patient`).
- **Encapsulation & Domain Modeling**: Protecting class invariants with private fields and controlled getters/setters.
- **Multithreading via `Runnable`**: Decoupling thread execution from task definition for concurrent systems (`OnlineExam`, `BankingConcurrent`).
- **Multithreading via `Thread` Subclassing**: Extending `Thread` and overriding `run()` for autonomous worker tasks (`TrafficManagement`).
- **Thread Management & Coordination**: Assigning explicit identifiers with `setName()`, controlling frequency using `Thread.sleep()`, and synchronizing termination via `join()`.

---

## 📂 Programs Included

### 1. College Management System with Packages (`q1/`)
- **Concept**: Java Packages & Imports.
- **Description**:
  - `student.Student` (`student/Student.java`): Models student academic data.
  - `course.Course` (`course/Course.java`): Models course registration data.
  - `Main.java`: Imports both packages and prints composite information.
- **How to Compile & Run**:
  ```bash
  cd "week 7/q1"
  javac student/Student.java course/Course.java Main.java
  java Main
  ```

---

### 2. Hospital Management System & Consultation Fees (`q2/`)
- **Concept**: Cross-package Collaboration, Encapsulation & Dynamic Billing.
- **Description**:
  - `doctor.Doctor` (`doctor/Doctor.java`): Private attributes for `doctorId`, `name`, `specialization`, and `consultationFee`.
  - `patient.Patient` (`patient/Patient.java`): Private attributes for `patientId`, `name`, `disease`, and `age`.
  - `Main.java`: Imports Doctor and Patient, creates 2 doctors and 3 patients, matches patients to doctors based on medical condition, displays consultation records, and computes total consultation fees collected per doctor.
- **How to Compile & Run**:
  ```bash
  cd "week 7/q2"
  javac doctor/Doctor.java patient/Patient.java Main.java
  java Main
  ```

---

### 3. Online Examination Concurrent Monitoring (`OnlineExam.java`)
- **Concept**: Concurrency via `Runnable` Interface.
- **Description**: Simulates concurrent exam activities:
  1. Displaying remaining examination time
  2. Auto-saving student answers
  3. Monitoring active network connection
  Each task runs in its own named `Thread` with customized `sleep()` intervals.
- **How to Compile & Run**:
  ```bash
  cd "week 7"
  javac OnlineExam.java
  java OnlineExam
  ```

---

### 4. Concurrent Banking Services (`BankingConcurrent.java`)
- **Concept**: Multi-task Concurrency & Iterative Execution Tracking.
- **Description**: Executes 3 concurrent banking operations via `Runnable`:
  - `Transaction-Thread`: Transaction processing (500ms delay)
  - `Balance-Thread`: Balance updating (700ms delay)
  - `SMS-Thread`: SMS notification (900ms delay)
  Each thread executes exactly **three times**, reporting its thread name, activity, and execution count.
- **How to Compile & Run**:
  ```bash
  cd "week 7"
  javac BankingConcurrent.java
  java BankingConcurrent
  ```

---

### 5. Smart Traffic Junction Monitoring (`TrafficManagement.java`)
- **Concept**: Extending `Thread` Class & Periodic Polling.
- **Description**: Models three traffic junctions (`North-Cross-Junction`, `Central-Square-Junction`, `Highway-Exit-Junction`) by extending `Thread`. Each thread runs with unique sampling frequencies and logs its traffic condition 3 times.
- **How to Compile & Run**:
  ```bash
  cd "week 7"
  javac TrafficManagement.java
  java TrafficManagement
  ```
