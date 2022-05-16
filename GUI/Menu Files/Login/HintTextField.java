package Login;
import java.awt.Color;  
import java.awt.Font;  
import java.awt.event.FocusAdapter;  
import java.awt.event.FocusEvent;  
import javax.swing.JTextField;  

/**
 * Source 
 * http://javaswingcomponents.blogspot.com/2012/05/how-to-create-simple-hinttextfield-in.html
 */
public class HintTextField extends JTextField {  
  
  Font gainFont = new Font("Segoe UI", 0, 13);
  Font lostFont = new Font("Segoe UI", 0, 13);
  
  public HintTextField(final String hint) {  
  
    setText(hint);  
    setFont(lostFont);  
    setForeground(Color.GRAY);  
  
    this.addFocusListener(new FocusAdapter() {  
  
      @Override  
      public void focusGained(FocusEvent e) {  
        if (getText().equals(hint)) {  
          setText("");  
          setFont(gainFont);  
        } else {  
          setText(getText());  
          setForeground(Color.BLACK);
          setFont(gainFont);  
        }  
      }  
  
      @Override  
      public void focusLost(FocusEvent e) {  
        if (getText().equals(hint)|| getText().length()==0) {  
          setText(hint);  
          setFont(lostFont);  
          setForeground(Color.GRAY);  
        } else {  
          setText(getText());  
          setFont(gainFont);  
          setForeground(Color.BLACK);  
        }  
      }  
    });  
  
  }  
}