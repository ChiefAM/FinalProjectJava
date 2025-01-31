package POS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CustomerOrder 
{
    JComboBox<String> customerList = new JComboBox<String>();
    List<List<String>> data = new ArrayList<>();
    JLabel quantity;
    JTextField quantityField;
    CustomerOrder()
    {

        JFrame f = new JFrame("Customer Order");
        f.setSize(800,800);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setLayout(new BorderLayout());
        f.setResizable(false);
        f.getContentPane().setBackground(new Color(238, 236, 225));
        f.setTitle("Customer Order");



        
        JLabel l = new JLabel("Select Customer");
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.CENTER);
        l.setForeground(Color.yellow);
        l.setBackground(Color.black);
        l.setOpaque(true);
        l.setFont(new java.awt.Font("Cosmic Sans",java.awt.Font.BOLD, 25));
        l.setFocusable(false);
        l.setBorder(MenuUI.border);

        JPanel  panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(1,1));
        panel.setBackground(new Color(238, 236, 225));
        panel.setBorder(MenuUI.border);


        JPanel panel2 = new JPanel();
        panel2.setLayout(new java.awt.GridLayout(2,0));
        panel2.setBackground(new Color(238, 236, 225));
        panel2.setBorder(MenuUI.border);


        JLabel product = new JLabel("Select Product");
        product.setHorizontalAlignment(JLabel.CENTER);
        product.setVerticalAlignment(JLabel.CENTER);
        product.setForeground(Color.yellow);
        product.setBackground(Color.black);
        product.setOpaque(true);
        product.setFont(new java.awt.Font("Cosmic Sans",java.awt.Font.BOLD, 25));
        product.setFocusable(false);
        product.setBorder(MenuUI.border);


        JComboBox<String> productList = new JComboBox<String>();
        productList.addItem(SalesUI.items[0]);
        productList.addItem(SalesUI.items[1]);
        productList.addItem(SalesUI.items[2]);
        productList.addItem(SalesUI.items[3]);
        productList.addItem(SalesUI.items[4]);


        JButton add = new JButton("Add");
        add.setBounds(50, 50, 400, 50);
        add.setFocusable(false);
        add.setFont(new java.awt.Font("Cosmic Sans",java.awt.Font.BOLD, 25));
        add.setForeground(Color.yellow);
        add.setBackground(Color.black);
        add.setBorder(MenuUI.border);
        add.addActionListener(e -> {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Orders.csv", true))) {

            if (quantityField.getText().isEmpty() || customerList.getSelectedItem().toString().isEmpty() || productList.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty values please fill them", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


        
        // Add the new customer data to the file
        String order =customerList.getSelectedItem() + "," + productList.getSelectedItem() + "," + quantityField.getText() + "\n";
        writer.write(order);
        writer.newLine();
        System.out.println("Order added: " + order);
    
        } catch (IOException ex) {
                System.err.println("Error with file operations: " + ex.getMessage());
        }
        f.dispose();


        // check if no entery in all of the field so show the error message



    });


        JButton backToMenu = new JButton("Back to Menu");
        backToMenu.setBounds(50, 50, 400, 50);
        backToMenu.setFocusable(false);
        backToMenu.setFont(new java.awt.Font("Cosmic Sans",java.awt.Font.BOLD, 25));
        backToMenu.setForeground(Color.yellow);
        backToMenu.setBackground(Color.black);
        backToMenu.setBorder(MenuUI.border);
        backToMenu.addActionListener(e -> {
            f.dispose();
        });

        quantity = new JLabel("Quantity : ");
        quantity.setHorizontalAlignment(JLabel.CENTER);
        quantity.setVerticalAlignment(JLabel.CENTER);
        quantity.setForeground(Color.yellow);
        quantity.setBackground(Color.black);
        quantity.setOpaque(true);
        quantity.setFont(new java.awt.Font("Cosmic Sans",java.awt.Font.BOLD, 25));
        quantity.setFocusable(false);
        quantity.setBorder(MenuUI.border);


        quantityField = new JTextField();
        quantityField.setFont(new java.awt.Font("Cosmic Sans",java.awt.Font.BOLD, 25));
        quantityField.setHorizontalAlignment(JTextField.CENTER);
        quantityField.setBackground(new Color(238, 236, 225));
        quantityField.setBorder(MenuUI.border);
        quantityField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        
        


        //read from fie Customers.csv and display name of customer
        try (BufferedReader reader = new BufferedReader(new FileReader("Customers.csv"))) {
            String line;
            
        
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                List<String> row = Arrays.asList(parts);
                data.add(row);
            }
        
            // Move this outside the reading loop
            for (List<String> dat : data) {
                customerList.addItem(dat.get(0)); 
            }
        
        } catch (IOException e) { // Use a more descriptive variable name
            System.err.println("Error reading file: " + e.getMessage()); // Print to stderr for error messages
        }
        
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new java.awt.GridLayout(1,0));
        panel3.setBackground(new Color(238, 236, 225));
        panel3.setBorder(MenuUI.border);




        f.add(panel, BorderLayout.NORTH);
        f.add(panel2, BorderLayout.CENTER);
        f.add(panel3, BorderLayout.SOUTH);
        panel3.add(backToMenu);
        panel3.add(add);
        panel.add(l);
        panel.add(customerList);
        panel2.add(product);
        panel2.add(productList);
        panel2.add(quantity);
        panel2.add(quantityField);

            
}

    }



