import java.util.Scanner;

class Customer {
    private int customerId;
    private String customerName;
    private int unitsConsumed;
    private String connectionType;
    Customer(int customerId, String customerName, int unitsConsumed, String connectionType) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.unitsConsumed = unitsConsumed;
        this.connectionType = connectionType;
    }
    void domestic() {
        double bill;
        if (unitsConsumed <= 100)
            bill = unitsConsumed * 2;
        else if (unitsConsumed <= 200)
            bill = unitsConsumed * 3;
        else if (unitsConsumed <= 500)
            bill = unitsConsumed * 5;
        else
            bill = unitsConsumed * 7;

        double surcharge = 0;

        if (unitsConsumed > 500)
            surcharge = bill * 0.05;

        displayBill(bill, surcharge);
    }
    void commercial() {
        double bill;

        if (unitsConsumed <= 100)
            bill = unitsConsumed * 5;
        else if (unitsConsumed <= 300)
            bill = unitsConsumed * 7;
        else if (unitsConsumed <= 500)
            bill = unitsConsumed * 10;
        else
            bill = unitsConsumed * 12;

        double surcharge = 0;

        if (unitsConsumed > 500)
            surcharge = bill * 0.05;

        displayBill(bill, surcharge);
    }

    void calculateBill() {
        if (connectionType.equalsIgnoreCase("DOMESTIC"))
            domestic();
        else if (connectionType.equalsIgnoreCase("COMMERCIAL"))
            commercial();
        else
            System.out.println("Invalid Connection Type");
    }

    void calculateBill(boolean applySurcharge) {
        double bill = 0;

        if (connectionType.equalsIgnoreCase("DOMESTIC")) {
            if (unitsConsumed <= 100)
                bill = unitsConsumed * 2;
            else if (unitsConsumed <= 200)
                bill = unitsConsumed * 3;
            else if (unitsConsumed <= 500)
                bill = unitsConsumed * 5;
            else
                bill = unitsConsumed * 7;

        } else if (connectionType.equalsIgnoreCase("COMMERCIAL")) {
            if (unitsConsumed <= 100)
                bill = unitsConsumed * 5;
            else if (unitsConsumed <= 300)
                bill = unitsConsumed * 7;
            else if (unitsConsumed <= 500)
                bill = unitsConsumed * 10;
            else
                bill = unitsConsumed * 12;

        } else {
            System.out.println("Invalid Connection Type");
            return;
        }

        double surcharge = 0;

        if (applySurcharge && unitsConsumed > 500)
            surcharge = bill * 0.05;

        displayBill(bill, surcharge);
    }

    void displayBill(double bill, double surcharge) {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Customer: " + customerName);
        System.out.println("Units: " + unitsConsumed);
        System.out.println("Energy Charge: " + bill);
        System.out.println("Surcharge: " + surcharge);
        System.out.println("Final Bill: " + (bill + surcharge));
    }
}

class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Customer Name: ");
        String name = sc.next();

        System.out.print("Enter Units Consumed: ");
        int units = sc.nextInt();

        System.out.print("Enter Connection Type: ");
        String type = sc.next();

        Customer c = new Customer(id, name, units, type);

        c.calculateBill();

        sc.close();
    }
}