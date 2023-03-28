import javax.swing.JFrame;

class Main {
  public static void main(String[] args) {
  
    MainWindow window = new MainWindow();
    
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);

    GameRunner gRun = holder.gRun;

    window.pack();

    window.setVisible(true);  
    window.setLocationRelativeTo(null);
    
    gRun.start();
    }
  }