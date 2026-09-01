package banking.payment;

public class CardPayment implements Payment, OnlineTransaction {
    private String cardNumber;
    private String cardHolder;

    public CardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Card Payment of ₹" + amount + " debited from card [" + cardNumber + "] for " + cardHolder);
    }
}
