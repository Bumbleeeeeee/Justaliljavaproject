import java.util.*;

public class ChessRunner{

  public static void run(){
    Board board = new Board();
    Scanner scan = new Scanner(System.in);
    boolean gameActive = true;
    while (gameActive) {
      int status = board.checkStatus(board.isWhiteTurn());
      System.out.print("************");
      if (status == 2) {
        if (board.isWhiteTurn() == true) System.out.print(" BLACK WINS! ");
        if (board.isWhiteTurn() == false) System.out.print(" WHITE WINS! ");
        System.out.print("************");
        gameActive = false;
      
      } else if (status == 3) {
        System.out.println(" STALEMATE ************");
        gameActive = false;

      } else {
        if (board.isWhiteTurn()) {
          System.out.print(" WHITE'S TURN");
        } else System.out.print(" BLACK'S TURN");
        if (status == 1) System.out.print("- YOU ARE IN CHECK");
        System.out.print(" ************");
        System.out.println();
        System.out.println();
        board.printBoard();
        System.out.println();
        System.out.println("What are the coordinates of the piece you would like to move? (enter row number first, column number second)");
        int a = scan.nextInt(), b = scan.nextInt();
        System.out.println("What are the coordinates of the square you would like to move it to? (enter row number first, column number second)");
        int c = scan.nextInt(), d = scan.nextInt();
        //Player player = new Player();
        //board.movePiece(player.getMove());
        board.movePiece(new int[]{a, b}, new int[]{c, d});
      }
      System.out.println();
    }
  }
}