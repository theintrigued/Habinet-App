import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Classes.User;
import Help.HelpPanel;
import Menu.Menu;
import Settings.SettingsPanel;

/**
 * @author Maher Athar Ilyas
*/
public class app
{
    // Properties
    JFrame frame;
    Menu menu;
    JPanel otherPanel;
    User user;
    // Constructors

    public app(/*User user*/){

        //this.user = user;

        frame = new JFrame();

        //menu = new Menu(user);
        //otherPanel = new Home(user);

        otherPanel = new HelpPanel();
        menu = new Menu();

        frame.add(menu, BorderLayout.WEST);
        frame.add(otherPanel, BorderLayout.CENTER);
        

       
        frame.setSize(1280, 800);
        frame.setTitle("Register Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);	

    }
    
    // Methods
    
}