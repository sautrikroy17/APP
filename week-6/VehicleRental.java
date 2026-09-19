class Vehicle {
    protected String vehicleNumber;
    protected String brand;
    protected double speed;

    public Vehicle(String vehicleNumber, String brand, double speed) {
        this.vehicleNumber = vehicleNumber;
        this.brand = brand;
        this.speed = speed;
    }

    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " km/h");
    }
}

class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String vehicleNumber, String brand, double speed, int numberOfDoors) {
        super(vehicleNumber, brand, speed);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Type: Car\n");
    }
}

class Bike extends Vehicle {
    private boolean hasGear;

    public Bike(String vehicleNumber, String brand, double speed, boolean hasGear) {
        super(vehicleNumber, brand, speed);
        this.hasGear = hasGear;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Has Gear: " + (hasGear ? "Yes" : "No"));
        System.out.println("Type: Bike\n");
    }
}

public class VehicleRental {
    public static void main(String[] args) {
        Vehicle vehicle1 = new Car("KA01AB1234", "Toyota", 140.0, 4);
        Vehicle vehicle2 = new Bike("TN02CD5678", "Yamaha", 110.0, true);

        System.out.println("--- Rental Vehicles Details ---\n");
        vehicle1.displayDetails();
        vehicle2.displayDetails();
    }
}
