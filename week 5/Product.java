import java.util.Scanner;

public abstract class Product {
    protected int productId;
    protected String name;
    protected double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public abstract double calculateDiscount();

    public double getFinalPrice() {
        return price - calculateDiscount();
    }

    public void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + name);
        System.out.println("Original Price: ₹" + price);
        System.out.println("Discount: ₹" + calculateDiscount());
        System.out.println("Final Price: ₹" + getFinalPrice());
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Electronics Details (ID, Name, Price):");
        int eId = scanner.nextInt();
        String eName = scanner.next();
        double ePrice = scanner.nextDouble();

        System.out.println("Enter Clothing Details (ID, Name, Price):");
        int cId = scanner.nextInt();
        String cName = scanner.next();
        double cPrice = scanner.nextDouble();

        System.out.println("Enter Books Details (ID, Name, Price):");
        int bId = scanner.nextInt();
        String bName = scanner.next();
        double bPrice = scanner.nextDouble();

        Product[] products = {
            new Electronics(eId, eName, ePrice),
            new Clothing(cId, cName, cPrice),
            new Books(bId, bName, bPrice)
        };

        System.out.println("\n--- Product Pricing Summary ---");
        for (Product product : products) {
            product.displayDetails();
        }

        scanner.close();
    }
}

class Electronics extends Product {
    public Electronics(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return price * 0.15;
    }
}

class Clothing extends Product {
    public Clothing(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return price * 0.20;
    }
}

class Books extends Product {
    public Books(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return price * 0.10;
    }
}
