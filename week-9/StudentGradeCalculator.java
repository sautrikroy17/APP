import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StudentModel {
    private String studentName;
    private double mark1;
    private double mark2;
    private double mark3;

    public void setStudentDetails(String name, double m1, double m2, double m3) {
        this.studentName = name;
        this.mark1 = m1;
        this.mark2 = m2;
        this.mark3 = m3;
    }

    public double getTotalMarks() {
        return mark1 + mark2 + mark3;
    }

    public double getAverageMarks() {
        return getTotalMarks() / 3.0;
    }

    public String getGrade() {
        double avg = getAverageMarks();
        if (avg >= 90) return "A";
        else if (avg >= 75) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }

    public String getStudentName() {
        return studentName;
    }
}

class StudentView extends JFrame {
    private JTextField nameField;
    private JTextField mark1Field;
    private JTextField mark2Field;
    private JTextField mark3Field;
    private JButton calculateButton;
    private JLabel totalLabel;
    private JLabel averageLabel;
    private JLabel gradeLabel;

    public StudentView() {
        setTitle("Student Grade Calculator (MVC)");
        setSize(420, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 8, 8));

        add(new JLabel("Student Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Subject 1 Marks:"));
        mark1Field = new JTextField();
        add(mark1Field);

        add(new JLabel("Subject 2 Marks:"));
        mark2Field = new JTextField();
        add(mark2Field);

        add(new JLabel("Subject 3 Marks:"));
        mark3Field = new JTextField();
        add(mark3Field);

        calculateButton = new JButton("Calculate Result");
        add(new JLabel(""));
        add(calculateButton);

        add(new JLabel("Total Marks:"));
        totalLabel = new JLabel("-");
        add(totalLabel);

        add(new JLabel("Average Marks:"));
        averageLabel = new JLabel("-");
        add(averageLabel);

        add(new JLabel("Grade:"));
        gradeLabel = new JLabel("-");
        add(gradeLabel);
    }

    public String getStudentName() {
        return nameField.getText().trim();
    }

    public String getMark1() {
        return mark1Field.getText().trim();
    }

    public String getMark2() {
        return mark2Field.getText().trim();
    }

    public String getMark3() {
        return mark3Field.getText().trim();
    }

    public void setResult(double total, double avg, String grade) {
        totalLabel.setText(String.format("%.2f / 300", total));
        averageLabel.setText(String.format("%.2f", avg));
        gradeLabel.setText(grade);
    }

    public void addCalculateListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}

class StudentController {
    private StudentModel model;
    private StudentView view;

    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;

        this.view.addCalculateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = view.getStudentName();
                    if (name.isEmpty()) {
                        view.showError("Please enter student name.");
                        return;
                    }
                    double m1 = Double.parseDouble(view.getMark1());
                    double m2 = Double.parseDouble(view.getMark2());
                    double m3 = Double.parseDouble(view.getMark3());

                    if (m1 < 0 || m1 > 100 || m2 < 0 || m2 > 100 || m3 < 0 || m3 > 100) {
                        view.showError("Marks must be between 0 and 100.");
                        return;
                    }

                    model.setStudentDetails(name, m1, m2, m3);
                    view.setResult(model.getTotalMarks(), model.getAverageMarks(), model.getGrade());
                } catch (NumberFormatException ex) {
                    view.showError("Please enter valid numeric marks for all subjects.");
                }
            }
        });
    }
}

public class StudentGradeCalculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentModel model = new StudentModel();
                StudentView view = new StudentView();
                new StudentController(model, view);
                view.setVisible(true);
            }
        });
    }
}
