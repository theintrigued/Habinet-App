package Menu;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import javax.swing.*;
/**
 * Program Description 
 * @author Maher Athar Ilyas
 * @version 27.04.2021
*/ 
public class MenuFrame
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        // Constants
        JFrame frame = new JFrame();
        
//        Menu menu = new Menu(new User("Ather Ilyas", "happiness", "ather@gmail.com", "Strict", 1, 10, 2, 5));
        Menu menu = new Menu();
 

        frame.add(menu, BorderLayout.WEST);

        frame.setSize(1280, 800);
        frame.setTitle("Menu Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // Variables
        
        // Program Code
  
        scan.close();
    }
    
}