import javax.swing.JFrame;
import Login.*;

/**
 * Program Description 
 * @author Maher Athar Ilyas
 * @version 28.04.2021
*/ 
public class LoginFrame
{
	public static void main(String[] args)
	{
		
		JFrame frame = new JFrame(); 

        frame.add(new LoginPanel());
       
        frame.setSize(1280, 800);
        frame.setTitle("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);	
		
	}
	
}