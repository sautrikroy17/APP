import java.util.Scanner;

public abstract class Shape {
    public abstract double calculateArea();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter radius of Circle: ");
        double radius = scanner.nextDouble();

        System.out.print("Enter length of Rectangle: ");
        double length = scanner.nextDouble();
        System.out.print("Enter breadth of Rectangle: ");
        double breadth = scanner.nextDouble();

        System.out.print("Enter base of Triangle: ");
        double base = scanner.nextDouble();
        System.out.print("Enter height of Triangle: ");
        double height = scanner.nextDouble();

        Shape[] shapes = {
            new Circle(radius),
            new Rectangle(length, breadth),
            new Triangle(base, height)
        };

        String[] shapeNames = { "Circle", "Rectangle", "Triangle" };

        System.out.println("\n--- Calculated Areas ---");
        for (int i = 0; i < shapes.length; i++) {
            System.out.printf("%s Area: %.2f\n", shapeNames[i], shapes[i].calculateArea());
        }

        scanner.close();
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double breadth;

    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public double calculateArea() {
        return length * breadth;
    }
}

class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}
