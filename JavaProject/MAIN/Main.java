import javax.swing.JLayeredPane;
import java.awt.Component;

class Main {
  public static void main(String[] args) {
  
    holder.board = new Board();
    holder.cListen = new ClickListener();
    
    
    MainWindow window = new MainWindow();
    WindowPane pane = new WindowPane();

    window.setContentPane(pane);


    LiterallyJustASquare square = new LiterallyJustASquare();
    pane.add(square, JLayeredPane.PALETTE_LAYER);
    square.repaint();

    holder.gRun = new GameRunner();
    pane.add(holder.gRun, JLayeredPane.DEFAULT_LAYER);
    
    WelcomeWindow welcome = new WelcomeWindow();
    pane.add(welcome,JLayeredPane.POPUP_LAYER);

    window.setVisible(true);
    window.pack();
    
    /*System.out.println("\nComponents in window:\n");
    
    Component[] comps = window.getContentPane().getComponents();
    for(int i = 0; i < comps.length; i++)  System.out.println(comps[i] + "\n");

    System.out.println("\nComponents in window's pane:\n");
    
    Component[] comps2 = pane.getComponents();
    for(int i = 0; i < comps2.length; i++)  System.out.println(comps2[i] + "\n");
    */
    }
  }