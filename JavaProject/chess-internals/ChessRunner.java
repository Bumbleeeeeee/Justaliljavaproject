public class ChessRunner{

  //status 1 = check
  //status 2 = win state
  //status 3 = stalemate
  //else asks for turn

  //all S.O.PLs are temp placeholders for an output!
  public static void run(){
    Board board = holder.board;
    boolean gameActive = true;
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
        Player player;
        if(status == 1) System.out.println(" Check! ");
        if (board.isWhiteTurn()) player = new Player();
        else player = new ComputerPlayer(board);
        int[][] move = player.getMove();
        board.movePiece(move[0], move[1]);
      }
      System.out.println();
    }
  }
}