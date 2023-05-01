import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
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
        this.setVisible(true);    
  }
}
