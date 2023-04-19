import java.util.Scanner;

public class ChessRunner{

  public static boolean gameActive = false;

  //status 1 = check
  //status 2 = win state
  //status 3 = stalemate
  //else asks for turn

  //all S.O.PLs are temp placeholders for an output!
  public static void run(){
    System.out.println("welcome");
    Board board = holder.board;
    System.out.println("??");
    int level = 0;
    gameActive = true;
    Scanner scan = new Scanner(System.in);
    level = scan.nextInt();
    ComputerPlayer computerPlayer = new ComputerPlayer(board, false);
    while (gameActive) {
      int status = board.checkStatus(board.isWhiteTurn());
      if (status == 2) {
        if (board.isWhiteTurn() == true) System.out.print(" BLACK WINS! ");
        if (board.isWhiteTurn() == false) System.out.print(" WHITE WINS! ");
        gameActive = false;
      
      } else if (status == 3) {
        System.out.println(" STALEMATE");
        gameActive = false;

      } else {
        computerPlayer.getMove(level);
      }
    }
  }
}