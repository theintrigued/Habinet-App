
package Settings;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Maher Athar Ilyas
 */
public class SettingsPanel extends JPanel {

    private JToggleButton BackButton;
    private JRadioButton Carefree;
    private JLabel ChangLabel;
    private JButton DisableNotificationButton;
    private JLabel EmailLabel;
    private JButton EnableNotificationButton;
    private JRadioButton Medium;
    private JLabel ModeLabel;
    private JLabel NotificationLabel;
    private JLabel PasswordLabel;
    private JLabel SettingsTitle;
    private JRadioButton Strict;
    private JButton emailButton;
    private JButton passwordButton;

    /**
     * Creates new form SettingsPanel
     */
    public SettingsPanel() {
        initComponents();
    }

                         
    private void initComponents() {

        SettingsTitle = new JLabel();
        BackButton = new JToggleButton();
        NotificationLabel = new JLabel();
        ChangLabel = new JLabel();
        PasswordLabel = new JLabel();
        EmailLabel = new JLabel();
        ModeLabel = new JLabel();
        EnableNotificationButton = new JButton();
        DisableNotificationButton = new JButton();
        Strict = new JRadioButton();
        Medium = new JRadioButton();
        Carefree = new JRadioButton();
        passwordButton = new JButton();
        emailButton = new JButton();

        setPreferredSize(new Dimension(962, 800));

        SettingsTitle.setFont(new Font("Segoe UI Semibold", 0, 36)); // NOI18N
        SettingsTitle.setText("Settings");

        BackButton.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        BackButton.setText("Previous Page");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        NotificationLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NotificationLabel.setText("Notifications");

        ChangLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ChangLabel.setText("Change");

        PasswordLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PasswordLabel.setText("Change Password");

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EmailLabel.setText("Change Email");

        ModeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ModeLabel.setText("App Mode");

        EnableNotificationButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        EnableNotificationButton.setText("Enable");
        EnableNotificationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnableNotificationButtonActionPerformed(evt);
            }
        });

        DisableNotificationButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DisableNotificationButton.setText("Disable");

        Strict.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Strict.setText("Strict");
        Strict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StrictActionPerformed(evt);
            }
        });

        Medium.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Medium.setText("Medium");

        Carefree.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Carefree.setText("Carefree");

        passwordButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        passwordButton.setText("Send Password Reset Link");

        emailButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        emailButton.setText("Send Email Reset Link");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(BackButton)
                        .addGap(213, 213, 213)
                        .addComponent(SettingsTitle, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(NotificationLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(PasswordLabel)
                                    .addComponent(EmailLabel)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(ChangLabel)
                                        .addComponent(ModeLabel)))
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(Carefree)
                                    .addComponent(Medium)
                                    .addComponent(Strict)
                                    .addComponent(emailButton)
                                    .addComponent(passwordButton)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(EnableNotificationButton)
                                        .addGap(33, 33, 33)
                                        .addComponent(DisableNotificationButton)))))))
                .addContainerGap(274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(SettingsTitle)
                    .addComponent(BackButton))
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(NotificationLabel)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(EnableNotificationButton)
                        .addComponent(DisableNotificationButton)))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ChangLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ModeLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Strict)
                        .addGap(16, 16, 16)
                        .addComponent(Medium)
                        .addGap(18, 18, 18)
                        .addComponent(Carefree)))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordButton)
                    .addComponent(PasswordLabel))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailLabel)
                    .addComponent(emailButton))
                .addContainerGap(192, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void EnableNotificationButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                         
        // TODO add your handling code here:
    }                                                        

    private void StrictActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      


    // Variables declaration - do not modify                     

    // End of variables declaration                   
}
