package POS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class OrdersUI 
{
    List<List<String>> data = new ArrayList<>();
    String[] columnNames = {"Customer Name", "Product", "Quantity"};
    JTable table;
    OrdersUI()
    {
        JFrame f = new JFrame("Customer Order");
        f.setSize(800,800);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setLayout(new BorderLayout());
        f.setResizable(false);
        f.getContentPane().setBackground(new Color(238, 236, 225));
        f.setTitle("Orders");


        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);


    




        
        try (BufferedReader reader = new BufferedReader(new FileReader("Orders.csv"));) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                List<String> row = Arrays.asList(parts);
                data.add(row);
            }
        
            // Move this outside the reading loop
            for (List<String> dat : data) {
                // add each element to a column
                model.addRow(new Object[] { dat.get(0), dat.get(1), dat.get(2) }); 

                
               // print hello world
               



            }
        
        } catch (IOException e) { // Use a more descriptive variable name
            System.err.println("Error reading file: " + e.getMessage()); // Print to stderr for error messages
        }




        table = new JTable(model);
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
        table.getTableHeader().setBorder(MenuUI.border);

        //change the vertical lines color of the ttable
        table.setGridColor(Color.YELLOW);
        



        // Disable editing for all columns by default
        table.setDefaultEditor(Object.class, null);




        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        backToMenuButton.setFocusable(false);
        backToMenuButton.setForeground(Color.YELLOW);
        backToMenuButton.setBackground(Color.BLACK);
        backToMenuButton.setBorder(MenuUI.border);
        backToMenuButton.addActionListener(e -> 
        {
            f.dispose();
        });



        f.add(sp, BorderLayout.CENTER);
        f.add(backToMenuButton, BorderLayout.SOUTH);




        




    }

}
