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
    int level = -1; //level has been made -1 for no AI; if you change, make sure to keep the check in getMove so it can be used for later!!
    gameActive = true;
    /* 
    ===========================commented bc the scan bricks the entire thing D:==========================
    update, its all been commented out, the while statement prevents any other methods from running, we just need to do something else insetead! 
    Shouldnt be too bad, i can fit it all into the Update method within Gamerunner!*/
    System.out.println("putting this here so youre not confused: a bunch of code has been commented\nchessrunner line20 for info! :D");
    /* 
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
        if (board.isWhiteTurn() == false) computerPlayer.getMove(level);
      }
    }*/
  }
}