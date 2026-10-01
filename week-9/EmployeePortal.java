import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Employee {
    private String id;
    private String name;
    private String department;

    public Employee(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}

class EmployeePortalModel {
    private String currentPassword = "admin123";
    private List<Employee> employeeList = new ArrayList<>();

    public boolean validateLogin(String user, String pass) {
        return "admin".equals(user) && currentPassword.equals(pass);
    }

    public boolean changePassword(String oldPass, String newPass, String confirmPass) {
        if (!currentPassword.equals(oldPass)) {
            return false;
        }
        if (newPass.isEmpty() || !newPass.equals(confirmPass)) {
            return false;
        }
        currentPassword = newPass;
        return true;
    }

    public void addEmployee(String id, String name, String dept) {
        employeeList.add(new Employee(id, name, dept));
    }

    public List<Employee> getEmployees() {
        return employeeList;
    }
}

class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {
        setTitle("Employee Portal - Login");
        setSize(380, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("  Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("  Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel(""));
        loginButton = new JButton("Login");
        add(loginButton);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener l) {
        loginButton.addActionListener(l);
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
}

class MainPortalView extends JFrame {
    private JMenuBar menuBar;
    private JMenuItem addEmpItem;
    private JMenuItem viewEmpItem;
    private JMenuItem changePassItem;
    private JMenuItem logoutItem;
    private JMenuItem exitItem;
    private JTextArea displayArea;

    public MainPortalView() {
        setTitle("Employee Management Portal");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        menuBar = new JMenuBar();

        JMenu empMenu = new JMenu("Employee");
        addEmpItem = new JMenuItem("Add Employee");
        viewEmpItem = new JMenuItem("View Employee");
        empMenu.add(addEmpItem);
        empMenu.add(viewEmpItem);

        JMenu toolsMenu = new JMenu("Tools");
        changePassItem = new JMenuItem("Change Password");
        toolsMenu.add(changePassItem);

        JMenu exitMenu = new JMenu("Exit");
        logoutItem = new JMenuItem("Logout");
        exitItem = new JMenuItem("Exit Application");
        exitMenu.add(logoutItem);
        exitMenu.addSeparator();
        exitMenu.add(exitItem);

        menuBar.add(empMenu);
        menuBar.add(toolsMenu);
        menuBar.add(exitMenu);
        setJMenuBar(menuBar);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        displayArea.setText("Welcome to the Employee Management Portal.\nSelect options from the menu above.");
    }

    public void setDisplayText(String text) {
        displayArea.setText(text);
    }

    public void addMenuListeners(ActionListener addEmpL, ActionListener viewEmpL, ActionListener changePassL, ActionListener logoutL, ActionListener exitL) {
        addEmpItem.addActionListener(addEmpL);
        viewEmpItem.addActionListener(viewEmpL);
        changePassItem.addActionListener(changePassL);
        logoutItem.addActionListener(logoutL);
        exitItem.addActionListener(exitL);
    }
}

class EmployeePortalController {
    private EmployeePortalModel model;
    private LoginView loginView;
    private MainPortalView mainView;

    public EmployeePortalController(EmployeePortalModel model, LoginView loginView, MainPortalView mainView) {
        this.model = model;
        this.loginView = loginView;
        this.mainView = mainView;

        loginView.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u = loginView.getUsername();
                String p = loginView.getPassword();
                if (model.validateLogin(u, p)) {
                    JOptionPane.showMessageDialog(loginView, "Login Successful! Welcome, " + u, "Authentication", JOptionPane.INFORMATION_MESSAGE);
                    loginView.setVisible(false);
                    mainView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(loginView, "Invalid credentials. Try admin / admin123", "Authentication Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainView.addMenuListeners(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField idField = new JTextField();
                    JTextField nameField = new JTextField();
                    JTextField deptField = new JTextField();
                    Object[] message = {
                        "Employee ID:", idField,
                        "Employee Name:", nameField,
                        "Department:", deptField
                    };
                    int option = JOptionPane.showConfirmDialog(mainView, message, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        String id = idField.getText().trim();
                        String name = nameField.getText().trim();
                        String dept = deptField.getText().trim();
                        if (id.isEmpty() || name.isEmpty() || dept.isEmpty()) {
                            JOptionPane.showMessageDialog(mainView, "All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            model.addEmployee(id, name, dept);
                            JOptionPane.showMessageDialog(mainView, "Employee added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            },
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Employee> list = model.getEmployees();
                    if (list.isEmpty()) {
                        mainView.setDisplayText("No employees registered yet.");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(String.format("%-15s %-25s %-20s\n", "EMPLOYEE ID", "EMPLOYEE NAME", "DEPARTMENT"));
                        sb.append("=".repeat(65)).append("\n");
                        for (Employee emp : list) {
                            sb.append(String.format("%-15s %-25s %-20s\n", emp.getId(), emp.getName(), emp.getDepartment()));
                        }
                        mainView.setDisplayText(sb.toString());
                    }
                }
            },
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPasswordField oldPass = new JPasswordField();
                    JPasswordField newPass = new JPasswordField();
                    JPasswordField confPass = new JPasswordField();
                    Object[] message = {
                        "Old Password:", oldPass,
                        "New Password:", newPass,
                        "Confirm Password:", confPass
                    };
                    int option = JOptionPane.showConfirmDialog(mainView, message, "Change Password", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        String oldP = new String(oldPass.getPassword());
                        String newP = new String(newPass.getPassword());
                        String confP = new String(confPass.getPassword());
                        if (model.changePassword(oldP, newP, confP)) {
                            JOptionPane.showMessageDialog(mainView, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(mainView, "Password change failed. Check old password and match.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            },
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainView.setVisible(false);
                    loginView.clearFields();
                    loginView.setVisible(true);
                }
            },
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            }
        );
    }
}

public class EmployeePortal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EmployeePortalModel model = new EmployeePortalModel();
                LoginView loginView = new LoginView();
                MainPortalView mainView = new MainPortalView();
                new EmployeePortalController(model, loginView, mainView);
                loginView.setVisible(true);
            }
        });
    }
}
