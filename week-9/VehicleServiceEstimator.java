import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class VehicleServiceModel {
    private String regNumber;
    private String vehicleType;
    private boolean generalService;
    private boolean oilChange;
    private boolean brakeService;
    private boolean batteryCheck;

    public void setDetails(String reg, String type, boolean gen, boolean oil, boolean brake, boolean battery) {
        this.regNumber = reg;
        this.vehicleType = type;
        this.generalService = gen;
        this.oilChange = oil;
        this.brakeService = brake;
        this.batteryCheck = battery;
    }

    public double calculateTotalCost() {
        double total = 0.0;
        if (generalService) total += 1000.0;
        if (oilChange) total += 800.0;
        if (brakeService) total += 1200.0;
        if (batteryCheck) total += 500.0;
        return total;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}

class VehicleServiceView extends JFrame {
    private JTextField regField;
    private JComboBox<String> typeCombo;
    private JCheckBox generalBox;
    private JCheckBox oilBox;
    private JCheckBox brakeBox;
    private JCheckBox batteryBox;
    private JButton calculateButton;
    private JLabel totalLabel;

    public VehicleServiceView() {
        setTitle("Vehicle Service Cost Estimator (MVC)");
        setSize(450, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Registration Number:"));
        regField = new JTextField();
        formPanel.add(regField);

        formPanel.add(new JLabel("Vehicle Type:"));
        String[] types = {"Two Wheeler", "Car"};
        typeCombo = new JComboBox<>(types);
        formPanel.add(typeCombo);

        formPanel.add(new JLabel("Services Required:"));
        JPanel checkPanel = new JPanel(new GridLayout(4, 1));
        generalBox = new JCheckBox("General Service (₹1,000)");
        oilBox = new JCheckBox("Oil Change (₹800)");
        brakeBox = new JCheckBox("Brake Service (₹1,200)");
        batteryBox = new JCheckBox("Battery Check (₹500)");
        checkPanel.add(generalBox);
        checkPanel.add(oilBox);
        checkPanel.add(brakeBox);
        checkPanel.add(batteryBox);
        formPanel.add(checkPanel);

        calculateButton = new JButton("Calculate Cost");
        formPanel.add(new JLabel(""));
        formPanel.add(calculateButton);

        formPanel.add(new JLabel("Estimated Total Cost:"));
        totalLabel = new JLabel("₹ 0.00");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        totalLabel.setForeground(new Color(25, 118, 210));
        formPanel.add(totalLabel);

        add(formPanel, BorderLayout.CENTER);
    }

    public String getRegistrationNumber() {
        return regField.getText().trim();
    }

    public String getVehicleType() {
        return (String) typeCombo.getSelectedItem();
    }

    public boolean isGeneralServiceSelected() {
        return generalBox.isSelected();
    }

    public boolean isOilChangeSelected() {
        return oilBox.isSelected();
    }

    public boolean isBrakeServiceSelected() {
        return brakeBox.isSelected();
    }

    public boolean isBatteryCheckSelected() {
        return batteryBox.isSelected();
    }

    public void setEstimatedCost(double cost) {
        totalLabel.setText(String.format("₹ %.2f", cost));
    }

    public void addCalculateListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Warning", JOptionPane.WARNING_MESSAGE);
    }
}

class VehicleServiceController {
    private VehicleServiceModel model;
    private VehicleServiceView view;

    public VehicleServiceController(VehicleServiceModel model, VehicleServiceView view) {
        this.model = model;
        this.view = view;

        this.view.addCalculateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reg = view.getRegistrationNumber();
                if (reg.isEmpty()) {
                    view.showError("Please enter vehicle registration number.");
                    return;
                }

                boolean gen = view.isGeneralServiceSelected();
                boolean oil = view.isOilChangeSelected();
                boolean brake = view.isBrakeServiceSelected();
                boolean battery = view.isBatteryCheckSelected();

                if (!gen && !oil && !brake && !battery) {
                    view.showError("Please select at least one service option.");
                    return;
                }

                model.setDetails(reg, view.getVehicleType(), gen, oil, brake, battery);
                view.setEstimatedCost(model.calculateTotalCost());
            }
        });
    }
}

public class VehicleServiceEstimator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VehicleServiceModel model = new VehicleServiceModel();
                VehicleServiceView view = new VehicleServiceView();
                new VehicleServiceController(model, view);
                view.setVisible(true);
            }
        });
    }
}
