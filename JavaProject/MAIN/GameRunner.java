import javax.swing.JPanel;
import java.awt.*;

//This is essentially the object that is the panel on which the board is in, it makes up the window and manages updates
public class GameRunner extends JPanel{

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
  
  public void start(){
    System.out.println("Started");

    gameActive = true;
    update();
    repaint();
  }


  //////////////////

//Status:
  //1 = check
  //2 = checkmate
  //3 = stalemate
  
  public void update(){

    repaint();
    Board board = holder.board;
    
    int status = board.checkStatus(board.isWhiteTurn());
      if (status == 2) {
        if (board.isWhiteTurn()){ System.out.print(" BLACK WINS! "); new ExitWindow("BLACK WINS!");}
        if (!board.isWhiteTurn()){ System.out.print(" WHITE WINS! "); new ExitWindow("WHITE WINS!");}
        gameActive = false;} 
        
        else if (status == 3) {
        gameActive = false; new ExitWindow("STALEMATE");
        }
    
    
     else if(vsComputer && holder.board.whiteT == computer.getIsWhite()){
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
