package hotelbooking1;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Dimension;


public class booking extends JFrame {
    private JLabel titleLabel,Totallable,Blank;
    private JComboBox<Integer> SingleBox, DoubleBox, FamilyBox,DaysBox;
    private JComboBox<String> MealBox;
    private JTextField companyField, agentField,  ttextField;
    private JButton submitButton,Confirm,close;
    private JLabel resultLabel;
    private JSpinner checkInDate, checkOutDate;
    Connection c;
    Statement s;
    int totalAmount = 0;
    int roomPrice = 0;


    public booking() {
        try {
            setUndecorated(true);
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Hotel Booking - Create Reservation");
        setSize(500, 600);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(40, 40, 40));

        titleLabel = new JLabel("Bill", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);



        Integer[] Snumbers = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
         SingleBox = new JComboBox<>(Snumbers);
        Integer[] Dnumbers = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
        DoubleBox = new JComboBox<>(Dnumbers);
        Integer[] Fnumbers = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
        FamilyBox = new JComboBox<>(Fnumbers);
        MealBox = new JComboBox<>(new String[]{"Room Only","Bread & Breakfast(B&B)", "Half Board(HB)", "Full Board(FB)", "All  Inclusive(AI)"});
        Integer[] dnumbers = IntStream.rangeClosed(1, 30).boxed().toArray(Integer[]::new);
        DaysBox = new JComboBox<>(dnumbers);

        companyField = new JTextField();
        agentField = new JTextField();

        submitButton = new JButton("Get Total");
        submitButton.setBackground(new Color(102, 0, 204));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> processBooking());


        Confirm = new JButton("Pay");
        Confirm.setBackground(new Color(100, 200, 0));
        Confirm.setForeground(Color.WHITE);
        Confirm.addActionListener(e -> Confirm());

        Totallable = new JLabel("Total" );

        close=new JButton("Close");
        close.setBackground(new Color(255, 0, 10));
        close.setPreferredSize(new Dimension(2, 10));
        close.setForeground(Color.WHITE);
        close.addActionListener(e ->close());

        ttextField = new JTextField("0");
        ttextField.setEditable(false);





        formPanel.add(new JLabel("# Single Rooms:"));
        formPanel.add(SingleBox);
        formPanel.add(new JLabel("# Double Rooms:"));
        formPanel.add(DoubleBox);
        formPanel.add(new JLabel("# Family Rooms:"));
        formPanel.add(FamilyBox);
        formPanel.add(new JLabel("Meal Type:"));
        formPanel.add(MealBox);
        formPanel.add(new JLabel("Company (If Any):"));
        formPanel.add(companyField);
        formPanel.add(new JLabel("Travel Agent(If Any):"));
        formPanel.add(agentField);
        formPanel.add(new JLabel("How many Days:"));
        formPanel.add(DaysBox);
        formPanel.add(submitButton);
        formPanel.add(Totallable);
        formPanel.add(Confirm);
        formPanel.add(ttextField);
        formPanel.add(close);



        add(formPanel, BorderLayout.CENTER);

    }


    private void close() {



            JOptionPane.showMessageDialog(this, "Cancel Reservation? " , "Error", JOptionPane.CANCEL_OPTION);
            this.dispose();

    }

    private void processBooking() {


        int single = (int) SingleBox.getSelectedItem();
        int doubleOccupancy = (int) DoubleBox.getSelectedItem();
        int family = (int) FamilyBox.getSelectedItem();
        String meal = (String) MealBox.getSelectedItem();
        String company = companyField.getText().trim();
        String agent = agentField.getText().trim();
        int days = (int) DaysBox.getSelectedItem();

        // Database connection resources
        Connection c = null;
        Statement s = null;

        try {


            // Establishing DB connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelBooking1", "root", "");
            s = c.createStatement();

            if (meal.equals("Room Only")) {
                // Single query to fetch all room prices
                String sql = "SELECT Price FROM colomrooms WHERE ID IN (1, 2, 3)";
                try (ResultSet rs = s.executeQuery(sql)) {
                    int index = 1;
                    while (rs.next()) {
                        int price = rs.getInt("Price");
                        if (index == 1) {
                            roomPrice += single * price; // Price for single rooms
                        } else if (index == 2) {
                            roomPrice += doubleOccupancy * price; // Price for double rooms
                        } else if (index == 3) {
                            roomPrice += family * price; // Price for family rooms
                        }
                        index++;
                    }

                    // Calculate total price
                    totalAmount = roomPrice * days;
                    ttextField.setText("Rs :" + totalAmount);
                }
            } else {
                // For meal packages
                String sql = "SELECT Price FROM packages WHERE Package='" + meal + "'";
                try (ResultSet rs = s.executeQuery(sql)) {
                    if (rs.next()) {
                        roomPrice = rs.getInt("Price");

                        // Calculate total price based on meal package
                        int roomTypeTotal = single + doubleOccupancy + family;
                        totalAmount = (roomTypeTotal * roomPrice) * days;

                        ttextField.setText(String.valueOf(totalAmount));


                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private void Confirm() {

        if (totalAmount==0){

            JOptionPane.showMessageDialog(this, "Total Cant be Null!", "Error", JOptionPane.ERROR_MESSAGE);
            return;





        }


        String confirm = ttextField.getText().trim();
        int response = JOptionPane.showConfirmDialog(this, "your total bill is Rs: " + confirm + " Comfirm Reservation?", "Select an Option", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {

            Checkout CheckoutFrame = new Checkout();
            CheckoutFrame.setVisible(true);
            CheckoutFrame.setLocationRelativeTo(null);
            this.dispose();
        } else {

            booking booking = new booking();
            booking.setVisible(true);
            booking.setLocationRelativeTo(null);
            this.dispose();


        }


    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new booking().setVisible(true));
    }
}
