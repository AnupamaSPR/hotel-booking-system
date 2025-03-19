package hotelbooking1;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signup extends javax.swing.JFrame {
    public Signup() {

        initComponents();
        setLocationRelativeTo(null); // Center window
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        signup = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        uname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        passw = new javax.swing.JPasswordField();
        rpassword = new javax.swing.JPasswordField();
        showPasswordCheck = new javax.swing.JCheckBox();


        setTitle("Signup");
        setTitle("Sandy Beach Hotel");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);

        jPanel1.setBackground(new java.awt.Color(51, 51, 102));
        jPanel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("Signup");

        jLabel2.setForeground(Color.WHITE);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setForeground(Color.WHITE);
        jLabel5.setForeground(Color.WHITE);

        jLabel2.setText("Username:");
        jLabel3.setText("Email:");
        jLabel4.setText("Password:");
        jLabel5.setText("Re-type Password:");

        signup.setText("Signup");
        signup.setBackground(new Color(102, 255, 102));
        signup.setForeground(Color.BLACK);
        signup.addActionListener(evt -> signupActionPerformed());

        backButton.setText("Go Back");
        backButton.setBackground(new Color(255, 102, 102));
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(evt -> goBack());

        loginButton.setText("Click here to login");
        loginButton.setBackground(new Color(102, 178, 255));
        loginButton.setForeground(Color.BLACK);
        loginButton.addActionListener(evt -> goToLogin());

        showPasswordCheck.setText("Show Password");
        showPasswordCheck.setForeground(Color.WHITE);
        showPasswordCheck.setBackground(new Color(51, 51, 102));
        showPasswordCheck.addActionListener(evt -> togglePasswordVisibility());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(uname, 200, 200, 200)
                                        .addComponent(email, 200, 200, 200)
                                        .addComponent(passw, 200, 200, 200)
                                        .addComponent(rpassword, 200, 200, 200)))
                        .addComponent(showPasswordCheck)
                        .addComponent(signup)
                        .addComponent(loginButton)
                        .addComponent(backButton)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(uname))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(email))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(passw))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(rpassword))
                        .addComponent(showPasswordCheck)
                        .addComponent(signup)
                        .addComponent(loginButton)
                        .addComponent(backButton)
        );

        add(jPanel1);
        pack();
    }

    private void signupActionPerformed() {
        String username = uname.getText().trim();
        String emailText = email.getText().trim();
        String password = new String(passw.getPassword());
        String confirmPassword = new String(rpassword.getPassword());

        if (username.isEmpty() || emailText.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelBooking1", "root", "");
             PreparedStatement ps = con.prepareStatement("INSERT INTO signup(username, email, password) VALUES (?, ?, ?)");) {

            ps.setString(1, username);
            ps.setString(2, emailText);
            ps.setString(3, password);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Registered successfully");
                goToLogin();
            } else {
                JOptionPane.showMessageDialog(this, "Signup failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        this.dispose();
        new Colombo().setVisible(true);
    }

    private void goToLogin() {
        new Login().setVisible(true);
        dispose();
    }

    private void togglePasswordVisibility() {
        char echoChar = showPasswordCheck.isSelected() ? (char) 0 : '*';
        passw.setEchoChar(echoChar);
        rpassword.setEchoChar(echoChar);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Signup().setVisible(true));
    }

    private javax.swing.JButton signup, backButton, loginButton;
    private javax.swing.JTextField uname, email;
    private javax.swing.JPasswordField passw, rpassword;
    private javax.swing.JCheckBox showPasswordCheck;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
}