import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

//JFrame object, will mostly be called for the pack() method, it mostly 
public class MainWindow extends JFrame{
  
  public MainWindow(){
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    holder.window = this;

    this.setVisible(true);  
    
    this.add(holder.gRun, 0); //,JLayeredPane.DEFAULT_LAYER

    this.pack();
    }
  }