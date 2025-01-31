package POS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment {
   //this class is to open when the user is ready to pay for their items
    public static double TodayTotal = 0;
    public static double CashTotalToday = 0;
    public static double VisaTotalToday = 0;
    public static double CryptoTotalToday = 0;
    JButton cash = new JButton("Cash");
    JButton visa = new JButton("Visa");
    JButton crypto = new JButton("Crypto");
    String PaymentTime;
    String fileName = "Payment.txt";
    public Payment()
   {

         //this is the payment screen
         JFrame f = new JFrame();
        f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            // Check if the window was disposed (not just hidden)
            if (e.getWindow().isDisplayable()) {
                return; // If it's still displayable, it was just hidden, so don't open SalesUI
            }

                
                // make it apear on top
                SalesUI.f.toFront();
                SalesUI.f.setEnabled(true);
            }
        });
        //creates the frame
        f.setSize(1000,700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setLayout(new  BorderLayout());
        f.setResizable(false);
        f.getContentPane().setBackground(Color.black);
        f.setTitle("Payment");   


        
        //creates the panel
        JPanel p = new JPanel();
        p.setBackground(Color.black);
        p.setForeground(Color.YELLOW);
        p.setLayout(new GridLayout(4,0));
        p.setBorder(MenuUI.border);

        double ThisTotal = Double.parseDouble(String.format("%.2f", SalesUI.totall));
        //creates the payment options
        cash.setBackground(Color.black);
        cash.setForeground(Color.YELLOW);
        cash.setBorder(MenuUI.border);
        cash.setFocusable(false);

        visa.setBackground(Color.black);
        visa.setForeground(Color.YELLOW);
        visa.setBorder(MenuUI.border);
        visa.setFocusable(false);

        crypto.setBackground(Color.black);
        crypto.setForeground(Color.YELLOW);
        crypto.setBorder(MenuUI.border);
        crypto.setFocusable(false);

        JLabel total = new JLabel("Total: " + ThisTotal + "$");
        total.setFont(new java.awt.Font("Cosmic Sans", 1, 25));
        total.setForeground(Color.YELLOW);
        total.setBackground(Color.black);
        total.setOpaque(true);
        total.setHorizontalAlignment(JLabel.CENTER);
        total.setBorder(MenuUI.border);
        total.setFocusable(false);

        

        TimeDisplay TimeDisplay = new TimeDisplay();
        TimeDisplay.setFont(new java.awt.Font("Cosmic Sans", 1, 25));
        TimeDisplay.setForeground(Color.YELLOW);
        TimeDisplay.setBackground(Color.black);
        TimeDisplay.setOpaque(true);
        TimeDisplay.setHorizontalAlignment(JLabel.CENTER);
        TimeDisplay.setBorder(MenuUI.border);
        TimeDisplay.setFocusable(false);

        




        cash.addActionListener(e -> {
            f.dispose();
            TodayTotal += ThisTotal;
            SalesUI.totall = 0;
            SalesUI.total.setText("Total: " + SalesUI.totall + "$");
            SalesUI.model.setRowCount(0);
            CashTotalToday += ThisTotal;
            String PaymentTime = TimeDisplay.getFormattedDateTime();
            


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
                writer.write("Cash Payment: " + ThisTotal + "$ at " + PaymentTime +" by "+ MenuUI.User.getText() + "\n");
                
                
                
                System.out.println("Successfully wrote to the file.");
                
    
            } catch (IOException ee) {
                System.out.println("An error occurred."); 
            }



        });
        crypto.addActionListener(e -> {
            f.dispose();
            TodayTotal += ThisTotal;
            SalesUI.totall = 0;
            SalesUI.total.setText("Total: " + SalesUI.totall + "$");
            SalesUI.model.setRowCount(0);
            CryptoTotalToday += ThisTotal;
            PaymentTime = TimeDisplay.getFormattedDateTime();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
                writer.write("Crypto Payment: " + ThisTotal + "$ at " + PaymentTime + " by "+ MenuUI.User.getText() +"\n");
                
                
                
                System.out.println("Successfully wrote to the file.");
                
    
            } catch (IOException ee) {
                System.out.println("An error occurred."); 
            }

        });

        visa.addActionListener(e -> {
            f.dispose();
            TodayTotal += ThisTotal;
            SalesUI.totall = 0;
            SalesUI.total.setText("Total: " + SalesUI.totall + "$");
            SalesUI.model.setRowCount(0);
            VisaTotalToday += ThisTotal;
            PaymentTime = TimeDisplay.getFormattedDateTime();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
                writer.write("Visa Payment: " + ThisTotal + "$ at " + PaymentTime +" by "+ MenuUI.User.getText() + "\n");
                
                
                
                System.out.println("Successfully wrote to the file.");
                
    
            } catch (IOException ee) {
                System.out.println("An error occurred."); 
            }
        });

























        p.add(cash);
        p.add(visa);
        p.add(crypto);
        f.add(total, BorderLayout.NORTH);
        f.add(p, BorderLayout.CENTER);
        f.add(TimeDisplay, BorderLayout.SOUTH);





























   }
   public static String datetime()
   {
        

         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
         LocalDateTime now = LocalDateTime.now();
         return dtf.format(now);

}

}


   
   


