import java.sql.*;
import java.util.Scanner;

public class ProductJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void insertProduct(Scanner sc) {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, qty);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product inserted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void retrieveProduct(Scanner sc) {
        System.out.print("Enter Product ID to retrieve: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nProduct Information:");
                    System.out.println("ID: " + rs.getInt("ProductID"));
                    System.out.println("Name: " + rs.getString("ProductName"));
                    System.out.println("Price: ₹" + rs.getDouble("Price"));
                    System.out.println("Quantity: " + rs.getInt("Quantity"));
                } else {
                    System.out.println("Product not found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void updateQuantity(Scanner sc) {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter New Quantity: ");
        int qty = sc.nextInt();

        String sql = "UPDATE Product SET Quantity = ? WHERE ProductID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, qty);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product quantity updated successfully.");
            } else {
                System.out.println("Product not found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void displayLowStockProducts() {
        String sql = "SELECT * FROM Product WHERE Quantity < 10";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\nProducts with Low Stock (Quantity < 10):");
            System.out.printf("%-10s %-25s %-12s %-10s\n", "ProductID", "ProductName", "Price", "Quantity");
            System.out.println("=".repeat(60));
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-10d %-25s ₹%-11.2f %-10d\n",
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"));
            }
            if (!found) {
                System.out.println("No low stock products found.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Store Inventory System (JDBC) ---");
            System.out.println("1. Insert New Product");
            System.out.println("2. Retrieve Product by ID");
            System.out.println("3. Update Product Quantity");
            System.out.println("4. Display Low Stock Products (Quantity < 10)");
            System.out.println("5. Exit");
            System.out.print("Enter choice (1-5): ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    insertProduct(sc);
                    break;
                case 2:
                    retrieveProduct(sc);
                    break;
                case 3:
                    updateQuantity(sc);
                    break;
                case 4:
                    displayLowStockProducts();
                    break;
                case 5:
                    System.out.println("Exiting store inventory.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
