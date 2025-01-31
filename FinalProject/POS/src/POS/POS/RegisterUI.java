/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package POS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 *
 * @author antho
 */
public class RegisterUI implements ActionListener
{
            JTextField UsernameField;
            JPasswordField PasswordField;
            JButton RegisterButton;
            JFrame f = new JFrame();
            String fileName = "credentials.txt";
            String line;
            public static List<String> lines = new ArrayList<>();
            public static String UsernameString;
            String PasswordString;
            
            public RegisterUI() 
            {
                //creates the frame
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
                f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
                f.setSize(400,200);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                f.setLayout(new BorderLayout());
                f.setResizable(false);
                f.getContentPane().setBackground(Color.BLACK);
                f.setTitle("Register");




                //creates the first panel
                JPanel panel1 = new JPanel();
                panel1.setBounds(50, 300, 1000, 300);
                panel1.setBackground(Color.BLACK);
                panel1.setLayout(new GridLayout(2,2));
                //creates the second panel
                JPanel panel2 = new JPanel();
                panel2.setBounds(50, 600, 1000, 300);
                panel2.setBackground(Color.BLACK);
                panel2.setLayout(new GridLayout());

                //creates the labels
                JLabel Username = new JLabel("Username:");
                Username.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                Username.setHorizontalAlignment(JLabel.CENTER);
                Username.setOpaque(true);
                Username.setForeground(Color.yellow);
                Username.setBackground(Color.black);
                Username.setBorder(MenuUI.border);
                Username.setFocusable(false);
                //creates the password label
                JLabel Password = new JLabel("Password:");
                Password.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                Password.setHorizontalAlignment(JLabel.CENTER);
                Password.setOpaque(true);
                Password.setForeground(Color.yellow);
                Password.setBackground(Color.black);
                Password.setBorder(MenuUI.border);
                Password.setFocusable(false);



                //creates the text fields
                UsernameField = new JTextField();
                UsernameField.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                UsernameField.setHorizontalAlignment(JTextField.CENTER);
                UsernameField.setOpaque(true);
                UsernameField.setForeground(Color.yellow);
                UsernameField.setBackground(Color.black);
                UsernameField.setBorder(MenuUI.border);

                //creates the password field
                PasswordField = new JPasswordField();
                PasswordField.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                PasswordField.setHorizontalAlignment(JTextField.CENTER);
                PasswordField.setOpaque(true);
                PasswordField.setForeground(Color.yellow);
                PasswordField.setBackground(Color.black);
                PasswordField.setBorder(MenuUI.border);
                

                //creates the register button
                RegisterButton = new JButton("Register");
                RegisterButton.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                RegisterButton.setFocusable(false);
                RegisterButton.setForeground(Color.yellow);
                RegisterButton.setBackground(Color.black);
                RegisterButton.setBorder(MenuUI.border);
                RegisterButton.addActionListener(this);
                RegisterButton.setFocusable(false);
                //adds the components to the panels
                panel1.add(Username);
                panel1.add(UsernameField);
                panel1.add(Password);
                panel1.add(PasswordField);
                panel2.add(RegisterButton);
                f.add(panel1, BorderLayout.CENTER);
                f.add(panel2, BorderLayout.SOUTH);
                
                //disables the main menu
                MenuUI.instance.setEnabled(false);

                //press enter to register 
                UsernameField.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            RegisterButton.doClick();
                        }
                    }
                });
                //press enter to register
                PasswordField.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            RegisterButton.doClick();
                        }
                    }
                });

}


    @Override
    
    public void actionPerformed(ActionEvent e) 
    {
        
        //if the register button is pressed
        if(e.getSource() == RegisterButton)
        {
            String Username = UsernameField.getText();
            char[] passwordChars = PasswordField.getPassword();
            String Password = new String(passwordChars);
            
            if(Username.isEmpty() || Password.isEmpty())
            {
                return;
            }
            //adds the username and password to the list
            MenuUI.instance.setEnabled(true);
            MenuUI.instance.toFront();
            MenuUI.instance.requestFocus();
            MenuUI.User.setText("User:" + UsernameField.getText());
            f.dispose();
            //writes the username and password to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(Username + "\n" + Password);
                
                
                
                System.out.println("Successfully wrote to the file.");
                
    
            } catch (IOException ee) {
                System.out.println("An error occurred."); 
            }
            //reads the file
            UsernameString = lines.get(0);
            PasswordString = lines.get(1);
            
        }
        
        
    }
}
