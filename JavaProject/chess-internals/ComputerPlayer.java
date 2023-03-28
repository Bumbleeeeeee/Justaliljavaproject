import java.lang.*;
import java.util.*;

class ComputerPlayer extends Player {
  private Board board;

  //otherwise known as WHO CARES ABOUT EFFICIENCY HAHAHAAA
  //i think i'm going to cry
  public ComputerPlayer(Board hat) {
    board = hat;
  }

 public int[][] getMove() {
    return easy();
  }

  //random moving
  public int[][] easy() {
    ArrayList<int[][]> moves = getPossibleMoves(board.whiteT);
    return moves.get((int)(Math.random()*moves.size()));
  }

  private ArrayList<int[]> getPieces(boolean white) { 
    ArrayList<int[]> pieceLocations = new ArrayList<int[]>();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board.getBoard()[i][j] != null && board.getBoard()[i][j].isWhite() == white) pieceLocations.add(new int[] {i, j});
      }
    }
    return pieceLocations;
  }

  private ArrayList<int[][]> getPossibleMoves(boolean white) {
    Verifier verify = new Verifier(board.board, white, board.lastMove);
    ArrayList<int[][]> moves = new ArrayList<int[][]>();
    ArrayList<int[]> pieces = getPieces(white);
    for (int i = 0; i < pieces.size(); i++) {
      for (int j = 0; j < 8; j++) {
        for (int k = 0; k < 8; k++) {
          int[] mto = {j, k};
          if (verify.moveValid(pieces.get(i), mto)) {
            moves.add(new int[][] {pieces.get(i), mto});
          }
        }
      }
    }
    return moves;
  }

  

}