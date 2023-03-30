import java.util.*;

class ComputerPlayer extends Player {

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
  
  private Board board;

  public ComputerPlayer(Board hat) {
    board = hat;
  }

 public int[][] getMove() {
    return easy();
  }

  //random moving
  public int[][] easy() {
    ArrayList<int[][]> moves = getPossibleMoves(board.getBoard(), board.whiteT, board.getLastMove());
    return moves.get((int)(Math.random()*moves.size()));
  }

  private ArrayList<int[]> getPieces(Piece[][] hat, boolean white) { 
    ArrayList<int[]> pieceLocations = new ArrayList<int[]>();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (hat[i][j] != null && hat[i][j].isWhite() == white) pieceLocations.add(new int[] {i, j});
      }
    }
    return pieceLocations;
  }

  private ArrayList<int[][]> getPossibleMoves(Piece[][] b, boolean white, int[][] lastMove) {
    Verifier verify = new Verifier(b, white, lastMove);
    ArrayList<int[][]> moves = new ArrayList<int[][]>();
    ArrayList<int[]> pieces = getPieces(b, white);
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

  private Piece[][] copyBoard(Piece[][] hat) {
    Piece[][] b = new Piece[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        b[i][j] = hat[i][j] == null ? null : hat[i][j];
      }
    }
    return b;
  }

  private double calculatePosScore(Piece[][] hat, boolean isWhite) {
    double score = 0;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (hat[i][j] != null) {
          double val = 0;
          if (hat[i][j].getType().equals("P")) {
            if (hat[i][j].isWhite()) val += pawnPos[i][j];
            else val += pawnPos[7-i][7-j];
            val += pawnValue;
          } else if (hat[i][j].getType().equals("R")) {
            if (hat[i][j].isWhite()) val += rookPos[i][j];
            else val += rookPos[7-i][7-j];
            val += rookValue;
          } else if (hat[i][j].getType().equals("N")) {
            if (hat[i][j].isWhite()) val += knightPos[i][j];
            else val += knightPos[7-i][7-j];
            val += knightValue;
          } else if (hat[i][j].getType().equals("B")) {
            if (hat[i][j].isWhite()) val += bishopPos[i][j];
            else val += bishopPos[7-i][7-j];
            val += bishopValue;
          } else if (hat[i][j].getType().equals("Q")) {
            if (hat[i][j].isWhite()) val += queenPos[i][j];
            else val += queenPos[7-i][7-j];
            val += queenValue;
          } else {
            if (hat[i][j].isWhite()) val += kingPos[i][j];
            else val += kingPos[7-i][7-j];
            val += kingValue;
          }
          if (hat[i][j].isWhite() == isWhite) score += val;
          else score -= val;
        }
      }
    }
    return score;
  }

  
  public int[][] medium() {
    ArrayList<int[][]> moves = getPossibleMoves(board.getBoard(), board.whiteT, board.getLastMove());
    int indexBest = 0, scoreBest = 0;
    for (int i = 0; i < moves.size(); i++) {
      Piece[][] tree = copyBoard(board.getBoard());
      if (movePiece(tree, moves.get(i)[0], moves.get(i)[1], board.getLastMove(), board.whiteT, "Q")) {
        double x = calculatePosScore(tree, board.whiteT);
      }
    }
    return moves.get(indexBest);
  }

    public boolean movePiece(Piece[][] b, int[] start, int[] end, int[][] lastMove, boolean whiteT, String promote) {
    Verifier verify = new Verifier(b, whiteT, lastMove);
    Piece piece = b[start[0]][start[1]];
    if (!verify.moveValid(start, end)) {
      return false;
    }
    b[start[0]][start[1]] = null;
    Piece temp = b[end[0]][end[1]];
    b[end[0]][end[1]] = piece;
    if (verify.castling) {
      if (end[1] == 2) {b[end[0]][3] = b[end[0]][0]; b[end[0]][0] = null;}
      else if (end[1] == 6) {b[end[0]][5] = b[end[0]][7]; b[end[0]][7] = null;}
    }
    if (verify.enpassant) {
      b[start[0]][end[1]] = null;
    }
    
    if (piece.getType() == "P" && ((end[0] == 0 && piece.isWhite() == true) || (end[0] == 7 && piece.isWhite() == false))) {
      piece = new Piece(promote, whiteT);
    }

    b[end[0]][end[1]].madeAMove();
    lastMove[0] = start; lastMove[1] = end;
    return true;
  }

/*
  public int evaluate(Piece[][] hat, boolean isWhite, int depth) {

  if (depth == 3) {
      return calculatePosScore(hat)
  }

  Piece[][] b = copyBoard(hat);

    ArrayList<int
    
    return evaluate(b, isWhite, depth+1);
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