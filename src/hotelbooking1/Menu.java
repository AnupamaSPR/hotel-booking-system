package hotelbooking1;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Menu extends JFrame {
    private JButton colomboButton, galleButton, backButton;
    private JLabel titleLabel;
    private JLabel colomboBackground, galleBackground;

    public Menu() {
        // Set modern FlatLaf theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Sandy Beach Hotel - Menu");
        setTitle("Sandy Beach Hotel");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title Label
        titleLabel = new JLabel("Welcome to Sandy Beach Hotel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(new Color(216, 203, 180));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(13, 26, 58)); // Set background color

        add(titleLabel, BorderLayout.NORTH);

        // Background Images
        colomboBackground = new JLabel(new ImageIcon(getClass().getResource("/images/Colombo.jpg")));
        galleBackground = new JLabel(new ImageIcon(getClass().getResource("/images/Galle.jpg")));

        // Labels for Colombo and Galle Panels
        JLabel colomboLabel = new JLabel("Click to View Colombo Branch", SwingConstants.CENTER);
        colomboLabel.setFont(new Font("sen serif", Font.BOLD, 20));
        colomboLabel.setBackground(new Color(13, 26, 58));
        colomboLabel.setOpaque(true);
        colomboLabel.setBackground(new Color(13, 26, 58)); // Set background color
        colomboLabel.setForeground(Color.WHITE);


        JLabel galleLabel = new JLabel("Click to View Galle Branch", SwingConstants.CENTER);
        galleLabel.setFont(new Font("sen serif", Font.BOLD, 20));
        galleLabel.setBackground(new Color(13, 26, 58));
        galleLabel.setOpaque(true);
        galleLabel.setForeground(Color.WHITE);

        // Separate Panels
        JPanel colomboPanel = new JPanel(new BorderLayout());
        colomboPanel.setBackground(new Color(13, 26, 58));

        JPanel gallePanel = new JPanel(new BorderLayout());
        gallePanel.setBackground(new Color(13, 26, 58));

        // Button Panels
        JPanel colomboButtonPanel = new JPanel(new BorderLayout());
        colomboButtonPanel.setBackground(new Color(30, 30, 30));
        colomboButton = new JButton("Colombo");
        styleButton(colomboButton, new Color(102, 0, 204));

        JPanel galleButtonPanel = new JPanel(new BorderLayout());
        galleButtonPanel.setBackground(new Color(30, 30, 30));
        galleButton = new JButton("Galle");
        styleButton(galleButton, new Color(0, 153, 76));

        colomboButtonPanel.add(colomboLabel, BorderLayout.NORTH);
        colomboButtonPanel.add(colomboButton, BorderLayout.CENTER);

        galleButtonPanel.add(galleLabel, BorderLayout.NORTH);
        galleButtonPanel.add(galleButton, BorderLayout.CENTER);

        colomboPanel.add(colomboButtonPanel, BorderLayout.NORTH);
        colomboPanel.add(colomboBackground, BorderLayout.CENTER);

        gallePanel.add(galleButtonPanel, BorderLayout.NORTH);
        gallePanel.add(galleBackground, BorderLayout.CENTER);

        // Add Mouse Listeners to Background Labels
        colomboBackground.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openColombo();
            }
        });

        galleBackground.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openGalle();
            }
        });

        // Back Button Panel
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setBackground(new Color(30, 30, 30));
        backButton = new JButton("Close");
        styleBackButton(backButton, new Color(255, 51, 51));
        backButtonPanel.add(backButton);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 40, 40));
        mainPanel.setBackground(new Color(13, 26, 58));
        mainPanel.setPreferredSize(new Dimension(80, 40));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(colomboPanel);
        mainPanel.add(gallePanel);

        add(mainPanel, BorderLayout.CENTER);
        add(backButtonPanel, BorderLayout.SOUTH);


        colomboButton.addActionListener(e -> openColombo());
        galleButton.addActionListener(e -> openGalle());
        backButton.addActionListener(e -> closeAllWindows());


    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Adjust padding if needed
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 50)); // Reduced width
    }

    private void styleBackButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14)); // Adjust padding if needed
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(80, 40)); // Further reduced width
    }

    private void openColombo() {
        new Colombo().setVisible(true);

    }

    public interface CloseEventListener {
        void handleCloseEvent(CloseEvent event);
    }

    public class CloseEvent extends java.util.EventObject {
        public CloseEvent(Object source) {
            super(source);
        }
    }

    private void openGalle() {
        new Galle().setVisible(true);

    }

    private void closeAllWindows() {
        for (Window window : Window.getWindows()) {
            window.dispose();
        }
    }

    private void goBack() {

        this.dispose();
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
    }
}