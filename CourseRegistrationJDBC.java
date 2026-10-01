import java.sql.*;
import java.util.Scanner;

public class CourseRegistrationJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/university_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void displayStudentsByCourse(String courseCode) {
        String sql = "SELECT StudentID, StudentName, CourseCode, CourseName, Semester " +
                     "FROM CourseRegistration WHERE UPPER(CourseCode) = UPPER(?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseCode);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("No students registered for course code: " + courseCode);
                    return;
                }

                System.out.println("\nRegistered Students for " + courseCode.toUpperCase() + ":");
                System.out.printf("%-12s %-25s %-12s %-30s %-10s\n", "StudentID", "StudentName", "CourseCode", "CourseName", "Semester");
                System.out.println("=".repeat(95));

                while (rs.next()) {
                    System.out.printf("%-12s %-25s %-12s %-30s %-10d\n",
                            rs.getString("StudentID"),
                            rs.getString("StudentName"),
                            rs.getString("CourseCode"),
                            rs.getString("CourseName"),
                            rs.getInt("Semester"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Course Registration Query System (JDBC) ---");
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("Course code cannot be empty.");
        } else {
            displayStudentsByCourse(code);
        }

        sc.close();
    }
}
