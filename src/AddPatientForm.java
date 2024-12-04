import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPatientForm {
    public static void showAddPatientForm() {
        // Create the frame
        JFrame frame = new JFrame("Add Patient - MINT Hospital");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Add Patient Details", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(new Color(34, 113, 179));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");
        JTextField dobField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JLabel contactLabel = new JLabel("Contact Info:");
        JTextField contactField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        JLabel historyLabel = new JLabel("Medical History:");
        JTextArea historyArea = new JTextArea(3, 20);

        // Add fields to panel
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(dobLabel);
        formPanel.add(dobField);
        formPanel.add(genderLabel);
        formPanel.add(genderCombo);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(historyLabel);
        formPanel.add(new JScrollPane(historyArea));

        frame.add(formPanel, BorderLayout.CENTER);

        // Buttons
        JButton addButton = new JButton("Add Patient");
        JButton cancelButton = new JButton("Cancel");

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add button functionality
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String dob = dobField.getText();
                String gender = genderCombo.getSelectedItem().toString();
                String contact = contactField.getText();
                String address = addressField.getText();
                String history = historyArea.getText();

                // Insert into database
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO patient (Name, Date_of_Birth, Gender, Contact_Info, Address, Medical_History) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, name);
                    stmt.setString(2, dob);
                    stmt.setString(3, gender);
                    stmt.setString(4, contact);
                    stmt.setString(5, address);
                    stmt.setString(6, history);

                    int rows = stmt.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(frame, "Patient added successfully!");
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: Unable to add patient.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> frame.dispose());

        // Show the frame
        frame.setVisible(true);
    }
}
