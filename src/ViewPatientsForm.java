import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewPatientsForm {
    private JFrame frame;
    private JTextField txtPatientId;
    private JTextField txtPatientName;
    private JTextArea txtResult;

    public ViewPatientsForm() {
        frame = new JFrame("Search Patient - MINT Hospital");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblPatientId = new JLabel("Enter Patient ID:");
        txtPatientId = new JTextField();
        JLabel lblPatientName = new JLabel("Enter Patient Name:");
        txtPatientName = new JTextField();
        JButton btnSearch = new JButton("Search");
        txtResult = new JTextArea(15, 50);
        txtResult.setEditable(false);
        txtResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(txtResult);

        panel.add(lblPatientId);
        panel.add(txtPatientId);
        panel.add(lblPatientName);
        panel.add(txtPatientName);
        panel.add(new JLabel()); // Spacer
        panel.add(btnSearch);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPatient();
            }
        });

        frame.setVisible(true);
    }

    private void searchPatient() {
        String patientId = txtPatientId.getText().trim();
        String patientName = txtPatientName.getText().trim();

        if (patientId.isEmpty() && patientName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter Patient ID or Name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Query for patient details
            String patientQuery = "SELECT * FROM patient WHERE Patient_ID = ? OR Name LIKE ?";
            PreparedStatement patientStmt = conn.prepareStatement(patientQuery);
            patientStmt.setString(1, patientId.isEmpty() ? null : patientId);
            patientStmt.setString(2, "%" + patientName + "%");
            ResultSet patientRs = patientStmt.executeQuery();

            StringBuilder result = new StringBuilder();
            if (patientRs.next()) {
                result.append("Patient Details:\n");
                result.append("ID: ").append(patientRs.getInt("Patient_ID")).append("\n");
                result.append("Name: ").append(patientRs.getString("Name")).append("\n");
                result.append("DOB: ").append(patientRs.getDate("Date_of_Birth")).append("\n");
                result.append("Gender: ").append(patientRs.getString("Gender")).append("\n");
                result.append("Contact: ").append(patientRs.getString("Contact_Info")).append("\n");
                result.append("Address: ").append(patientRs.getString("Address")).append("\n");
                result.append("History: ").append(patientRs.getString("Medical_History")).append("\n\n");
            } else {
                txtResult.setText("No patient found with the given details.\n");
                return;
            }

            // Query for prescriptions
            String prescriptionQuery = "SELECT * FROM prescription WHERE Patient_ID = ?";
            PreparedStatement prescriptionStmt = conn.prepareStatement(prescriptionQuery);
            prescriptionStmt.setString(1, patientId);
            ResultSet prescriptionRs = prescriptionStmt.executeQuery();

            result.append("Prescriptions:\n");
            if (!prescriptionRs.isBeforeFirst()) {
                result.append("No prescriptions found.\n");
            } else {
                while (prescriptionRs.next()) {
                    result.append("Prescription ID: ").append(prescriptionRs.getInt("Prescription_ID")).append("\n");
                    result.append("Medication: ").append(prescriptionRs.getString("Medication")).append("\n");
                    result.append("Dosage: ").append(prescriptionRs.getString("Dosage")).append("\n");
                    result.append("------------------------------\n");
                }
            }

            // Query for billing
            String billingQuery = "SELECT * FROM billing WHERE Patient_ID = ?";
            PreparedStatement billingStmt = conn.prepareStatement(billingQuery);
            billingStmt.setString(1, patientId);
            ResultSet billingRs = billingStmt.executeQuery();

            result.append("Billing:\n");
            if (!billingRs.isBeforeFirst()) {
                result.append("No billing records found.\n");
            } else {
                while (billingRs.next()) {
                    result.append("Billing ID: ").append(billingRs.getInt("Bill_ID")).append("\n");
                    result.append("Amount: ").append(billingRs.getDouble("total_Amount")).append("\n");
                    result.append("Date: ").append(billingRs.getString("Date_issued")).append("\n");
                    result.append("------------------------------\n");
                }
            }

            txtResult.setText(result.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewPatientsForm::new);
    }
}
