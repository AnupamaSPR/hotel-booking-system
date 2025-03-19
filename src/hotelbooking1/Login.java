package hotelbooking1;

import com.formdev.flatlaf.FlatDarkLaf;
import java.sql.*;
import javax.swing.*;

public class Login extends javax.swing.JFrame {
    private Connection c;
    private PreparedStatement pst;
    private javax.swing.JButton jButton1, jButton2;
    private javax.swing.JCheckBox ck;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField pa;
    private javax.swing.JTextField un;
    public Login() {
        initComponents();
        setLocationRelativeTo(null); // Center window
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        un = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pa = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ck = new javax.swing.JCheckBox();


        setTitle("Login");
        setResizable(false);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(50, 50, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36));
        jLabel1.setText("Login");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel2.setText("Username:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel4.setText("Password:");

        jButton1.setText("Login");
        jButton1.addActionListener(evt -> loginAction());

        jButton2.setText("Go back");
        jButton2.addActionListener(evt -> goBack());

        ck.setText("Show Password");
        ck.addActionListener(evt -> togglePassword());

        // Layout Setup
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setAutoCreateGaps(true);
        jPanel1Layout.setAutoCreateContainerGaps(true);

        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addComponent(un, 200, 200, 200))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addComponent(pa, 200, 200, 200))
                        .addComponent(ck)
                        .addComponent(jButton1, 100, 100, 100)
                        .addComponent(jButton2, 100, 100, 100)
        );

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(jLabel2)
                                .addComponent(un, 30, 30, 30))
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(jLabel4)
                                .addComponent(pa, 30, 30, 30))
                        .addComponent(ck)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
        );

        add(jPanel1);
        pack();
    }

    private void loginAction() {
        String username = un.getText().trim();
        String password = new String(pa.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (username.equals("Admin") && password.equals("555")) {
            new Administrator().setVisible(true);
            dispose();
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelBooking1", "root", "");
            String query = "SELECT * FROM signup WHERE Username=? AND Password=?";
            pst = c.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               this. dispose();
               new Home().setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Connection Error", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { if (pst != null) pst.close(); if (c != null) c.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    private void goBack() {
        dispose();
        new Colombo().setVisible(true);


    }

    private void togglePassword() {
        pa.setEchoChar(ck.isSelected() ? (char) 0 : '*');
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }


}
