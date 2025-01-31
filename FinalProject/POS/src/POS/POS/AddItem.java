package POS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGQTable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class AddItem implements ActionListener, ItemListener
{JFrame f = new JFrame();
    JTextField pp2;
    JTextField pp1;
    JTextField pp;
    String fileName = "Products.txt";
    public static JComboBox<String> nbItem;
    String[] currency = {"$", "€", "LL"};
    JLabel NbItem;
    public static JLabel curr;
    public static List<String> lines = new ArrayList<>();
    AddItem() 
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
        f.setTitle("Add Item");




        //creates the first label
        JLabel p = new JLabel("Product Name: ");
        p.setBounds(50, 300, 1000, 300);
        p.setBackground(Color.black);
        p.setForeground(Color.yellow);
        p.setBorder(MenuUI.border);
        p.setLayout(new GridLayout());
        p.setFont(new Font("Arial",Font.PLAIN,24));
        //creates second label
        JLabel p1 = new JLabel("Product Price: ");
        p1.setBounds(50, 300, 1000, 300);
        p1.setBackground(Color.BLACK);
        p1.setForeground(Color.yellow);
        p1.setBorder(MenuUI.border);
        p1.setLayout(new GridLayout());
        p1.setFont(new Font("Arial",Font.PLAIN,24));
        //creates third label
        JLabel p2 = new JLabel("Product Quantity: ");
        p2.setBounds(50, 300, 1000, 300);
        p2.setBackground(Color.black);
        p2.setForeground(Color.yellow);
        p2.setBorder(MenuUI.border);
        p2.setLayout(new GridLayout());
        p2.setFont(new Font("Arial",Font.PLAIN,24));
        //creates the text fields 
        pp = new JTextField();
        pp.setBounds(50, 300, 1000, 300);
        pp.setBackground(Color.black);
        pp.setForeground(Color.yellow);
        pp.setBorder(MenuUI.border);
        pp.setLayout(new GridLayout());

        //creates the text fields
        pp1 = new JTextField();
        pp1.setBounds(50, 300, 1000, 300);
        pp1.setBackground(Color.black);
        pp1.setForeground(Color.yellow);
        pp1.setBorder(MenuUI.border);
        pp1.setLayout(new GridLayout());
        // Add a key listener to the text field to prevent invalid characters
        pp1.addKeyListener(new KeyAdapter() {
            @Override
            
            public void keyTyped(KeyEvent evt) {
                // Get the character that was typed
                char c = evt.getKeyChar();
                // Get the current text in the text field
                String currentText = pp1.getText();
                
                // Allow only digits, a single decimal point, and backspace
                if (!(Character.isDigit(c) || (c == '.' && !currentText.contains(".")) || c == KeyEvent.VK_BACK_SPACE)) {
                    
                    evt.consume(); // Prevent invalid characters
                }
            }
        });
        
        //creates the combo box
        nbItem = new JComboBox<String>();
        nbItem.setBounds(50, 300, 1000, 300);
        nbItem.setBorder(MenuUI.border);
        nbItem.setPreferredSize(new java.awt.Dimension(1000, 50));
        nbItem.setBackground(Color.black);
        nbItem.setForeground(Color.yellow);
        String[] pro1 = SalesUI.product1.getText().split(" ");
        nbItem.addItem(pro1[0]);
        String[] pro2 = SalesUI.product2.getText().split(" ");
        nbItem.addItem(pro2[0]);
        String[] pro3 = SalesUI.product3.getText().split(" ");
        nbItem.addItem(pro3[0]);
        String[] pro4 = SalesUI.product4.getText().split(" ");

        nbItem.addItem(pro4[0]);
        String[] pro5 = SalesUI.product5.getText().split(" ");

        nbItem.addItem(pro5[0]);
        // Add an item listener to the combo box to update the label
        nbItem.addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e) {
            
            // Update the label when the selected item changes
            if (e.getStateChange() == ItemEvent.SELECTED)    
            {
                NbItem.setText("Change product " + nbItem.getSelectedItem());
            }}
        });
        //creates the text fields
        pp2 = new JTextField();
        pp2.setBounds(50, 300, 1000, 300);
        pp2.setBackground(Color.black);
        pp2.setForeground(Color.yellow);
        pp2.setBorder(MenuUI.border);
        pp2.setLayout(new GridLayout());
        // Add a key listener to the text field to prevent invalid characters
        pp2.addKeyListener(new java.awt.event.KeyAdapter() {
            // Prevent invalid characters
            public void keyTyped(java.awt.event.KeyEvent evt) {
                // Get the character that was typed
                JTextField1KeyTyped(evt);
            }
            
        });
        //creates the button
        JButton ADD = new JButton("ADD");
        ADD.setBounds(50, 300, 1000, 300);
        ADD.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        ADD.setBackground(Color.black);
        ADD.setForeground(Color.yellow);
        ADD.setBorder(MenuUI.border);
        ADD.addActionListener(this);
        ADD.setFocusable(false);
        
        //creates the panels
        JPanel  panel1 = new JPanel();
        panel1.setBounds(50, 300, 1000, 300);
        panel1.setBackground(Color.black);
        panel1.setForeground(Color.yellow);
        panel1.setBorder(MenuUI.border);
        panel1.setLayout(new GridLayout(3,2));
        //creates the label
        NbItem = new JLabel();
        NbItem.setBounds(50, 300, 1000, 300);
        NbItem.setBackground(Color.black);
        NbItem.setForeground(Color.yellow);
        NbItem.setBorder(MenuUI.border);
        NbItem.setLayout(new GridLayout());
        // default value
        NbItem.setText("Change product 1");

        
        //creates the panels
        JPanel  panel2 = new JPanel();
        panel2.setBounds(50, 300, 1000, 300);
        panel2.setBackground(Color.black);
        panel2.setForeground(Color.yellow);
        panel2.setBorder(MenuUI.border);
        panel2.setLayout(new GridLayout(1,2));
        panel2.add(nbItem);
        

        //creates the panels
        JPanel panel3 = new JPanel();
        panel3.setBounds(50, 300, 1000, 300);
        panel3.setBackground(Color.black);
        panel3.setForeground(Color.yellow);
        panel3.setBorder(MenuUI.border);
        panel3.setLayout(new GridLayout(1,2));

        //creates the label
        curr = new JLabel("$");
        curr.setBounds(50, 300, 1000, 300);
        curr.setBackground(Color.black);
        curr.setForeground(Color.yellow);
        curr.setBorder(MenuUI.border);
        curr.setLayout(new GridLayout());
        
        //creates the combo box
        JComboBox <String> currencyBox = new JComboBox<String>(currency);
        currencyBox.setBounds(50, 300, 1000, 300);
        currencyBox.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        currencyBox.setPreferredSize(new java.awt.Dimension(1000, 50));
        currencyBox.setBackground(Color.black);
        currencyBox.setForeground(Color.yellow);
        currencyBox.setBorder(MenuUI.border);
        
        // Add an item listener to the combo box to update the label
        currencyBox.addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED)    
            {
                // Update the label when the selected item changes
                curr.setText(currencyBox.getSelectedItem().toString());
            }}
        });


        //adds the components to the panels
        panel1.add(p);
        panel1.add(pp);
        panel1.add(p1);
        panel1.add(pp1);
        panel1.add(p2);
        panel1.add(pp2);
        
        panel2.add(currencyBox);
        panel2.add(nbItem);
        //adds the panels to the frame
        f.add(panel2,BorderLayout.NORTH);
        f.add(panel1,BorderLayout.CENTER);
        f.add(ADD,BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("ADD"))
        {
            //creates the object
            Products pro = new Products(pp.getText(),Float.parseFloat(pp1.getText()),Integer.parseInt(pp2.getText()));
            //writes to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                //writes the product to the file
                writer.write(pro.getName() + "\n"+ pro.getPrice() + "\n" + pro.getQuantity() + "\n");
                
                
                
                System.out.println("Successfully wrote to the file.");
    
            } catch (IOException ee) {
                System.out.println("An error occurred."); 
            }
            //reads the file
            String line;
            //
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                //reads the file
            while ((line = reader.readLine()) != null) {
                //adds the lines to the list
                lines.add(line); 

            }
            } catch (IOException ee) {
            System.out.println("Error reading file: " + ee.getMessage());
            }
            //switch statement
            switch(nbItem.getSelectedIndex()) {
                //case 0
                case 0:
                    SalesUI.p1 = String.format("%.2f", Float.parseFloat(pp1.getText()));
                    SalesUI.product1.setText(pp.getText() + " "+ SalesUI.p1+ " " + curr.getText());
                    SalesUI.stock[0] = Integer.parseInt(lines.get(2));
                    SalesUI.items[0] = pp.getText();
                    SalesUI.prices[0] = (float) Float.parseFloat(pp1.getText());
                    
                    
                    break;   
                    //case 1         
                case 1:
                    SalesUI.p2 =String.format("%.2f", Float.parseFloat(pp1.getText()));
                    SalesUI.product2.setText(pp.getText() + " "+ SalesUI.p2 + " " + curr.getText());
                    SalesUI.stock[1] = Integer.parseInt(lines.get(2));
                    SalesUI.items[1] = pp.getText();
                    SalesUI.prices[1] = (float) Float.parseFloat(pp1.getText());
                    break;
                    
                case 2:
                    SalesUI.p3 = String.format("%.2f", Float.parseFloat(pp1.getText()));
                    SalesUI.product3.setText(pp.getText() + " "+ SalesUI.p3 + " " + curr.getText());
                    SalesUI.stock[2] = Integer.parseInt(lines.get(2));
                    SalesUI.items[2] = pp.getText();
                    SalesUI.prices[2] = (float) Float.parseFloat(pp1.getText());
                    break;
                case 3:
                    SalesUI.p4 = String.format("%.2f", Float.parseFloat(pp1.getText()));
                    SalesUI.product4.setText(pp.getText()+ " "+ SalesUI.p4+ " " + curr.getText());
                    SalesUI.stock[3] = Integer.parseInt(lines.get(2));
                    SalesUI.items[3] = pp.getText();
                    SalesUI.prices[3] = (float) Float.parseFloat(pp1.getText());
                    break;
                case 4:
                    SalesUI.p5 = String.format("%.2f", Float.parseFloat(pp1.getText()));
                    SalesUI.product5.setText(pp.getText()+ " "+ SalesUI.p5+ " " + curr.getText());
                    SalesUI.stock[4] = Integer.parseInt(lines.get(2));
                    SalesUI.items[4] = pp.getText();
                    SalesUI.prices[4] = (float) Float.parseFloat(pp1.getText());
                    break;
                default:
                    // handle the default case here
                    break;
            }
            


            //closes the frame
            f.dispose();
        }
    }
    //method to prevent invalid characters
    public void JTextField1KeyTyped(java.awt.event.KeyEvent evt) {
        // Get the character that was typed
        char c = evt.getKeyChar();
        // Prevent invalid characters
        if(!(Character.isDigit(c) ))
        {
            // Prevent invalid characters
            evt.consume();
        }
    }
    //method to prevent invalid characters
    public void textFieldKeyTyped(java.awt.event.KeyEvent evt) {
        // Get the character that was typed
        char c = evt.getKeyChar();
        String currentText = ((JTextField) evt.getSource()).getText(); // Get the text from the JTextField
    
        // Allow digits, a single decimal point, and backspace
        if (!(Character.isDigit(c) || (c == '.' && !currentText.contains(".")) || c == KeyEvent.VK_BACK_SPACE)) {
            evt.consume(); // Consume the event to prevent the character from being added
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        
    }
}










