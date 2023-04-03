import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainWindow extends JFrame{
  
  public MainWindow(){
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    GameRunner gRun = holder.gRun;
    this.add(gRun,BorderLayout.CENTER);

    this.setResizable(false);

    //this.setLocationRelativeTo(null);
    //16,47
    this.setPreferredSize(new Dimension(gRun.screenWidth + 16, gRun.screenHeight + 47));
    this.pack();
    System.out.println(this.getHeight() + ", " + this.getWidth());

    holder.gRun.addMouseListener(holder.cListen);

    holder.window = this;

    this.setVisible(true);  
    }
  }