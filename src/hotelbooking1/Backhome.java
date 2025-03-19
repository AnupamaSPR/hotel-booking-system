package hotelbooking1;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.sql.*;



public class Backhome extends JFrame {
    private JWindow blurWindow;
    private JButton loginButton, signupButton, closeButton;
    private JLabel titleLabel, subtitleLabel;
    private Image backgroundImage;
    private JTable table1, table2;
    private DefaultTableModel tableModel1, tableModel2;

    public Backhome() {
        // Set FlatLaf modern theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load Background Image
        backgroundImage = new ImageIcon(getClass().getResource("/images/colbg.jpg")).getImage();

        setTitle("Sandy Beach Hotel In Galle");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Background Panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Title Label
        titleLabel = new JLabel("Sandy Beach Hotel In Colombo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Script MT Bold", Font.BOLD, 50));
        titleLabel.setForeground(new Color(216, 203, 180));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);
        tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Using FlowLayout to center tables

        tableModel1 = new DefaultTableModel(new String[]{"Room Type", "Price", "Availability"}, 0);
        tableModel2 = new DefaultTableModel(new String[]{"Package", "Price(Ex Room Price)"}, 0);

        // Tables
        table1 = new JTable(tableModel1);
        table2 = new JTable(tableModel2);

        fetchRoomData();
        fetchFacilityData();

        // Set row height for the tables
        table1.setRowHeight(25);  // Adjust row height for table1
        table2.setRowHeight(25);  // Adjust row height for table2
        table2.setBackground(new Color(185, 230, 189));
        table2.setForeground(new Color(0, 0, 0));

        // Set the preferred column widths to prevent columns from stretching too much
        table1.getColumnModel().getColumn(0).setPreferredWidth(120); // Room Type column width
        table1.getColumnModel().getColumn(1).setPreferredWidth(80);  // Price column width
        table1.getColumnModel().getColumn(2).setPreferredWidth(120);  // Price column width

        table2.getColumnModel().getColumn(0).setPreferredWidth(120); // Facility column width
        table2.getColumnModel().getColumn(1).setPreferredWidth(100); // Availability column width

        // Set scrollable viewport size to make the tables smaller
        table1.setPreferredScrollableViewportSize(new Dimension(250, 80));  // Table1 preferred size
        table2.setPreferredScrollableViewportSize(new Dimension(250, 80));  // Table2 preferred size

        // Set the JTable in JScrollPane
        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);

        // Set the preferred size of the scroll panes to control the overall table size
        scrollPane1.setPreferredSize(new Dimension(250, 100)); // Scroll pane size for table1
        scrollPane2.setPreferredSize(new Dimension(250, 100)); // Scroll pane size for table2

        // Add the scroll panes to the panel
        tablePanel.add(scrollPane1);
        tablePanel.add(scrollPane2);

        // Add the table panel to the background panel
        backgroundPanel.add(tablePanel, BorderLayout.EAST);

        // Image Panel (4 images above button panel)
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center images & add spacing
        imagePanel.setOpaque(false);

        String[] imagePaths = {"/images/co1.jpeg", "/images/co1.jpg", "/images/co2.jpg", "/images/co4.jpeg"};

        for (String path : imagePaths) {
            ImageIcon icon = new ImageIcon(getClass().getResource(path));
            Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // Resize
            JLabel imageLabel = new JLabel(new ImageIcon(img));

            imagePanel.add(imageLabel); // Add each image to panel
        }

        backgroundPanel.add(imagePanel, BorderLayout.WEST);
        backgroundPanel.revalidate();
        backgroundPanel.repaint();



        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setOpaque(false);

        // Create a subtitle label above the buttons
        JLabel buttonSubtitle = new JLabel("Login or signup to reserve ", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Font font = getFont();
                g2.setFont(font);

                // Create a stroke for the text
                g2.setStroke(new BasicStroke(2));
                g2.setColor(Color.BLACK);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

                // Draw the outline
                g2.drawString(getText(), x, y);

                // Draw the text
                g2.setColor(getForeground());
                g2.drawString(getText(), x, y);
            }
        };
        buttonSubtitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
        buttonSubtitle.setForeground(Color.BLACK);
        buttonSubtitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add vertical spacing

        // Add the subtitle label above the button panel
        buttonPanel.add(buttonSubtitle, BorderLayout.NORTH);

        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        closeButton = new JButton("Back");

        // Modern Styling for Buttons
        styleButton(loginButton, new Color(102, 0, 204));
        styleButton(signupButton, new Color(0, 153, 76));
        styleButton(closeButton, new Color(255, 51, 51));

        // Action Listeners with Blur Effect
        loginButton.addActionListener(e -> openLogin());
        signupButton.addActionListener(e -> openSignup());
        closeButton.addActionListener(e -> Back());

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setOpaque(false);
        buttons.add(loginButton);
        buttons.add(signupButton);
        buttons.add(closeButton);

        buttonPanel.add(buttons, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(backgroundPanel);


    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 22));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    private void fetchRoomData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelBooking1", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select*from colomrooms")) {

            while (rs.next()) {
                String roomType = rs.getString("Room_typ");
                String price = "Rs" + rs.getDouble("price");
                String Availability = rs.getString("avlbl");
                tableModel1.addRow(new Object[]{roomType, price,Availability});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchFacilityData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelBooking1", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select*from packages")) {

            while (rs.next()) {
                String Package = rs.getString("Package");
                String Price = "Rs" + rs.getDouble("Price");

                tableModel2.addRow(new Object[]{Package, Price});


            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBlurEffect() {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(getLocationOnScreen(), getSize());
            BufferedImage capture = robot.createScreenCapture(screenRect);

            // Apply Gaussian Blur
            BufferedImage blurredImage = applyGaussianBlur(capture);

            // Create an overlay window
            blurWindow = new JWindow();
            JLabel label = new JLabel(new ImageIcon(blurredImage));
            blurWindow.setContentPane(label);
            blurWindow.setSize(getSize());
            blurWindow.setLocation(getLocation());
            blurWindow.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void hideBlurEffect() {
        if (blurWindow != null) {
            blurWindow.dispose();
            blurWindow = null;
        }
    }

    private BufferedImage applyGaussianBlur(BufferedImage image) {
        float[] blurMatrix = {
                1f / 256f, 4f / 256f, 6f / 256f, 4f / 256f, 1f / 256f,
                4f / 256f, 16f / 256f, 24f / 256f, 16f / 256f, 4f / 256f,
                6f / 256f, 24f / 256f, 36f / 256f, 24f / 256f, 6f / 256f,
                4f / 256f, 16f / 256f, 24f / 256f, 16f / 256f, 4f / 256f,
                1f / 256f, 4f / 256f, 6f / 256f, 4f / 256f, 1f / 256f
        };
        Kernel kernel = new Kernel(3, 3, blurMatrix);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return op.filter(image, null);
    }

    private void openLogin() {

        Login loginFrame = new Login();
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);

        loginFrame.addWindowListener(new java.awt.event.WindowAdapter() {

        });
    }

    private void openSignup() {

        Signup signupFrame = new Signup();
        signupFrame.setVisible(true);
        signupFrame.setLocationRelativeTo(null);

        signupFrame.addWindowListener(new java.awt.event.WindowAdapter() {


        });
    }

    private void Back() {
        this.dispose();
        Menu MenuFrame = new Menu();
        MenuFrame.setVisible(true);
        MenuFrame.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Colombo().setVisible(true));
    }
}
