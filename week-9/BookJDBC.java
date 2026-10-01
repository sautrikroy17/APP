import java.sql.*;
import java.util.Scanner;

public class BookJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void insertBook(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Availability (Available/Issued): ");
        String avail = sc.nextLine();

        String sql = "INSERT INTO Book (BookID, Title, Author, Price, Availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setDouble(4, price);
            pstmt.setString(5, avail);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book record successfully inserted.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void searchBook(Scanner sc) {
        System.out.print("Enter Book ID to search: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM Book WHERE BookID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nBook Details Found:");
                    System.out.println("Book ID: " + rs.getInt("BookID"));
                    System.out.println("Title: " + rs.getString("Title"));
                    System.out.println("Author: " + rs.getString("Author"));
                    System.out.println("Price: ₹" + rs.getDouble("Price"));
                    System.out.println("Availability: " + rs.getString("Availability"));
                } else {
                    System.out.println("No book found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void displayAvailableBooks() {
        String sql = "SELECT * FROM Book WHERE Availability = 'Available'";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\nAvailable Books in Library:");
            System.out.printf("%-10s %-30s %-20s %-10s\n", "BookID", "Title", "Author", "Price");
            System.out.println("=".repeat(75));
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-10d %-30s %-20s ₹%-9.2f\n",
                        rs.getInt("BookID"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getDouble("Price"));
            }
            if (!found) {
                System.out.println("No books currently available.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void changeAvailability(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter New Status (Available/Issued): ");
        String status = sc.nextLine();

        String sql = "UPDATE Book SET Availability = ? WHERE BookID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Availability status updated successfully.");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Library Book Management (JDBC) ---");
            System.out.println("1. Insert New Book");
            System.out.println("2. Search Book by ID");
            System.out.println("3. Display All Available Books");
            System.out.println("4. Update Book Availability Status");
            System.out.println("5. Exit");
            System.out.print("Enter choice (1-5): ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    insertBook(sc);
                    break;
                case 2:
                    searchBook(sc);
                    break;
                case 3:
                    displayAvailableBooks();
                    break;
                case 4:
                    changeAvailability(sc);
                    break;
                case 5:
                    System.out.println("Exiting library portal.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }
}
