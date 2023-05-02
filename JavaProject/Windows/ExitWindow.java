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

    public ExitWindow(){
        this.setBounds(48,96,240,144);
        MainWindow window = holder.window;
        JLayeredPane pane = (JLayeredPane)window.getContentPane();
        pane.add(this, JLayeredPane.POPUP_LAYER);
        
        JLabel text = new JLabel("unspecified[placeholder]");
        text.setBounds(0,0,240,144);
        
        text.setVisible(true);
        this.add(text);
        
        this.setVisible(true);    
  }
}
