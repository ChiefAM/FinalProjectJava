package POS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;


/**
 *
 * @author ChiefAM
 */

public class MenuUI extends JFrame implements ActionListener
{
    JLabel POSOption;
    public static JLabel User;
    JButton Sales;
    JButton UserLogin;
    JButton UserLogout;
    JButton UserRegister;
    JButton createCustomer;
    public static JFrame instance;
    String fileName = "credentials.txt";
    String line;
    List<String> lines = new ArrayList<>();
    
    public static Border outerBorder = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.YELLOW); // Top, left, bottom, right
    public static Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 0, 0); // Add padding
    public static CompoundBorder border = BorderFactory.createCompoundBorder(outerBorder, innerBorder);
    JButton customerOrder;
    JButton Orders;
    public MenuUI()
    {
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while ((line = reader.readLine()) != null) {
                lines.add(line); 

            }
            } catch (IOException ee) {
            System.out.println("Error reading file: " + ee.getMessage());
            }
        instance = this;
        //this is the main menu
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 800);
        this.setExtendedState(JFrame. MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.black);
        this.setTitle("Point of Sale System");


        User = new JLabel("User: Guest");
        User.setBounds(70, 40, 300, 45);
        User.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        User.setHorizontalAlignment(JLabel.CENTER);
        User.setOpaque(true);
        User.setForeground(Color.yellow);
        User.setBorder(border);
        User.setOpaque(true);
        User.setBackground(Color.black);
        User.setBorder(border);


        //this is the POSOption button in the first panel
        POSOption = new JLabel("Point of Sale System");
        POSOption.setBounds(50, 50, 800, 50);
        POSOption.setFont(new Font("Cosmic Sans",Font.BOLD, 40));
        POSOption.setForeground(Color.yellow);
        POSOption.setHorizontalAlignment(JLabel.CENTER);
        POSOption.setOpaque(true);
        POSOption.setBackground(Color.black);
        POSOption.setBorder(border);
    
        //this is the Sales button in the first panel
        Sales = new JButton("Sales");
        Sales.setBounds(50, 100, 400, 50);
        Sales.setFocusable(false);
        Sales.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        Sales.setForeground(Color.yellow);
        Sales.setBackground(Color.black);
        Sales.setBorder(border);
        Sales.addActionListener(this);
  
        //this is the UserRegister button in the first panel
        UserRegister = new JButton("Register");
        UserRegister.setBounds(450, 100, 400, 50);
        UserRegister.setFocusable(false);
        UserRegister.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        UserRegister.setForeground(Color.YELLOW);
        UserRegister.setBackground(Color.BLACK);
        UserRegister.setBorder(border);
        UserRegister.addActionListener(this);


        UserLogin = new JButton("Login");
        UserLogin.setBounds(450, 150, 400, 50);
        UserLogin.setFocusable(false);
        UserLogin.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        UserLogin.setForeground(Color.YELLOW);
        UserLogin.setBackground(Color.BLACK);
        UserLogin.setBorder(border);
        UserLogin.addActionListener(this);


        UserLogout = new JButton("Logout");
        UserLogout.setBounds(450, 200, 400, 50);
        UserLogout.setFocusable(false);
        UserLogout.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        UserLogout.setForeground(Color.YELLOW);
        UserLogout.setBackground(Color.BLACK);
        UserLogout.setBorder(border);
        UserLogout.addActionListener(this);


        createCustomer = new JButton("Create Customer");
        createCustomer.setBounds(50, 200, 400, 50);
        createCustomer.setFocusable(false);
        createCustomer.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        createCustomer.setForeground(Color.YELLOW);
        createCustomer.setBackground(Color.BLACK);
        createCustomer.setBorder(border);
        createCustomer.addActionListener(this);




        customerOrder = new JButton("Customer Order");
        customerOrder.setBounds(450, 200, 400, 50);
        customerOrder.setFocusable(false);
        customerOrder.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        customerOrder.setForeground(Color.YELLOW);
        customerOrder.setBackground(Color.BLACK);
        customerOrder.setBorder(border);
        customerOrder.addActionListener(this);

        
        Orders = new JButton("Orders");
        Orders.setBounds(50, 250, 400, 50);
        Orders.setFocusable(false);
        Orders.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
        Orders.setForeground(Color.YELLOW);
        Orders.setBackground(Color.BLACK);
        Orders.setBorder(border);
        Orders.addActionListener(this);

        



        JPanel mp = new JPanel();
        mp.setLayout(new GridLayout(3,2));
        mp.setBackground(Color.black);
        this.add(mp, BorderLayout.CENTER);

        JPanel up = new JPanel();
        up.setLayout(new GridLayout());
        up.setBackground(Color.black);
        this.add(up, BorderLayout.NORTH);
        



        

        this.add(User, BorderLayout.SOUTH);
        up.add(POSOption);
        mp.add(Sales);
        mp.add(UserRegister);
        mp.add(createCustomer);
        mp.add(UserLogin);
        mp.add(customerOrder);
        mp.add(Orders);
        mp.add(UserLogout);
        
        
        
        
        
        if (lines.isEmpty()) {
            new RegisterUI();
        } else if (User.getText().equals("User: Guest"))
        {
            new LoginUI();
        }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        
        
        
        if(e.getSource() == Sales)
        {


            if(User.getText().equals("User: Guest")){
                JOptionPane.showMessageDialog(null, "You must be logged in to make a sale");
                return;
            }
                new SalesUI();
                //close the current window
                this.dispose();


            
        }
        else if(e.getSource() == createCustomer)
        {
            new CreateCustomer();

            
        }
        else if(e.getSource() == UserRegister)
        {
            if(User.getText().equals("User: Guest")){
                new RegisterUI();
                //close the current window
                this.setEnabled(false);
            }
            else
            {
                System.out.println("You are already registered");
            }


            
            
        }
        else if(e.getSource() == UserLogin)
        {
            if(User.getText().equals("User: Guest"))
            {
            new LoginUI();
            //close the current window
            this.setEnabled(false);
            }
            else
            {
                System.out.println("You are already logged in");
            }
            
            
        }
        else if(e.getSource() == UserLogout)
        {
            if(!User.getText().equals("User: Guest"))
            {
                LoginUI.UsernameString = null;
                LoginUI.PasswordString = null;
                User.setText("User: Guest");
                System.out.println("You have been logged out");
            }
            else
            {
                System.out.println("You are already logged out");
            }

            
            
            
        }
        if(e.getSource() == customerOrder)
        {
            new CustomerOrder();


        }
        if(e.getSource() == Orders)
        {
            new OrdersUI();
        }


        
    }
}

