package POS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateCustomer 
{
    JFrame f = new JFrame();
    CreateCustomer()
    {
        f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
            f.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Check if the window was disposed (not just hidden)
                    if (e.getWindow().isDisplayable()) {
                        return; // If it's still displayable, it was just hidden, so don't open SalesUI
                    }
    
                    
                    // make it apear on top
                    MenuUI.instance.toFront();
                    MenuUI.instance.setEnabled(true);
                }
            });
                f.setSize(800,800);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                f.setLayout(new BorderLayout());
                f.setResizable(false);
                f.getContentPane().setBackground(new Color(238, 236, 225));
                f.setTitle("Create Customer");



                JLabel l = new JLabel("Customer Orders");
                l.setHorizontalAlignment(JLabel.CENTER);
                l.setVerticalAlignment(JLabel.CENTER);
                l.setForeground(Color.yellow);
                l.setBackground(Color.black);
                l.setOpaque(true);
                l.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                l.setFocusable(false);
                l.setBorder(MenuUI.border);

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3,2));
                panel.setBackground(new Color(238, 236, 225));
                panel.setBorder(MenuUI.border);
                


                JLabel Name = new JLabel("Name: ");
                Name.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                Name.setHorizontalAlignment(JLabel.CENTER);
                Name.setBackground(new Color(238, 236, 225));
                Name.setBorder(MenuUI.border);
                


                JTextField name = new JTextField();
                name.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                name.setHorizontalAlignment(JTextField.CENTER);
                name.setBackground(new Color(238, 236, 225));
                name.setBorder(MenuUI.border);
                


                JLabel Number = new JLabel("Customer Number: ");
                Number.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                Number.setHorizontalAlignment(JLabel.CENTER);
                Number.setBackground(new Color(238, 236, 225));
                Number.setBorder(MenuUI.border);


                JTextField number = new JTextField();
                number.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                number.setHorizontalAlignment(JTextField.CENTER);
                number.setBackground(new Color(238, 236, 225));
                number.setBorder(MenuUI.border);

                
                JLabel Location = new JLabel("Location: ");
                Location.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                Location.setHorizontalAlignment(JLabel.CENTER);
                Location.setBackground(new Color(238, 236, 225));
                Location.setBorder(MenuUI.border);

                JTextField location = new JTextField();
                location.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                location.setHorizontalAlignment(JTextField.CENTER);
                location.setBackground(new Color(238, 236, 225));
                location.setBorder(MenuUI.border);



                JButton NewCustomer = new JButton("New Customer");
                NewCustomer.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                NewCustomer.setFocusable(false);
                NewCustomer.setForeground(Color.YELLOW);
                NewCustomer.setBackground(Color.BLACK);
                NewCustomer.setBorder(MenuUI.border);
                NewCustomer.addActionListener(e -> 
                {
// ... (previous code for reading from the file)

try (BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.csv", true))) {

    String line;


    
        // Add the new customer data to the file
        String newCustomerLine = name.getText() + "," + number.getText() + "," + location.getText(); // Assuming you have email and phone fields
        writer.write(newCustomerLine);
        writer.newLine();
        System.out.println("New customer added successfully!");
    
        } catch (IOException ex) {
                System.err.println("Error with file operations: " + ex.getMessage());
        }
        f.dispose();
    });


                panel.add(Name);
                panel.add(name);
                panel.add(Number);
                panel.add(number);
                panel.add(Location);
                panel.add(location);

                f.add(l, BorderLayout.NORTH);
                f.add(panel, BorderLayout.CENTER);
                f.add(NewCustomer, BorderLayout.SOUTH);
                

    }
}
