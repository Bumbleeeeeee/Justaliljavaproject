import java.util.*;

class ComputerPlayer extends Player {
  private Board board;

  private final double[][] pawnPos = {
        {0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0},
        {5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0},
        {1.0,  1.0,  2.0,  3.0,  3.0,  2.0,  1.0,  1.0},
        {0.5,  0.5,  1.0,  2.5,  2.5,  1.0,  0.5,  0.5},
        {0.5,  0.5,  1.0,  2.5,  2.5,  1.0,  0.5,  0.5},
        {0.5, -0.5, -1.0,  0.0,  0.0, -1.0, -0.5,  0.5},
        {0.5,  1.0, 1.0,  -2.0, -2.0,  1.0,  1.0,  0.5},
        {0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0}
      };
  private final double pawnValue = 10;

  private final double[][] rookPos = {   
      {0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0},
      {0.5,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.5},
      {-0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
      {-0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
      {-0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
      {-0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
      {-0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5},
      {0.0,   0.0, 0.0,  0.5,  0.5,  0.0,  0.0,  0.0}
    };
  private final double rookValue = 50;
  
  private final double[][] knightPos = {
        {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0},
        {-4.0, -2.0,  0.0,  0.0,  0.0,  0.0, -2.0, -4.0},
        {-3.0,  0.0,  1.0,  1.5,  1.5,  1.0,  0.0, -3.0},
        {-3.0,  0.5,  1.5,  2.0,  2.0,  1.5,  0.5, -3.0},
        {-3.0,  0.0,  1.5,  2.0,  2.0,  1.5,  0.0, -3.0},
        {-3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5, -3.0},
        {-4.0, -2.0,  0.0,  0.5,  0.5,  0.0, -2.0, -4.0},
        {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0}
      };
  private final double knightValue = 30;

  private final double[][] bishopPos = {    
        {-2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0},
        {-1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0},
        {-1.0,  0.0,  0.5,  1.0,  1.0,  0.5,  0.0, -1.0},
        {-1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0},
        {-1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0},
        {-1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0},
        {-1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0},
        {-2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0}
      };
  private final double bishopValue = 35;

  private final double[][] queenPos = {
        {-2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0},
        {-1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0},
        {-1.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0},
        {-0.5,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5},
        {0.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5},
        {-1.0,  0.5,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0},
        {-1.0,  0.0,  0.5,  0.0,  0.0,  0.0,  0.0, -1.0},
        {-2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0}
      };
  private final double queenValue = 90;
  
  private final double[][] kingPos = {
        {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
        {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
        {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
        {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
        {-2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0},
        {-1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0},
        {2.0,  2.0,  0.0,  0.0,  0.0,  0.0,  2.0,  2.0 },
        {2.0,  3.0,  1.0,  0.0,  0.0,  1.0,  3.0,  2.0}};
  private final double kingValue = 1000;


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

  /*public static int evaluate(Piece[][] board, boolean isWhite, int depth) {

  if (depth == 3) {
      return 
    }
    
    Piece[][] b = new Piece[8][8]();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        b[i][j] = board[i][j] == null ? null : board[i][j]
      }
    }

    ArrayList<int
    
    return evaluate(b, isWhite, depth+1);
  }

  public boolean movePiece(int[] start, int[] end) {
    Verifier verify = new Verifier(board, whiteT, lastMove);
    Piece piece = board[start[0]][start[1]];
    if (!verify.moveValid(start, end)) {
      return false;
    }
    board[start[0]][start[1]] = null;
    Piece temp = board[end[0]][end[1]];
    board[end[0]][end[1]] = piece;
    if (verify.castling) {
      if (end[1] == 2) {board[end[0]][3] = board[end[0]][0]; board[end[0]][0] = null;}
      else if (end[1] == 6) {board[end[0]][5] = board[end[0]][7]; board[end[0]][7] = null;}
    }
    if (verify.enpassant) {
      board[start[0]][end[1]] = null;
    }
    
    if (piece.getType() == "P" && ((end[0] == 0 && piece.isWhite() == true) || (end[0] == 7 && piece.isWhite() == false))) {
      piece = new Piece("Q", whiteT)
    }
    
    whiteT = !whiteT;
    board[end[0]][end[1]].madeAMove();
    lastMove[0] = start; lastMove[1] = end;
    return true;
  }

    public int checkStatus(boolean wT) {
    Verifier verify = new Verifier(board, whiteT, lastMove);
    boolean inCheck = false;
    boolean canMove = false;
    inCheck = verify.checkforCheck(verify.findKing(wT), wT);

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board[i][j] != null && board[i][j].isWhite() == wT) {
          Piece piece = board[i][j];
          for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
              int[] start = {i, j}; int[] end = {a, b};
              //System.out.println("i: " + i + " j: " + j + " a: " + a + " b: " + b);
              if (verify.checkStage1(start, end, wT)) {
                board[i][j] = null;
                Piece temp = board[a][b];
                board[a][b] = piece;
                if (!verify.checkforCheck(verify.findKing(wT), wT)) {
                  canMove = true;
                } 
                board[i][j] = piece;
                board[a][b] = temp;
              }
            }
          }
        }
      }
    }

    if (!inCheck && canMove) {
      return 0;
    } else if (inCheck && canMove) {
      return 1;
    } else if (inCheck && !canMove) {
      return 2;
    } else {
      return 3;
    }
  }
  

  */

}