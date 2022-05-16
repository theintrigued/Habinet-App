package Register;

import Login.*;
import Classes.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;



/**
 * Registeration Form
 * @author Maher Athar Ilyas
 */
public class RegisterForm extends JPanel {

    private JRadioButton Carefree;
    private JLabel Changeable;
    private JLabel ChooseMode;
    private JTextField EmailField;
    private JLabel EmailLabel;
    private JRadioButton Medium;
    private JTextField PasswordField;
    private JLabel PasswordLabel;
    private JButton RegisterButton;
    private JLabel RegisterTitle;
    private JTextField UsernameField;
    private JLabel UsernameLabel;
    private ButtonGroup ModeButtons;
    private JRadioButton Strict;
    public User newUser;
    private String mode;

    /**
     * Creates new form RegisterForm
     */
    public RegisterForm() {
        initComponents();
        setBackground(Color.WHITE);
    }


    private void initComponents() {

        ModeButtons = new ButtonGroup();
        RegisterTitle = new JLabel();
        EmailLabel = new JLabel();
        UsernameLabel = new JLabel();
        PasswordLabel = new JLabel();
        Strict = new JRadioButton();
        Medium = new JRadioButton();
        Carefree = new JRadioButton();
        EmailField = new HintTextField("Enter Email");
        UsernameField = new HintTextField("Choose your Username");
        PasswordField = new HintTextField("Choose Password");
        ChooseMode = new JLabel();
        RegisterButton = new JButton();
        Changeable = new JLabel();

        setPreferredSize(new java.awt.Dimension(460, 390));

        RegisterTitle.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        RegisterTitle.setText("Register");

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        EmailLabel.setText("Enter Email :");

        UsernameLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        UsernameLabel.setText("Choose Username :");

        PasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        PasswordLabel.setText("Choose Password :");

        ModeButtons.add(Strict);
        Strict.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Strict.setText("Strict");

        ModeButtons.add(Medium);
        Medium.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Medium.setText("Medium");

        ModeButtons.add(Carefree);
        Carefree.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Carefree.setText("Carefree");

        EmailField.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        EmailField.setHorizontalAlignment(JTextField.CENTER);
        

        UsernameField.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        UsernameField.setHorizontalAlignment(JTextField.CENTER);


        PasswordField.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        PasswordField.setHorizontalAlignment(JTextField.CENTER);
        

        ChooseMode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ChooseMode.setText("Choose App Mode ");

        RegisterButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        RegisterButton.setText("Register");
        RegisterButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterButtonActionPerformed(evt);
            }
        });

        Changeable.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Changeable.setText("(Changeable weekly)");

        //Layout by Netbeans
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(PasswordLabel)
                                        .addComponent(UsernameLabel))
                                    .addGap(43, 43, 43)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(UsernameField, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(EmailLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(RegisterTitle)
                                        .addComponent(EmailField, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))))
                            .addComponent(RegisterButton, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(Changeable)
                            .addComponent(ChooseMode))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(Strict)
                            .addComponent(Medium)
                            .addComponent(Carefree))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RegisterTitle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailLabel)
                    .addComponent(EmailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(UsernameLabel)
                    .addComponent(UsernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordLabel)
                    .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(Strict, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(ChooseMode))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(Medium, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Changeable))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Carefree, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RegisterButton)
                .addContainerGap())
        );

        
        
        Medium.setBackground(Color.WHITE);
        Strict.setBackground(Color.WHITE);
        Carefree.setBackground(Color.WHITE);

        Strict.setSelected(true);
    }

    private void RegisterButtonActionPerformed(ActionEvent evt) {
        
        
        //if Any field is empty display dialog
        if ( UsernameField.getText().equals("Choose your Username")
            || PasswordField.getText().equals("Choose Password") ) {
            
            JOptionPane.showMessageDialog(null, "Complete all fields to Continue" );
        }
        else{

           
            if (Strict.isSelected()) {
                mode = "STRICT";
            }
            else if (Medium.isSelected()){
                mode = "MEDIUM";
            }
            else{
                mode = "CAREFREE";
            }

            //newUser = new User(UsernameField.getText(), PasswordField.getText(), EmailField.getText(), mode, 0, 0 , 0, 0) ;         
            
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Registeration Successful" );
        }

        
    }  

}
