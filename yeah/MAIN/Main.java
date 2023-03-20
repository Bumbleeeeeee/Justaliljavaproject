import java.lang.*;
import java.util.*;
import javax.swing.JFrame;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
  
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);

    PanelManager pManage = holder.pManage;
    window.add(pManage);
    window.pack();

    

    window.setLocationRelativeTo(null);
    window.setVisible(true);  
    
    ChessRunner.run();
    }
  }