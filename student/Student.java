package student;

public class Student {
    private String studentId;
    private String name;
    private String department;
    private int year;

    public Student(String studentId, String name, String department, int year) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.year = year;
    }

    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Year of Study: " + year);
    }
}
