interface Confidential {
}

abstract class Document {
    protected String title;
    protected String author;

    public Document(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void displayDocumentInfo() {
        System.out.println("Title: " + title + " | Author: " + author);
    }
}

class PublicNotice extends Document {
    public PublicNotice(String title, String author) {
        super(title, author);
    }
}

class MedicalRecord extends Document implements Confidential {
    private String patientDiagnosis;

    public MedicalRecord(String title, String author, String patientDiagnosis) {
        super(title, author);
        this.patientDiagnosis = patientDiagnosis;
    }
}

class FinancialReport extends Document implements Confidential {
    private double quarterlyRevenue;

    public FinancialReport(String title, String author, double quarterlyRevenue) {
        super(title, author);
        this.quarterlyRevenue = quarterlyRevenue;
    }
}

class UserManual extends Document {
    public UserManual(String title, String author) {
        super(title, author);
    }
}

public class DocumentManagement {
    public static void main(String[] args) {
        Document[] documents = {
            new PublicNotice("Campus Holiday Announcement", "Dean Office"),
            new MedicalRecord("Patient Health History", "Dr. Wilson", "Confidential Cardiac Report"),
            new FinancialReport("Q3 Profit Analysis", "Finance Dept", 12500000.0),
            new UserManual("Lab Equipment Guide", "Tech Support")
        };

        System.out.println("--- Document Security Classification Report ---");
        for (Document doc : documents) {
            doc.displayDocumentInfo();
            if (doc instanceof Confidential) {
                System.out.println("Classification: [RESTRICTED] - This is a CONFIDENTIAL document.");
            } else {
                System.out.println("Classification: [PUBLIC] - This document is OPEN for general access.");
            }
            System.out.println();
        }
    }
}
