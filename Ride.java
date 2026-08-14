import java.util.Scanner;

class Ride {
    String passengerName;
    double distance;
    double ratePerKm;

    Ride(String passengerName, double distance, double ratePerKm) {
        this.passengerName = passengerName;
        this.distance = distance;
        this.ratePerKm = ratePerKm;
    }

    double calculateFare() {
        return distance * ratePerKm;
    }

    void display() {
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Rate per km: ₹" + ratePerKm);
        System.out.println("Total Fare: ₹" + calculateFare());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Passenger Name: ");
        String name = sc.next();
        System.out.println("Enter Distance: ");
        double dist = sc.nextDouble();
        System.out.println("Enter Rate per Km: ");
        double rate = sc.nextDouble();
        Ride r = new Ride(name, dist, rate);
        r.display();
        sc.close();
    }
}
