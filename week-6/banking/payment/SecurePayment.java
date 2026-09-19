package banking.payment;

public interface SecurePayment extends Payment {
    boolean verifyPayment();
}
