package Register;
import javax.swing.*;
/**
 * A simple Java class!
*/
public class RegisterPage extends JFrame
{
    // Properties

    // Constructors
    public RegisterPage(){
		JFrame frame = new JFrame(); 

        frame.add(new RegisterPanel());
       
        frame.setSize(1280, 800);
        frame.setTitle("Register Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);	
    }
    
    // Methods
    
}