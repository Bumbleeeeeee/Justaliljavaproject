

import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

//JFrame object, will mostly be called for the pack() method, it mostly 
public class MainWindow extends JFrame{
  
  public MainWindow(){
    
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setResizable(false);

    holder.window = this;

    this.setVisible(true);  
    }
}