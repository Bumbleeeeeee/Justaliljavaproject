import javax.swing.JPanel;
import java.awt.*;

public class PanelManager extends JPanel{

  public final int tileSize = 48;

  public final int screenRows = 8;
  public final int screenColumns = 8;

  public final int screenWidth = tileSize * screenColumns;
  public final int screenHeight = tileSize * screenRows;
  

  public PanelManager(){

    this.setPreferredSize(new Dimension(screenWidth,screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true); 
    //this.addKeyListener(keyH);
    this.setFocusable(true);

    PieceManager.instantiatePieces();
  }

  public void start(){
    ChessRunner.run();
    
    repaint();
  }


  public void paintComponent(Graphics g){;

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;

    Board.drawBoard(g2);
    PeiceManager.DrawPieces(g2);
    
    g2.dispose();
  }
}