/*
 * Jacob Cannamela
 * CSD 420
 * Assignment 10
 * 5/3/2025
 *
 * This program allows the user to display and update fan information
 * stored in a MySQL database.
 */

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FanViewer extends JFrame {

    // Database connection details
    static final String DB_URL = "jdbc:mysql://localhost:3306/databasedb";
    static final String USER = "student1";
    static final String PASS = "pass";

    // Input fields for user to enter or display fan data
    private JTextField idField = new JTextField(10);
    private JTextField firstNameField = new JTextField(25);
    private JTextField lastNameField = new JTextField(25);
    private JTextField favoriteTeamField = new JTextField(25);

    // Buttons to control actions
    private JButton displayButton = new JButton("Display");
    private JButton updateButton = new JButton("Update");

    // Constructor sets up the user interface
    public FanViewer() {
        super("Fan Viewer and Updater");

        // Using BorderLayout to organize the GUI
        setLayout(new BorderLayout());

        // Create and add the input fields in a grid layout
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Favorite Team:"));
        formPanel.add(favoriteTeamField);
        add(formPanel, BorderLayout.CENTER);

        // Style and organize the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        displayButton.setFont(new Font("Arial", Font.PLAIN, 14));
        updateButton.setFont(new Font("Arial", Font.PLAIN, 14));
        displayButton.setPreferredSize(new Dimension(120, 35));
        updateButton.setPreferredSize(new Dimension(120, 35));

        // Give the buttons some color to look better
        displayButton.setBackground(new Color(66, 135, 245));
        displayButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(88, 176, 102));
        updateButton.setForeground(Color.WHITE);

        buttonPanel.add(displayButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // space between buttons
        buttonPanel.add(updateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up what happens when buttons are clicked
        displayButton.addActionListener(e -> displayFan());
        updateButton.addActionListener(e -> updateFan());

        // Final frame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null); // center on screen
        setVisible(true);
    }

    /**
     * Method to look up a fan in the database using their ID
     * and populate the form fields with their information.
     */
    private void displayFan() {
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);

            // Create connection and query
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement("SELECT firstname, lastname, favoriteteam FROM fans WHERE ID = ?")) {

                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                // If a matching record is found, show it in the form
                if (rs.next()) {
                    String fn = rs.getString("firstname");
                    String ln = rs.getString("lastname");
                    String ft = rs.getString("favoriteteam");

                    firstNameField.setText(fn);
                    lastNameField.setText(ln);
                    favoriteTeamField.setText(ft);

                    // Show popup to confirm the fan was found
                    String message = String.format("Fan Details:\n%s %s\nTeam: %s", fn, ln, ft);
                    JOptionPane.showMessageDialog(this, message, "Fan Found", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Clear fields and notify user if no record found
                    JOptionPane.showMessageDialog(this, "No fan found with ID: " + id);
                    firstNameField.setText("");
                    lastNameField.setText("");
                    favoriteTeamField.setText("");
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be a number.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    /**
     * Method to update a fan's information in the database
     * after confirming with the user.
     */
    private void updateFan() {
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String favoriteTeam = favoriteTeamField.getText().trim();

            // Show confirmation dialog with fan info
            String message = String.format(
                "Are you sure you want to update user %d?\n\nName: %s %s\nTeam: %s",
                id, firstName, lastName, favoriteTeam
            );

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Confirm Update",
                    JOptionPane.YES_NO_OPTION
            );

            // If user chooses 'No', cancel update
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Update fan record in the database
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?")) {

                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, favoriteTeam);
                stmt.setInt(4, id);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Fan updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "No fan found with ID: " + id);
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be a number.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Update failed: " + e.getMessage());
        }
    }

    // Program entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FanViewer::new);
    }
}