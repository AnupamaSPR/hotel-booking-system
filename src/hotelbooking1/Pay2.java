package hotelbooking1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pay2 extends JFrame {
    private JTextField cardNumberField, cardHolderField;
    private JFormattedTextField expiryMonthField, expiryYearField;
    private JPasswordField cvvField;
    private JButton payButton,close;

    public Pay2() {
        setTitle("Payment2");
        setSize(400, 300);
        setUndecorated(true);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Add Card", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField(16);

        JLabel cardHolderLabel = new JLabel("Card Holder Name:");
        cardHolderField = new JTextField();

        JLabel expiryLabel = new JLabel("Expiry Date (MM/YY):");
        expiryMonthField = new JFormattedTextField();
        expiryMonthField.setColumns(2);
        expiryYearField = new JFormattedTextField();
        expiryYearField.setColumns(2);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvField = new JPasswordField(3);

        close=new JButton("Cancel");
        close.setBackground(new Color(255, 0, 10));
        close.setPreferredSize(new Dimension(10, 10));
        close.setForeground(Color.WHITE);
        close.addActionListener(e ->close());

        payButton = new JButton("Confirm and Pay");
        payButton.setBackground(new Color(50, 205, 50));
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("Arial", Font.BOLD, 14));
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment();
            }




        });

        panel.add(cardNumberLabel); panel.add(cardNumberField);
        panel.add(cardHolderLabel); panel.add(cardHolderField);
        panel.add(expiryLabel);
        JPanel expiryPanel = new JPanel();
        expiryPanel.add(expiryMonthField);
        expiryPanel.add(new JLabel("/"));
        expiryPanel.add(expiryYearField);
        panel.add(expiryPanel);

        panel.add(cvvLabel); panel.add(cvvField);
        panel.add(new JLabel()); panel.add(payButton);

        panel.add(close);

        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void close() {


        JOptionPane.showMessageDialog(this, "Cancel Reservation? " , "Error", JOptionPane.CANCEL_OPTION);
        this.dispose();
    }



    private void processPayment() {
        String cardNumber = cardNumberField.getText().trim();
        String cardHolder = cardHolderField.getText().trim();
        String month = expiryMonthField.getText().trim();
        String year = expiryYearField.getText().trim();
        String cvv = new String(cvvField.getPassword()).trim();

        if (cardNumber.isEmpty() || cardHolder.isEmpty() || month.isEmpty() || year.isEmpty() || cvv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cardNumber.matches("\\d{16}")) {
            JOptionPane.showMessageDialog(this, "Invalid Card Number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!month.matches("0[1-9]|1[0-2]")) {
            JOptionPane.showMessageDialog(this, "Invalid Expiry Month!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!year.matches("\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Invalid Expiry Year!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cvv.matches("\\d{3}")) {
            JOptionPane.showMessageDialog(this, "Invalid CVV!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Payment Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);


        JOptionPane.showMessageDialog(this, "As a Confirmation of Your Reservation Please Be Kind Enough To Bring Your NIC When You Arrive.   Thank You!", "Success", JOptionPane.INFORMATION_MESSAGE);
        new Home2().setVisible(true);
        this.dispose();




    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Pay2().setVisible(true));
    }
}
