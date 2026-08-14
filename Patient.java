import java.util.*;
class Patient {
    String patientName;
    double consultationFee;
    Patient(String patientName, double consultationFee) {
        this.patientName = patientName;
        this.consultationFee = consultationFee;
    }

    double calculateAmount(double fee) {

        double discount;

        if (fee >= 2000) {
            discount = fee * 10 / 100;
        } else {
            discount = fee * 5 / 100;
        }

        return fee - discount;
    }
    void display(double discount, double finalAmount) {

        System.out.println("Patient Name: " + patientName);
        System.out.println("Original Fee: ₹" + consultationFee);
        System.out.println("Discount: ₹" + discount);
        System.out.println("Final Amount: ₹" + finalAmount);
        System.out.println();
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Patient[] patient = new Patient[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter Patient Name:");
            String patientName = sc.next();
            System.out.println("Enter Consultation Fee:");
            double consultationFee = sc.nextDouble();
            patient[i] = new Patient(patientName, consultationFee);
            double discount;
            if (consultationFee >= 2000) {
                discount = consultationFee * 10 / 100;
            } else {
                discount = consultationFee * 5 / 100;
            }
            double finalAmount = patient[i].calculateAmount(consultationFee);
            patient[i].display(discount, finalAmount);
        }
        sc.close();
    }
}