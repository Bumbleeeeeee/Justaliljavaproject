import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class MainWindow extends JFrame{
  
  public MainWindow(){
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    GameRunner gRun = holder.gRun;
    this.add(gRun,BorderLayout.CENTER);

    this.setSize(384,450);

    this.setResizable(true);
    this.setVisible(true);  
    this.setLocationRelativeTo(null);

    holder.gRun.addMouseListener(holder.cListen);

    holder.window = this;
    }
  }