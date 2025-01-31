package POS;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.EventObject;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;


/**
 *
 * @author antho
 */
public class SalesUI implements ActionListener
{
        //variables
        int ii = 0;
        public static float totall = 0.00f;
        DecimalFormat df = new DecimalFormat("#.##"); 
        public static JLabel total = new JLabel("Total: ");
        public static JTable table = new JTable();
        public static String p1 = "";
        public static String p2 = "";
        public static String p3 = "";
        public static String p4 = "";
        public static String p5 = "";
        public static String[] Currency = {"USD", "EUR", "LBP"};
        public static JFrame f = new JFrame();
        public static JButton product1; 
        public static JButton product2;
        public static JButton product3;
        public static JButton product4;
        public static JButton product5;
        public static int[] stock = new int[5];
        public static DefaultTableModel model;
        public static JTextField textField = new JTextField();
        public static String[] items = new String[5];
        public static float[] prices = new float[5];
        public static int quantity = 1;
        JButton AddItems;
        Vector<Object> rowData = new Vector<>();
        String[] columnNames = {"Item Name", "Price", "Quantity", "Total price"};
        JButton payment;
        JButton backToMenuButton;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
    public SalesUI() 
    {
        

        //creates the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame. MAXIMIZED_BOTH);
        f.setVisible(true);
        f.setLayout(new BorderLayout());
        f.getContentPane().setBackground(Color.black);
        f.setTitle("Sales");


        //username label
        JLabel Username = new JLabel("Username:");
        Username.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        Username.setHorizontalAlignment(JLabel.CENTER);
        Username.setOpaque(true);
        Username.setForeground(Color.YELLOW);
        Username.setBackground(Color.black);
        Username.setBorder(MenuUI.border);
        Username.setText(MenuUI.User.getText());
        
        //creates the panel for the table
        JPanel SalesPanel = new JPanel();
        SalesPanel.setBackground(Color.BLACK);
        SalesPanel.setForeground(Color.YELLOW);
        SalesPanel.setLayout(new BorderLayout());
        SalesPanel.setBorder(MenuUI.border);
        //creates the model of the table
        model = new DefaultTableModel(columnNames, 0);
        // Add a listener to the table model to update the total when the quantity changes
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                
                //total = total price + each other 
                totall = 0.00f;
                for (int i = 0; i < model.getRowCount(); i++) {

                    
                    
                    totall += Double.parseDouble(String.format("%.2f", model.getValueAt(i, 3)));
                } 
                total.setText("Total: " + String.format("%.2f", totall) + "$");
            }
        });

        //remove items from table using delete
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DELETE){
                    int row = table.getSelectedRow();
                    if(row != -1){
                        model.removeRow(row);
                    }
                }
            }
        });


        //creates the table
        table.setModel(model);
        table.setRowHeight(30);
        table.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        table.setBackground(Color.BLACK); // Set background color
        table.setForeground(Color.YELLOW); // Set foreground color
        table.setBorder(MenuUI.border);
        table.setGridColor(Color.YELLOW); // Set grid color
        
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(Color.YELLOW); // Set grid color
        table.setShowGrid(true);
        //creates the scroll pane
        JScrollPane sp = new JScrollPane(table);
        sp.setBackground(Color.BLACK);
        sp.setForeground(Color.YELLOW);
        sp.setBorder(MenuUI.border);
        sp.getViewport().setBackground(Color.BLACK);
        sp.getViewport().setForeground(Color.YELLOW);
        
        

        // Set the table header font
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(Color.BLACK);
        headerRenderer.setForeground(Color.YELLOW);
        // Set the table header font
        headerRenderer.setFont(new Font("Cosmic Sans", Font.BOLD, 25));
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        SalesPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        table.getTableHeader().setBorder(MenuUI.border);

        //change the vertical lines color of the ttable
        table.setGridColor(Color.YELLOW);
        



        // Disable editing for all columns by default
        table.setDefaultEditor(Object.class, null);

        // Enable only the Quantity column        
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(textField));
        

        // Create a spinner for the Quantity column
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinner.setFont(new Font("Cosmic Sans", Font.BOLD, 25));
        spinner.setBorder(MenuUI.border);
        spinner.addChangeListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.setValueAt(spinner.getValue(), row, 2);
            }
        });

                        

        // Add a listener to the table model to update the total when the quantity changes for total price 
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Check if the change is an UPDATE and it's in the quantity column (index 2)
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 2) {
                    int row = e.getFirstRow(); // Use the row from the event
        
                    int oo = Integer.parseInt(model.getValueAt(row, 2).toString());
        
                    // Get the price (assuming it's a Float)
                    float price = Float.parseFloat(model.getValueAt(row, 1).toString());
        
                    float tt = oo * price;
                    model.setValueAt(tt, row, 3);
                }
            }
        });
 
    
        //the quantity can only take numbers
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });



        
        
        //creates the panel for the botom
        JPanel bot = new JPanel();
        bot.setBackground(Color.black);
        bot.setForeground(Color.YELLOW);
        bot.setLayout(new GridLayout(4,1));
        bot.setBorder(MenuUI.border);

        

        //creates the payment button
        payment = new JButton("Payment");
        payment.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        payment.setBackground(Color.black);
        payment.setForeground(Color.yellow);
        payment.setBorder(MenuUI.border);
        payment.addActionListener(this);


        

        //creates the total label
        total.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        total.setBackground(Color.black);
        total.setForeground(Color.yellow);
        total.setBorder(MenuUI.border);
        total.setHorizontalAlignment(JLabel.CENTER);
        total.setText("Total: " + totall);
        
        //creates the back to menu button
        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        backToMenuButton.setBackground(Color.black);
        backToMenuButton.setForeground(Color.yellow);
        backToMenuButton.setBorder(MenuUI.border);
        backToMenuButton.addActionListener(this);
        backToMenuButton.setFocusable(false);

        //creates the add items button
        AddItems = new JButton("Add Item");
        AddItems.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        AddItems.setBackground(Color.black);
        AddItems.setForeground(Color.yellow);
        AddItems.setBorder(MenuUI.border);
        AddItems.addActionListener(this);

        //creates the panel for the items
        JPanel ItemJPanel = new JPanel();
        ItemJPanel.setBackground(Color.black);
        ItemJPanel.setForeground(Color.YELLOW);
        ItemJPanel.setLayout(new GridLayout(10,1));
        ItemJPanel.setBorder(MenuUI.border);

        //creates the products
        product1 = new JButton("Product1 " + p1);
        product1.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        product1.setBackground(Color.black);
        product1.setForeground(Color.yellow);
        product1.setBorder(MenuUI.border);
        product1.addActionListener(this);
        product1.setFocusable(false);

        product2 = new JButton("Product2 "+ p2);
        product2.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        product2.setBackground(Color.black);
        product2.setForeground(Color.yellow);
        product2.setBorder(MenuUI.border);
        product2.addActionListener(this);
        product2.setFocusable(false);

        product3 = new JButton("Product3 "+ p3 );
        product3.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        product3.setBackground(Color.black);
        product3.setForeground(Color.yellow);
        product3.setBorder(MenuUI.border);
        product3.addActionListener(this);
        product3.setFocusable(false);

        product4 = new JButton("Product4 "+p4);
        product4.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        product4.setBackground(Color.black);
        product4.setForeground(Color.yellow);
        product4.setBorder(MenuUI.border);
        product4.addActionListener(this);
        product4.setFocusable(false);

        product5 = new JButton("Product5 " + p5);
        product5.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        product5.setBackground(Color.black);
        product5.setForeground(Color.yellow);
        product5.setBorder(MenuUI.border);
        product5.addActionListener(this);
        product5.setFocusable(false);



        JButton saveSale = new JButton("Save Sale");
        saveSale.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        saveSale.setBackground(Color.black);
        saveSale.setForeground(Color.yellow);
        saveSale.setBorder(MenuUI.border);
        saveSale.addActionListener(e -> {
            if(model.getRowCount() == 0)
            {
                JOptionPane.showMessageDialog(null, "No items in the cart");
                return;

            }
            //creates the save sale frame
            new SaveSale();
            //cannot access the frame
            
        });
// on hold
/* 
        JButton savedSale = new JButton("Saved Sale");
        savedSale.setFont(new Font("Cosmic Sans", Font.BOLD, 25));
        savedSale.setBackground(Color.black);
        savedSale.setForeground(Color.yellow);
        savedSale.setBorder(MenuUI.border);
        
        savedSale.addActionListener(e -> {
            
                model.setRowCount(0);
        
                try (BufferedReader reader = new BufferedReader(new FileReader("Sales.csv"))) {
                    String line = reader.readLine(); // Read the first line
        
                    if (line != null) { 
                        // CSV is not empty, process rows
                        do {
                           
                            String[] rowData = line.split(",");
                             model.addRow(rowData); 
                            
                        } while ((line = reader.readLine()) != null); 
                    } else {
                        // CSV is empty, display "No saved sales"
                             {
                            JOptionPane.showMessageDialog(null, "No saved sales");
                        
                            }
                    }
                }
                 catch (FileNotFoundException ee) {
                    ee.printStackTrace();
                }
                 catch (IOException ee) {
                    ee.printStackTrace();
                }});
            
*/

            


                            
                        
        
        


        //adds the components to the frame
        f.add(ItemJPanel, BorderLayout.WEST);
        //adds the products to the item panel
        ItemJPanel.add(product1);
        ItemJPanel.add(product2);
        ItemJPanel.add(product3);
        ItemJPanel.add(product4);
        ItemJPanel.add(product5);
        //adds the components to the frame
        bot.add(payment);
        bot.add(AddItems);
        bot.add(saveSale);
        //bot.add(savedSale);
        bot.add(backToMenuButton);
        //adds the components to the frame
        f.add(SalesPanel, BorderLayout.CENTER);
        f.add(bot, BorderLayout.SOUTH);
        f.add(Username, BorderLayout.NORTH);
        //adds the components to the sales panel
        SalesPanel.add(sp, BorderLayout.CENTER);
        SalesPanel.add(total, BorderLayout.SOUTH);
        

        

    }
    //action performed method
    @Override
    public void actionPerformed(ActionEvent e) {

        //if the payment button is clicked
        if(e.getSource() == AddItems){
            //creates the add item frame
            new AddItem();
            //cannot access the frame
            f.setEnabled(false);
        }
        //if the products button are clicked
        if(e.getSource() == product1){

            if(prices[0] == 0)
            {
                JOptionPane.showMessageDialog(null, "Add a product");
                return;
            }
            //creates the add item to the table
            model.addRow(new Object[]{items[0], prices[0], quantity, prices[0]});
            //decreases the stock
            stock[0]--;
                       

        }
        else if(e.getSource() == product2)
        {



            if(prices[1] == 0)
            {
                JOptionPane.showMessageDialog(null, "Add a product");
                return;
            }
            model.addRow(new Object[]{items[1], prices[1],quantity,prices[1]});
            stock[1]--;


        }
        else if(e.getSource() == product3)
        {


            if(prices[2] == 0)
            {
                JOptionPane.showMessageDialog(null, "Add a product");
                return;
            }
            model.addRow(new Object[]{items[2], prices[2],quantity,prices[2]});
            stock[2]--;
        }
        else if(e.getSource() == product4)
        {


            if(prices[3] == 0)
            {
                JOptionPane.showMessageDialog(null, "Add a product");
                return;
            }
            model.addRow(new Object[]{items[3], prices[3],quantity,prices[3]});
            stock[3]--;
        }
        else if(e.getSource() == product5)
        {


            if(prices[4] == 0)
            {
                JOptionPane.showMessageDialog(null, "Add a product");
                return;
            }
            model.addRow(new Object[]{items[4], prices[4],quantity,prices[4]});
            stock[4]--;
        }

        if(e.getSource() == payment)
        {
            if(model.getRowCount() == 0)
            {
                JOptionPane.showMessageDialog(null, "No items in the cart");
                return;

            }
            //creates the payment frame
            new Payment();
            //cannot access the frame
            f.setEnabled(false);
        }
        if(e.getSource() == backToMenuButton)
        {
            //creates the menu frame
            new MenuUI();
            //closes this frame
            f.dispose();
        }
        

        

    }
    


}