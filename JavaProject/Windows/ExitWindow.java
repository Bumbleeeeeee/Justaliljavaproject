import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.JLabel;
//for helper
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class ExitWindow extends JPanel{

  Border blackBorder = BorderFactory.createLineBorder(Color.black, 2, true);

    public ExitWindow(String endingText){
        this.setBounds(48,96,288,144);
        MainWindow window = holder.window;
        JLayeredPane pane = (JLayeredPane)window.getContentPane();
        pane.add(this, JLayeredPane.POPUP_LAYER);
        
        JLabel text = new JLabel(endingText, SwingConstants.CENTER);
        text.setBounds(0,0,288,144);
        text.setOpaque(true);
        
        int identity = -1;
        if(endingText.equals("STALEMATE")) identity = 0;
        else if(endingText.equals("WHITE WINS!")) identity = 1;
        else if(endingText.equals("BLACK WINS!")) identity = 2;

        setColors(identity, text);
        
        text.setVisible(true);
        this.add(text);
        
        this.setVisible(true);    
  }

  public void setColors(int identity, JLabel text){
    
    if(identity == 0){
      Color back1 = new Color(84,96,82,255);
      Color fore1 = new Color(211,224,207,255);
      
      text.setBackground(back1);
      text.setForeground(fore1);}
    

    if(identity == 1){
      text.setBackground(Color.WHITE);
      text.setForeground(Color.BLACK);}
    

    if(identity == 2){
      text.setBackground(Color.BLACK);
      text.setForeground(Color.WHITE);}
  }
}
