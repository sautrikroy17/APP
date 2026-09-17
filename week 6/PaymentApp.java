interface Payment {
    void pay(double amount);
}

class CreditCardPayment implements Payment {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing Credit Card payment of ₹" + amount);
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Transaction Completed Successfully.\n");
    }
}

class UPIPayment implements Payment {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing UPI payment of ₹" + amount);
        System.out.println("UPI ID: " + upiId);
        System.out.println("Transaction Completed Successfully.\n");
    }
}

class NetBankingPayment implements Payment {
    private String bankName;
    private String accountNumber;

    public NetBankingPayment(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing Net Banking payment of ₹" + amount);
        System.out.println("Bank: " + bankName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Transaction Completed Successfully.\n");
    }
}

public class PaymentApp {
    public static void main(String[] args) {
        Payment paymentRef;

        paymentRef = new CreditCardPayment("4111-2222-3333-4444", "John Doe");
        paymentRef.pay(4999.00);

        paymentRef = new UPIPayment("user@okaxis");
        paymentRef.pay(1250.50);

        paymentRef = new NetBankingPayment("HDFC Bank", "50100234567890");
        paymentRef.pay(15000.00);
    }
}
