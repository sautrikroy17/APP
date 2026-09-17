import java.util.Scanner;

interface PaymentMethod {
    void makePayment(double amount);
}

class UPIPayment implements PaymentMethod {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void makePayment(double amount) {
        System.out.println("Processing UPI payment of ₹" + amount);
        System.out.println("UPI ID: " + upiId);
        System.out.println("Payment Successful via UPI!\n");
    }
}

class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void makePayment(double amount) {
        System.out.println("Processing Credit Card payment of ₹" + amount);
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Payment Successful via Credit Card!\n");
    }
}

class NetBankingPayment implements PaymentMethod {
    private String bankName;
    private String accountNumber;

    public NetBankingPayment(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    @Override
    public void makePayment(double amount) {
        System.out.println("Processing Net Banking payment of ₹" + amount);
        System.out.println("Bank: " + bankName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Payment Successful via Net Banking!\n");
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\nSelect Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Credit Card");
        System.out.println("3. Net Banking");
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        PaymentMethod payment = null;

        switch (choice) {
            case 1:
                System.out.print("Enter UPI ID: ");
                String upiId = scanner.nextLine();
                payment = new UPIPayment(upiId);
                break;
            case 2:
                System.out.print("Enter Card Holder Name: ");
                String holder = scanner.nextLine();
                System.out.print("Enter Card Number: ");
                String cardNum = scanner.nextLine();
                payment = new CreditCardPayment(cardNum, holder);
                break;
            case 3:
                System.out.print("Enter Bank Name: ");
                String bank = scanner.nextLine();
                System.out.print("Enter Account Number: ");
                String accNum = scanner.nextLine();
                payment = new NetBankingPayment(bank, accNum);
                break;
            default:
                System.out.println("Invalid payment method selected.");
                scanner.close();
                return;
        }

        System.out.println("\n--- Payment Receipt ---");
        payment.makePayment(amount);

        scanner.close();
    }
}
