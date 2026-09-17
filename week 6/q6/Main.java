import banking.account.Account;
import banking.account.SavingsAccount;
import banking.account.CurrentAccount;
import banking.payment.Payment;
import banking.payment.SecurePayment;
import banking.payment.UPIPayment;
import banking.payment.CardPayment;
import banking.payment.OnlineTransaction;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Online Banking System ===");

        Account accountRef;

        accountRef = new SavingsAccount("SB-100234", "Vikram Singh", 75000.0, 4.5);
        accountRef.displayDetails();

        accountRef = new CurrentAccount("CA-900876", "Alpha Corp", 250000.0, 50000.0);
        accountRef.displayDetails();

        System.out.println("=== Payment Processing & Security ===");

        Payment upi = new UPIPayment("vikram@okaxis");
        Payment card = new CardPayment("XXXX-XXXX-XXXX-8921", "Vikram Singh");

        Payment[] payments = { upi, card };

        for (Payment payment : payments) {
            payment.pay(12500.0);

            if (payment instanceof OnlineTransaction) {
                System.out.println("Transaction Verified: Identified as an authorized OnlineTransaction.");
            }

            if (payment instanceof SecurePayment) {
                System.out.println("Security Clearance: Confirmed as enhanced SecurePayment implementation.");
            }
            System.out.println();
        }
    }
}
