import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ViewBillingsForm {
    public static void showBillingsForm(int patientId) {
        JFrame frame = new JFrame("Billing - MINT Hospital");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Header Label
        JLabel headerLabel = new JLabel("Billing for Patient ID: " + patientId, SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(new Color(34, 113, 179));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Create result panel (table for billings)
        JTable billingsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(billingsTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Query for billings
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM billing WHERE Patient_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            // Set table model dynamically from result set
            billingsTable.setModel(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error retrieving billings.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }

    // Convert ResultSet to TableModel
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Get column names
        Vector<String> columnNames = new Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }

        // Get data
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }
}
