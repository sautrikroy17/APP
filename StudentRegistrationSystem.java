import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegistrationSystem extends JFrame {
    private JTextField nameField;
    private JTextField regNoField;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JRadioButton otherRadio;
    private ButtonGroup genderGroup;
    private JComboBox<String> deptCombo;
    private JButton submitButton;

    public StudentRegistrationSystem() {
        setTitle("Student Registration System");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Student Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Register Number:"));
        regNoField = new JTextField();
        add(regNoField);

        add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        add(genderPanel);

        add(new JLabel("Department:"));
        String[] depts = {"Computer Science", "Information Technology", "Electronics and Communication", "Mechanical Engineering", "Biotechnology"};
        deptCombo = new JComboBox<>(depts);
        add(deptCombo);

        submitButton = new JButton("Submit Registration");
        add(new JLabel(""));
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String regNo = regNoField.getText().trim();
                String gender = "Not Specified";
                if (maleRadio.isSelected()) gender = "Male";
                else if (femaleRadio.isSelected()) gender = "Female";
                else if (otherRadio.isSelected()) gender = "Other";

                String dept = (String) deptCombo.getSelectedItem();

                if (name.isEmpty() || regNo.isEmpty()) {
                    JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Please enter all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String summary = "Registration Confirmed:\n\n"
                               + "Name: " + name + "\n"
                               + "Register No: " + regNo + "\n"
                               + "Gender: " + gender + "\n"
                               + "Department: " + dept;

                JOptionPane.showMessageDialog(StudentRegistrationSystem.this, summary, "Student Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentRegistrationSystem().setVisible(true);
            }
        });
    }
}
