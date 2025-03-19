package hotelbooking1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import javax.swing.SpinnerDateModel;
import com.formdev.flatlaf.FlatLightLaf;

public class Checkout extends JFrame implements ActionListener {
    private JLabel titleLabel, nameLabel, contactLabel,nicLabel, checkinLabel, checkoutLabel, timeLabel, guestsLabel;
    private JTextField nameField, contactField,nicField;
    private JSpinner checkinSpinner, checkoutSpinner, timeSpinner, guestsSpinner;
    private JButton submitButton,close;

    public Checkout() {
        setTitle("Reservation Form");
        setSize(400, 450);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        setUndecorated(true);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Reservation", JLabel.CENTER);
        titleLabel.setForeground(new java.awt.Color(25, 115, 37));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1;
        add(nameField, gbc);

        nicLabel = new JLabel("NIC Number:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add( nicLabel, gbc);
        nicField = new JTextField(15);
        gbc.gridx = 1;
        add(nicField, gbc);

        contactLabel = new JLabel("Contact Number:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(contactLabel, gbc);
        contactField = new JTextField(15);
        gbc.gridx = 1;
        add(contactField, gbc);

        checkinLabel = new JLabel("Check-in Date:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(checkinLabel, gbc);
        checkinSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor checkinEditor = new JSpinner.DateEditor(checkinSpinner, "yyyy-MM-dd");
        checkinSpinner.setEditor(checkinEditor);
        gbc.gridx = 1;
        add(checkinSpinner, gbc);

        checkoutLabel = new JLabel("Check-out Date:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(checkoutLabel, gbc);
        checkoutSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor checkoutEditor = new JSpinner.DateEditor(checkoutSpinner, "yyyy-MM-dd");
        checkoutSpinner.setEditor(checkoutEditor);
        gbc.gridx = 1;
        add(checkoutSpinner, gbc);

        timeLabel = new JLabel("Reservation Time:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(timeLabel, gbc);
        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        gbc.gridx = 1;
        add(timeSpinner, gbc);

        guestsLabel = new JLabel("Number of Guests:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(guestsLabel, gbc);
        guestsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        gbc.gridx = 1;
        add(guestsSpinner, gbc);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setBackground(new java.awt.Color(25, 115, 37));
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        close = new JButton("Cancel");
        close.addActionListener(e->close());
        close.setBackground(new java.awt.Color(119, 29, 32));
        close.setForeground(new java.awt.Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(close, gbc);

    }

    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String contact = contactField.getText().trim();
        String NIC = nicField.getText().trim();
        String checkin = new java.text.SimpleDateFormat("yyyy-MM-dd").format((Date) checkinSpinner.getValue());
        String checkout = new java.text.SimpleDateFormat("yyyy-MM-dd").format((Date) checkoutSpinner.getValue());
        String time = new java.text.SimpleDateFormat("HH:mm").format((Date) timeSpinner.getValue());
        int guests = (int) guestsSpinner.getValue();

        if (name.isEmpty() || contact.isEmpty()||NIC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (!NIC.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "Insert a valid NIC Number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!contact.matches("0[0-9]{9}")) {
            JOptionPane.showMessageDialog(this, "Invalid Contact Number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelBooking1", "root", "");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservations (name,NIC, contact, checkin, checkout, time, guests) VALUES (?, ?, ?, ?, ?, ?,?)") ) {
            stmt.setString(1, name);
            stmt.setString(2, NIC);
            stmt.setString(3, contact);
            stmt.setString(4, checkin);
            stmt.setString(5, checkout);
            stmt.setString(6, time);
            stmt.setInt(7, guests);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Reservation successful!   ", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            SwingUtilities.invokeLater(() -> new pay().setVisible(true));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void close(){
        JOptionPane.showMessageDialog(this, "Cancel Reservation? " , "Error", JOptionPane.CANCEL_OPTION);
        this.dispose();
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        SwingUtilities.invokeLater(() -> new Checkout().setVisible(true));
    }
}
