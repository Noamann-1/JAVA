import javax.swing.*;
import java.awt.*;

public class HospitalManagementUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("MINT Hospital Management System");
        frame.setSize(600, 400); // Width and height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Welcome to MINT Hospital", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(34, 113, 179)); // Custom color
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Panel for Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Rows, columns, gaps
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Buttons
        JButton addPatientButton = new JButton("Add Patient");
        JButton viewPatientsButton = new JButton("View Patients");
        JButton viewDoctorsButton = new JButton("Search Doctor by Speciality");

        // Customize buttons
        addPatientButton.setFont(new Font("Arial", Font.PLAIN, 18));
        viewPatientsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        viewDoctorsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addPatientButton.setBackground(new Color(102, 204, 255));
        viewPatientsButton.setBackground(new Color(153, 255, 153));
        viewDoctorsButton.setBackground(new Color(255, 204, 153));

        // Add buttons to the panel
        buttonPanel.add(addPatientButton);
        buttonPanel.add(viewPatientsButton);
        buttonPanel.add(viewDoctorsButton);

        // Add panel to the frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Footer
        JLabel footerLabel = new JLabel("Powered by MINT Hospital System", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(footerLabel, BorderLayout.SOUTH);

        // Show the frame
        frame.setVisible(true);

        addPatientButton.addActionListener(e -> {
            // Open the Add Patient Form
            SwingUtilities.invokeLater(() -> AddPatientForm.showAddPatientForm());
        });


        viewPatientsButton.addActionListener(e -> {
            // Open the View Patients Form
            SwingUtilities.invokeLater(ViewPatientsForm::new);
        });

        viewDoctorsButton.addActionListener(e -> {
            SwingUtilities.invokeLater(ViewDoctorsForm::showViewDoctorsForm);
        });

    }
}
