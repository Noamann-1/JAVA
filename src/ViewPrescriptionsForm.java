import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewPrescriptionsForm {

    public static void showPrescriptionsForm(String patientId) {
        // Create a frame
        JFrame frame = new JFrame("Prescription - MINT Hospital");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add logic to query prescriptions from the database based on patientId
        JTextArea prescriptionArea = new JTextArea(10, 50);
        prescriptionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(prescriptionArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM prescription WHERE Patient_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(patientId));

            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                prescriptionArea.setText("No prescription found for this patient.");
            } else {
                while (rs.next()) {
                    prescriptionArea.append("Prescription ID: " + rs.getInt("Prescription_ID") + "\n");
                    prescriptionArea.append("Medication: " + rs.getString("Medication") + "\n");
                    prescriptionArea.append("Dosage: " + rs.getString("Dosage") + "\n");
                    prescriptionArea.append("Date: " + rs.getString("Date") + "\n");
                    prescriptionArea.append("----------------------------------------------------\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: Unable to retrieve prescription.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }
}
