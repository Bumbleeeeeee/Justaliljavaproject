import javax.swing.JPanel;
import java.awt.*;
import java.lang.Runnable;

public class PanelManager extends JPanel implements Runnable{

  public final int tileSize = 48;
  public final int FPS = 20;
  public final int screenRows = 8;
  public final int screenColumns = 8;

  public final int screenWidth = tileSize * screenColumns;
  public final int screenHeight = tileSize * screenRows;

  Thread gameThread;
  

  public PanelManager(){

    this.setPreferredSize(new Dimension(screenWidth,screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true); 
    //this.addKeyListener(keyH);
    this.setFocusable(true);

    PieceManager.instantiatePieces();
    ClickListener cListen = holder.cListen;
  }


  public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run(){
    double drawInterval = 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while(gameThread != null){

      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;

      lastTime = currentTime;

      if(delta >= 1){
        update();
        repaint();
        delta--;
      }
    }
  }
  
  public void start(){
    startGameThread();
    
    ChessRunner.run();
    
    repaint();
  }


  //////////////////


  public void update(){
    
  }
  
  public void paintComponent(Graphics g){

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    Board.drawBoard(g2);
    PieceManager.DrawPieces(g2);
    
    g2.dispose();  
  }
}
