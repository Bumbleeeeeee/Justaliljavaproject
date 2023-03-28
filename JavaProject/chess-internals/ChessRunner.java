public class ChessRunner{

  public static void run(){
    Board board = holder.board;
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
        Player player;
        if (board.isWhiteTurn()) player = new Player();
        else player = new ComputerPlayer(board);
        //board.movePiece(player.getMove());
        int[][] move = player.getMove();
        board.movePiece(move[0], move[1]);
      }
      System.out.println();
    }
  }
}