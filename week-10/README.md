# Week 10: Functional Programming & Logic Programming

**Course:** `21CSC203P - Advanced Programming Practice`  
**Tutorial Date:** 15-10-2026  
**Department:** Computer Science and Engineering  

---

## 📂 Overview

This directory contains pure, verified solutions for **Tutorial 10** covering:
1. **Functional Programming Paradigms:** Higher-order functions (`filter()`, `map()`, `reduce()`), pure functions, lambda expressions, and collection immutability.
2. **Logic & Declarative Programming Paradigms:** Knowledge base representation using logical facts, inference rules, and deductive queries in Prolog, Python, and Java.

---

## 📑 Program Summary

| Problem | Java Implementation | Python Implementation | Prolog Knowledge Base | Core Topic |
| :--- | :--- | :--- | :--- | :--- |
| **Q1. Student Marks Processing** | `StudentMarksFP.java` | `student_marks_fp.py` | — | `filter()`, `map()`, `reduce()`, Lambdas |
| **Q2. Employee Salary Revision** | `SalaryRevisionFP.java` | `salary_revision_fp.py` | — | Threshold filtering, salary mapping & total reduction |
| **Q3. E-Commerce Festival Discount** | `EcommerceDiscountFP.java` | `ecommerce_discount_fp.py` | — | Pure functions, non-destructive discounts & immutability |
| **Q4. Smart Electricity Meter** | `ElectricityMeterFP.java` | `electricity_meter_fp.py` | — | Daily tariff mapping, threshold filtering & pure billing |
| **Q5. University Course Registration** | `CourseEligibilityLogic.java` | `course_eligibility.py` | `course_eligibility.pl` | Logical facts, conjunction rules & student queries |
| **Q6. Employee Promotion System** | `PromotionEligibilityLogic.java` | `promotion_eligibility.py` | `promotion_eligibility.pl` | Multi-criteria qualification rules & candidate retrieval |
| **Q7. Mall Parking Allocation** | `ParkingAllocationLogic.java` | `parking_allocation.py` | `parking_allocation.pl` | Slot availability predicates & vehicle matching rules |

---

## 📝 Detailed Question Solutions & Concept Explanations

### Question 1: Student Marks Processing
- **1a (Filter):** Filters students achieving 50 or higher (`filter(lambda s: s[1] >= 50)`).
- **1b (Map):** Awards 5 grace marks capping at 100 (`map(lambda s: (s[0], min(100, s[1] + 5)))`).
- **1c (Reduce):** Sums marks across the student list via `reduce(lambda acc, s: acc + s[1], students, 0)`.
- **1d (Lambda Explanation):** Lambda functions provide lightweight, anonymous inline function definitions. They eliminate the syntactic overhead of declaring standalone helper functions with `def`, enabling functional higher-order primitives (`map`, `filter`, `reduce`) to accept dynamic behavior directly at the call site while preserving code locality.

### Question 2: Employee Salary Revision
- **2a (Map):** Revises salaries under ₹40,000 with a 10% increment (`e[1] * 1.10`).
- **2b (Filter):** Isolates employees qualifying for the increment (`e[1] < 40000.0`).
- **2c (Reduce):** Accumulates post-revision payroll expenditure.
- **2d (Functional Suitability):** Functional programming enforces data immutability and isolates state transitions. In financial and payroll processing, this guarantees auditability, eliminates concurrency race conditions during parallel payroll batch processing, and ensures original historical records cannot be corrupted by downstream operations.

### Question 3: E-Commerce Festival Discount
- **3a (Pure Function):** `calculate_discounted_price(price)` calculates `price * 0.80` if `price > 1000` else returns original price without external state dependencies.
- **3b & 3c (Map & Filter):** Applies discount across catalog items and filters qualifying products.
- **3d (Immutability):** Pure functions are side-effect-free: they read input values and return freshly constructed data structures rather than mutating objects in-place. Consequently, the original catalog remains strictly unchanged, allowing other services (e.g., inventory, tax auditing) to access raw baseline figures safely.

### Question 4: Smart Electricity Meter
- **4a (Filter):** Isolates days where consumption strictly exceeds 10 units.
- **4b (Map):** Multiplies daily units by the ₹6/unit tariff.
- **4c (Reduce):** Sums total household monthly consumption.
- **4d (Pure Billing Function):** `calculate_bill_pure(readings, rate)` takes raw unit inputs and returns the total amount without modifying the original consumption list.

### Question 5: University Course Registration (Logic Programming)
- **5a (Facts):**
  ```prolog
  result(arun, pass, pass).
  result(bala, pass, fail).
  result(charan, pass, pass).
  result(divya, fail, pass).
  ```
- **5b (Rule):**
  ```prolog
  eligible_for_advanced(Student) :-
      result(Student, pass, pass).
  ```
- **5c (Query):**
  ```prolog
  ?- eligible_for_advanced(Student).
  % Output: Student = arun ; Student = charan.
  ```

### Question 6: Organization Promotion Eligibility (Logic Programming)
- **6a (Facts):**
  ```prolog
  employee(ravi, 6, completed, 5).
  employee(priya, 3, completed, 4).
  employee(kumar, 8, not_completed, 5).
  employee(meena, 7, completed, 4).
  ```
- **6b (Rule):**
  ```prolog
  eligible_for_promotion(Emp) :-
      employee(Emp, Exp, completed, Rating),
      Exp >= 5,
      Rating >= 4.
  ```
- **6c (Query):**
  ```prolog
  ?- eligible_for_promotion(Emp).
  % Output: Emp = ravi ; Emp = meena.
  ```

### Question 7: Shopping Mall Parking Allocation System (Logic Programming)
- **7a (Facts):**
  ```prolog
  parking_slot(s1, car, available).
  parking_slot(s2, bike, occupied).
  parking_slot(s3, car, available).
  parking_slot(s4, bike, available).
  parking_slot(s5, car, occupied).
  ```
- **7b (Rule):**
  ```prolog
  available_slot(Slot, VehicleType) :-
      parking_slot(Slot, VehicleType, available).
  ```
- **7c (Query):**
  ```prolog
  ?- available_slot(Slot, car).
  % Output: Slot = s1 ; Slot = s3.
  ```
