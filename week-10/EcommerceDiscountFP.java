import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EcommerceDiscountFP {

    public static class Product {
        private final String name;
        private final double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("%s: ₹%.2f", name, price);
        }
    }

    public static double calculateDiscountedPrice(double price) {
        if (price > 1000.0) {
            return price * 0.80;
        }
        return price;
    }

    public static List<Product> filterEligibleProducts(List<Product> products) {
        return products.stream()
                .filter(p -> p.getPrice() > 1000.0)
                .collect(Collectors.toList());
    }

    public static List<Product> applyFestivalDiscount(List<Product> products) {
        return products.stream()
                .map(p -> new Product(p.getName(), calculateDiscountedPrice(p.getPrice())))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Product> catalog = Arrays.asList(
                new Product("Wireless Mouse", 650.0),
                new Product("Mechanical Keyboard", 2800.0),
                new Product("USB-C Hub", 1200.0),
                new Product("Mouse Pad", 350.0),
                new Product("Noise Cancelling Headset", 4500.0),
                new Product("Webcam HD", 950.0),
                new Product("External SSD 1TB", 7200.0)
        );

        System.out.println("Original Catalog:");
        catalog.forEach(System.out::println);

        List<Product> eligible = filterEligibleProducts(catalog);
        System.out.println("\nProducts Costing More Than ₹1,000 (filter):");
        eligible.forEach(System.out::println);

        List<Product> discountedCatalog = applyFestivalDiscount(catalog);
        System.out.println("\nCatalog After 20% Discount on Eligible Items (map):");
        discountedCatalog.forEach(System.out::println);

        System.out.println("\nVerification of Immutability (Original Catalog Unmodified):");
        catalog.forEach(System.out::println);
    }
}
