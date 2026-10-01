import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginSystem extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JCheckBox rememberBox;
    private JCheckBox notifyBox;
    private JButton loginButton;

    public UserLoginSystem() {
        setTitle("User Login and Preferences");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 8, 8));

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userPanel.add(new JLabel("Username: "));
        userField = new JTextField(18);
        userPanel.add(userField);
        add(userPanel);

        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passPanel.add(new JLabel("Password:  "));
        passField = new JPasswordField(18);
        passPanel.add(passField);
        add(passPanel);

        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rememberBox = new JCheckBox("Remember Me");
        notifyBox = new JCheckBox("Receive Notifications");
        checkPanel.add(rememberBox);
        checkPanel.add(notifyBox);
        add(checkPanel);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        btnPanel.add(loginButton);
        add(btnPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(UserLoginSystem.this, "Username and password cannot be empty.", "Validation Failed", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String message = "Login Successful!\n\n"
                               + "User: " + username + "\n"
                               + "Remember Me: " + (rememberBox.isSelected() ? "Enabled" : "Disabled") + "\n"
                               + "Notifications: " + (notifyBox.isSelected() ? "Subscribed" : "Unsubscribed");

                JOptionPane.showMessageDialog(UserLoginSystem.this, message, "Authentication Status", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserLoginSystem().setVisible(true);
            }
        });
    }
}
