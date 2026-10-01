import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseManagementSystem extends JFrame {
    private JTextField studentNameField;
    private JList<String> courseList;
    private JTable registrationTable;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton removeButton;

    public CourseManagementSystem() {
        setTitle("Student Course Management System");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        topPanel.add(new JLabel("Student Name:"));
        studentNameField = new JTextField(15);
        topPanel.add(studentNameField);

        addButton = new JButton("Add Registration");
        removeButton = new JButton("Remove Registration");
        topPanel.add(addButton);
        topPanel.add(removeButton);
        add(topPanel, BorderLayout.NORTH);

        String[] courses = {
            "Advanced Java Programming",
            "Data Structures and Algorithms",
            "Operating Systems Principles",
            "Database Management Systems",
            "Computer Communication Networks",
            "Software Engineering and Architecture"
        };
        courseList = new JList<>(courses);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseList.setSelectedIndex(0);
        JScrollPane listScrollPane = new JScrollPane(courseList);
        listScrollPane.setPreferredSize(new Dimension(250, 0));
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Available Courses"));
        add(listScrollPane, BorderLayout.WEST);

        String[] columns = {"Student Name", "Selected Course", "Enrollment Status"};
        tableModel = new DefaultTableModel(columns, 0);
        registrationTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(registrationTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Registered Students"));
        add(tableScrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = studentNameField.getText().trim();
                String selectedCourse = courseList.getSelectedValue();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(CourseManagementSystem.this, "Please enter student name.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (selectedCourse == null) {
                    JOptionPane.showMessageDialog(CourseManagementSystem.this, "Please select a course from the list.", "Missing Selection", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                tableModel.addRow(new Object[]{name, selectedCourse, "Enrolled"});
                studentNameField.setText("");
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = registrationTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(CourseManagementSystem.this, "Please select an enrollment record from the table to remove.", "Selection Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                tableModel.removeRow(selectedRow);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CourseManagementSystem().setVisible(true);
            }
        });
    }
}
