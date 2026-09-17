import student.Student;
import course.Course;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("RA241100301015", "Aarav Sharma", "Computer Science", 2);
        Course course = new Course("21CSC203P", "Advanced Programming Practice", 4, "Dr. Ashwini S");

        System.out.println("=== College Management System ===");
        System.out.println("\n--- Student Details ---");
        student.displayStudent();

        System.out.println("\n--- Course Details ---");
        course.displayCourse();
    }
}
