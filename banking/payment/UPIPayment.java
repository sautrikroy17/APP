package banking.payment;

public class UPIPayment implements SecurePayment, OnlineTransaction {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean verifyPayment() {
        System.out.println("Verifying UPI credentials and 2FA for: " + upiId);
        return true;
    }

    @Override
    public void pay(double amount) {
        if (verifyPayment()) {
            System.out.println("UPI Payment of ₹" + amount + " completed successfully using " + upiId);
        } else {
            System.out.println("UPI Payment Verification Failed.");
        }
    }
}
