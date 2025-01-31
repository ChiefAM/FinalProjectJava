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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

/**
 *
 * @author antho
 */
public class LoginUI implements ActionListener
{
    JTextField UsernameField;
    JPasswordField PasswordField;
    JButton LoginButton;
    JFrame f = new JFrame();
    String fileName = "credentials.txt";
    String line;
    List<String> lines = new ArrayList<>();
    public static String UsernameString;
    public static String PasswordString;
        public LoginUI() 
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
                f.setSize(400,200);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                f.setLayout(new BorderLayout());
                f.setResizable(false);
                f.getContentPane().setBackground(new Color(238, 236, 225));
                f.setTitle("Login");



                JPanel panel1 = new JPanel();
                panel1.setBounds(50, 300, 1000, 300);
                panel1.setBackground(Color.BLACK);
                panel1.setLayout(new GridLayout(2,2));

                JPanel panel2 = new JPanel();
                panel2.setBounds(50, 600, 1000, 300);
                panel2.setBackground(Color.BLACK);
                panel2.setLayout(new GridLayout());


                JLabel Username = new JLabel("Username:");
                Username.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                Username.setHorizontalAlignment(JLabel.CENTER);
                Username.setOpaque(true);
                Username.setForeground(Color.yellow);
                Username.setBackground(Color.black);
                Username.setBorder(MenuUI.border);
                Username.setFocusable(false);
                
                JLabel Password = new JLabel("Password:");
                Password.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                Password.setHorizontalAlignment(JLabel.CENTER);
                Password.setOpaque(true);
                Password.setForeground(Color.yellow);
                Password.setBackground(Color.black);
                Password.setBorder(MenuUI.border);
                Password.setFocusable(false);




                UsernameField = new JTextField();
                UsernameField.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                UsernameField.setHorizontalAlignment(JTextField.CENTER);
                UsernameField.setOpaque(true);
                UsernameField.setForeground(Color.yellow);
                UsernameField.setBackground(Color.black);
                UsernameField.setBorder(MenuUI.border);


                PasswordField = new JPasswordField();
                PasswordField.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                PasswordField.setHorizontalAlignment(JTextField.CENTER);
                PasswordField.setOpaque(true);
                PasswordField.setForeground(Color.yellow);
                PasswordField.setBackground(Color.black);
                PasswordField.setBorder(MenuUI.border);
                


                LoginButton = new JButton("Login");
                LoginButton.setFont(new Font("Cosmic Sans",Font.BOLD, 25));
                LoginButton.setFocusable(false);
                LoginButton.setForeground(Color.yellow);
                LoginButton.setBackground(Color.black);
                LoginButton.setBorder(MenuUI.border);
                LoginButton.addActionListener(this);
                LoginButton.setFocusable(false);








                panel1.add(Username);
                panel1.add(UsernameField);
                panel1.add(Password);
                panel1.add(PasswordField);
                panel2.add(LoginButton);
                f.add(panel1, BorderLayout.CENTER);
                f.add(panel2, BorderLayout.SOUTH);

                UsernameField.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            LoginButton.doClick();
                        }
                    }
                });

                PasswordField.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            LoginButton.doClick();
                        }
                    }
                });

                MenuUI.instance.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == LoginButton)
        {

            
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while ((line = reader.readLine()) != null) {
                lines.add(line); 

            }
            } catch (IOException ee) {
            System.out.println("Error reading file: " + ee.getMessage());
            }
            UsernameString = lines.get(0);
            PasswordString = lines.get(1);
            if(Arrays.equals(PasswordField.getPassword(), PasswordString.toCharArray()) && UsernameField.getText().equals(UsernameString))
            {
                MenuUI.instance.setEnabled(true);
                MenuUI.instance.toFront();
                MenuUI.instance.requestFocus();
                System.out.println("Correct Username and Password");
                MenuUI.User.setText("User:" + UsernameString);
                f.dispose();

            }
            else
            {
                System.out.println("Incorrect Username or Password");
            }
            

        }
    }
}


