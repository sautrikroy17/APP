# Week 7

Java lab exercises for Week 7 covering packages and multithreading.

### Programs:
- `q1/`: College Management System (`student` and `course` packages, `Main.java`)
- `q2/`: Hospital Management System (`doctor` and `patient` packages, `Main.java`)
- `OnlineExam.java`: Concurrent examination activities via `Runnable`
- `BankingConcurrent.java`: Concurrent banking operations via `Runnable`
- `TrafficManagement.java`: Smart traffic junction monitoring extending `Thread`

### How to Run:
```bash
# Question 1
cd q1
javac student/Student.java course/Course.java Main.java
java Main

# Question 2
cd ../q2
javac doctor/Doctor.java patient/Patient.java Main.java
java Main

# Questions 3, 4, 5 (from root)
cd ..
javac OnlineExam.java && java OnlineExam
javac BankingConcurrent.java && java BankingConcurrent
javac TrafficManagement.java && java TrafficManagement
```
