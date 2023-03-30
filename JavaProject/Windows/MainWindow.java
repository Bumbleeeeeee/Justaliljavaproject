import javax.swing.JFrame;

public class MainWindow extends JFrame{
  static JFrame window;
  
  public static void constructWindow(){
    window = new JFrame();
    
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);

    GameRunner gRun = holder.gRun;
    window.add(gRun);

    window.pack();

    window.setVisible(true);  
    window.setLocationRelativeTo(null);

    holder.gRun.addMouseListener(holder.cListen);
    }
  }