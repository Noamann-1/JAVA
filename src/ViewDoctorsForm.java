import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ViewDoctorsForm {

    public static void showViewDoctorsForm() {
        // Create the frame
        JFrame frame = new JFrame("View Doctors - MINT Hospital");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Doctor Information", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(new Color(34, 113, 179));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Panel for buttons and search input
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Search by Specialty text field
        JLabel specialtyLabel = new JLabel("Specialty:");
        JTextField specialtyField = new JTextField(20);
        JButton searchButton = new JButton("Search by Specialty");
        JButton loadButton = new JButton("Load All Doctors");

        panel.add(specialtyLabel);
        panel.add(specialtyField);
        panel.add(searchButton);
        panel.add(loadButton);

        frame.add(panel, BorderLayout.SOUTH);

        // Table to display doctor details
        String[] columnNames = {"Doctor ID", "Name", "Specialty", "Contact Info","Experience"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        JTable doctorTable = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(doctorTable);
        frame.add(tableScrollPane, BorderLayout.CENTER);

        // Load All Doctors button action
        loadButton.addActionListener(e -> loadDoctorData(model, null));

        // Search by Specialty button action
        searchButton.addActionListener(e -> {
            String specialty = specialtyField.getText().trim();
            if (!specialty.isEmpty()) {
                loadDoctorData(model, specialty);
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a specialty to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

    private static void loadDoctorData(DefaultTableModel model, String specialty) {
        // Database connection and query to fetch doctor details
        String query;
        if (specialty == null || specialty.isEmpty()) {
            query = "SELECT Doctor_ID, Name, Specialty, Contact_Info, Experience FROM doctor";
        } else {
            query = "SELECT Doctor_ID, Name, Specialty, Contact_Info, Experience FROM doctor WHERE Specialty LIKE ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // If specialty is provided, set it in the query
            if (specialty != null && !specialty.isEmpty()) {
                stmt.setString(1, "%" + specialty + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                // Clear existing rows in the table model
                model.setRowCount(0);

                // Loop through the result set and add rows to the table model
                while (rs.next()) {
                    int doctorId = rs.getInt("Doctor_ID");
                    String name = rs.getString("Name");
                    String spec = rs.getString("Specialty");
                    String contactInfo = rs.getString("Contact_Info");
                    String experience = rs.getString("Experience");  // Retrieve Experience column

                    // If Experience is NULL, set a default value (e.g., "Not Available")
                    if (experience == null) {
                        experience = "Not Available";
                    }

                    // Add data to the table
                    model.addRow(new Object[]{doctorId, name, spec, contactInfo, experience});
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading doctor data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewDoctorsForm::showViewDoctorsForm);
    }
}
