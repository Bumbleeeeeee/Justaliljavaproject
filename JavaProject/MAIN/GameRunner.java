import javax.swing.JPanel;
import java.awt.*;
import java.lang.Runnable;

//This is essentially the object that is the panel on which the board is in, it makes up the window and manages updates
public class GameRunner extends JPanel implements Runnable{

  public static final int tileSize = 48;
  public final double FPS = 2.5;
  public static final int screenRows = 8;
  public static final int screenColumns = 8;

  public static final int screenWidth = tileSize * screenColumns;
  public static final int screenHeight = tileSize * screenRows;

  Thread gameThread;

  boolean vsComputer = false;
  ComputerPlayer computer;
  
  boolean gameActive = false;
  

  public GameRunner(){

    this.setBounds(0,0,screenWidth,screenHeight+13);
    this.setBackground(Color.black);
    this.setDoubleBuffered(true); 

    PieceManager.instantiatePieces();
    addMouseListener(holder.cListen);
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

      if(delta >= 1 && !SubWindow.subWindowExists){
        update();
        repaint();
        delta--;
      }
    }
  }
  
  public void start(){
    System.out.println("Started");

    startGameThread();
    gameActive = true;
    //ChessRunner.run();
  }


  //////////////////


  public void update(){

    Board board = holder.board;
    
    int status = board.checkStatus(board.isWhiteTurn());
      if (status == 2) {
        if (board.isWhiteTurn()) System.out.print(" BLACK WINS! ");
        if (!board.isWhiteTurn()) System.out.print(" WHITE WINS! ");
        gameActive = false;} 
        
        else if (status == 3) {
        System.out.println(" STALEMATE");
        gameActive = false;}
    
    
    if(vsComputer && holder.board.whiteT == computer.getIsWhite()){
      System.out.println("computers turn");
      computer.getMove();
    }
  }
  
  public void paintComponent(Graphics g){

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    Board.drawBoard(g2);
    PieceManager.DrawPieces(g2);
    
    g2.dispose();  
  }
}
