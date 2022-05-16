package Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import Classes.DB;
import Register.*;

/**
 * Login Form
*/
public class LoginForm extends JPanel
{
    // Properties
    private JTextField EmailField;
    private JLabel EmailLabel;
    private JButton ForgotButton;
    private JButton LoginButton;
    private JTextField PasswordField;
    private JLabel PasswordLabel;
    private JButton RegisterButton;
    private JLabel titleLabel;

    // Constructors
    public LoginForm(){        

        components();
        
    }

    // Methods
    public void components(){
        EmailField = new HintTextField("Enter Username/Email");
        EmailLabel = new JLabel();
        LoginButton =new JButton();
        ForgotButton = new JButton();
        RegisterButton = new JButton();
        PasswordField = new HintTextField("Enter Password");
        PasswordLabel = new JLabel();
        titleLabel = new JLabel();

        EmailField.setHorizontalAlignment(JTextField.CENTER);
        //EmailField.setText("Enter Email");


        EmailLabel.setText("Email :");

        LoginButton.setFont(new java.awt.Font("Segoe UI", 0, 13));
        LoginButton.setText("Login");
        LoginButton.addActionListener(new LoginListener());

        ForgotButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        ForgotButton.setText("Forgot Password");
        ForgotButton.addActionListener(new ForgotListener());

        RegisterButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        RegisterButton.setText("Register");
        RegisterButton.addActionListener(new RegisterListener());


        PasswordField.setHorizontalAlignment(JTextField.CENTER);
        //PasswordField.setText("Enter Password");

        PasswordLabel.setText("Password :");

        titleLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        titleLabel.setText("Welcome to Habinet");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(titleLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(RegisterButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ForgotButton, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
                            .addComponent(LoginButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(PasswordLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(EmailLabel)
                                .addGap(26, 26, 26)))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(PasswordField)
                            .addComponent(EmailField))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(PasswordLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(EmailLabel)
                            .addComponent(EmailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(LoginButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(ForgotButton)
                    .addComponent(RegisterButton))
                .addContainerGap()));


        setBackground(Color.white);
        setPreferredSize(new Dimension(360, 280));
        setMaximumSize(new Dimension(360, 280));
        setMinimumSize(new Dimension(360, 280));



    }

    class LoginListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            
            int userID = DB.getID(EmailField.getText(), PasswordField.getText());
            //if db doesnt work error overhere

            if (userID <= 0) {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password" );
            }
            else{
                app(DB.getUser(userID));
            }
            
            
 
            
        }
    }

    class RegisterListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           new RegisterPage();
        }
    }

    class ForgotListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           // TODO
        }
    }
}