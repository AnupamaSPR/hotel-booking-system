package hotelbooking1;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.sql.*;

public class Home extends JFrame {
    private JWindow blurWindow;
    private JButton loginButton, signupButton, closeButton;
    private JLabel titleLabel;
    private Image backgroundImage;
    private DefaultTableModel tableModel1, tableModel2;
    private JTable table1, table2;

    public Home() {
        // Set FlatLaf modern theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load Background Image
        backgroundImage = new ImageIcon(getClass().getResource("/images/cb.jpg")).getImage();

        setTitle("Sandy Beach Hotel In Colombo ");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom Main Panel with Background Image
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(45, 45, 45));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 100));

        // Title Label
        titleLabel = new JLabel("Sandy Beach Hotel In Colombo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Script MT Bold", Font.BOLD, 36));
        titleLabel.setForeground(new Color(234, 224, 175));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Make content panel transparent

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
        table1.setRowHeight(40);  // Adjust row height for table1
        table2.setRowHeight(40);  // Adjust row height for table2
        table1.setBackground(new Color(255, 250, 213));
        table2.setBackground(new Color(185, 230, 189));
        table2.setForeground(new Color(0, 0, 0));
        table1.setForeground(new Color(0, 0, 0));

        // Set the preferred column widths to prevent columns from stretching too much
        table1.getColumnModel().getColumn(0).setPreferredWidth(120); // Room Type column width
        table1.getColumnModel().getColumn(1).setPreferredWidth(80);  // Price column width
        table1.getColumnModel().getColumn(2).setPreferredWidth(120);  // Price column width

        table2.getColumnModel().getColumn(0).setPreferredWidth(300); // Package column width
        table2.getColumnModel().getColumn(1).setPreferredWidth(300); // Price column width
        // Set scrollable viewport size to make the tables smaller
        table1.setPreferredScrollableViewportSize(new Dimension(250, 80));  // Table1 preferred size
        table2.setPreferredScrollableViewportSize(new Dimension(250, 80));  // Table2 preferred size

        // Set the JTable in JScrollPane
        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);

        // Set the preferred size of the scroll panes to control the overall table size
        scrollPane1.setPreferredSize(new Dimension(250, 150)); // Scroll pane size for table1
        scrollPane2.setPreferredSize(new Dimension(250, 200)); // Scroll pane size for table2


        // Add the scroll panes to the panel
        tablePanel.add(scrollPane1);
        tablePanel.add(scrollPane2);

        // Add the table panel to the background panel
        contentPanel.add(tablePanel, BorderLayout.EAST);



        // Image Panel
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center images & add spacing
        imagePanel.setOpaque(false);

        String[] imagePaths = {"/images/co1.jpg", "/images/co2.jpg", "/images/co3.jpg", "/images/co4.jpeg"};

        for (String path : imagePaths) {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // Resize
                JLabel imageLabel = new JLabel(new ImageIcon(img));
                imagePanel.add(imageLabel); // Add each image to panel
            } else {
                System.err.println("Couldn't find file: " + path);
            }
        }

        contentPanel.add(imagePanel, BorderLayout.WEST);

        // Footer Panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footerPanel.setBackground(new Color(45, 45, 45));
        footerPanel.setPreferredSize(new Dimension(getWidth(), 50));

        // Buttons

        signupButton = new JButton("Make A Reservation");
        closeButton = new JButton("Logout");

        // Modern Styling for Buttons

        styleButton(signupButton, new Color(0, 153, 76));
        styleButton(closeButton, new Color(255, 51, 51));

        // Add buttons to footer

        footerPanel.add(signupButton);
        footerPanel.add(closeButton);

        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Set main panel as content pane
        setContentPane(mainPanel);

        // Button actions

        signupButton.addActionListener(e -> Reservation());
        closeButton.addActionListener(e -> logout());
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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


    private void Reservation() {

        booking bookingFrame = new booking();
        bookingFrame.setVisible(true);
        bookingFrame.setLocationRelativeTo(null);

        bookingFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

            }
        });
    }
    private void logout() {
        this.dispose();
        Colombo ColomboFrame = new Colombo();
        ColomboFrame.setVisible(true);
        ColomboFrame.setLocationRelativeTo(null);

    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home().setVisible(true));
    }
}