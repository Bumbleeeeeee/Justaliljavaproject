import java.lang.*;
import java.util.*;

class Board {
  private Piece[][] board;
  boolean whiteT;
  Scanner scan;
  private boolean castling;
  private boolean enpassant;
  private int[][] lastMove;

  public Board() {
    board = new Piece[8][8];
    scan = new Scanner(System.in);
    castling = false; enpassant = false;
    lastMove = new int[2][2];
    lastMove[0][0] = -1; lastMove[0][1] = -1; lastMove[1][0] = -1; lastMove[1][1] = -1;
    
    for (int i = 0; i < board[0].length; i++) {
      board[1][i] = new Piece("P", false); 
      board[6][i] = new Piece("P", true); 
    }

    board[0][0] = new Piece("R", false);
    board[0][1] = new Piece("N", false);
    board[0][2] = new Piece("B", false);
    board[0][3] = new Piece("Q", false);
    board[0][4] = new Piece("K", false);
    board[0][5] = new Piece("B", false);
    board[0][6] = new Piece("N", false);
    board[0][7] = new Piece("R", false);
    
    board[7][0] = new Piece("R", true);
    board[7][1] = new Piece("N", true);
    board[7][2] = new Piece("B", true);
    board[7][3] = new Piece("Q", true);
    board[7][4] = new Piece("K", true);
    board[7][5] = new Piece("B", true);
    board[7][6] = new Piece("N", true);
    board[7][7] = new Piece("R", true);

    whiteT = true;
  }

  public void printBoard() {
    boolean flipflop = false;
    System.out.println("  0 1 2 3 4 5 6 7");
    for (int i = 0; i < board.length; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < board[0].length; j++) {
        if (flipflop) System.out.print("\u001B[46m");
        else System.out.print("\u001B[44m");
        if (board[i][j] == null) System.out.print("__");
        else System.out.print(board[i][j].getIcon());
        System.out.print("\u001B[0m");
        flipflop = !flipflop;
      }
      System.out.println("x");
      flipflop = !flipflop;
    }
  }

  public boolean movePiece(int[] start, int[] end) {
    castling = false; enpassant = false;
    if (!(((start[0] < 8) && (start[0] >= 0)) && ((start[1] < 8) && (start[1] >= 0)) && ((end[0] < 8) && (end[0] >= 0)) && ((end[1] < 8) && (end[1] >= 0)))) {
      reportError("That's not a position on the board!");
      return false;
    }
    
    Piece piece = board[start[0]][start[1]];
    if (piece == null || whiteT != piece.isWhite()) {
      reportError("That's not your piece!");
      return false;
    }

    if (!moveValid(start, end, whiteT)) {
      reportError("That's not a valid move!");
      return false;
    }

    
    board[start[0]][start[1]] = null;
    Piece temp = board[end[0]][end[1]];
    board[end[0]][end[1]] = piece;
    if (castling) {
      if (end[1] == 2) {board[end[0]][3] = board[end[0]][0]; board[end[0]][0] = null;}
      else if (end[1] == 6) {board[end[0]][5] = board[end[0]][7]; board[end[0]][7] = null;}
    }
    if (enpassant) {
      board[start[0]][end[1]] = null;
    }
    
    if (checkforCheck(findKing(whiteT), whiteT)) {
      reportError("You can't leave your king in check!");
      board[start[0]][start[1]] = piece;
      board[end[0]][end[1]] = temp;
      if (castling) {
        if (end[0] == 2) {board[end[0]][0] = board[end[0]][3]; board[end[0]][3] = null;}
        else if (end[0] == 6) {board[end[0]][7] = board[end[0]][5]; board[end[0]][5] = null;}
      }
      if (enpassant) {
        board[start[0]][end[1]] = new Piece("P", !whiteT);
      }
      return false;
    }
    
    if (piece.getType() == "P" && ((end[0] == 0 && piece.isWhite() == true) || (end[0] == 7 && piece.isWhite() == false))) {
      System.out.println("What would you like to promote your pawn to? (enter Q for queen, N for knight, or R for rook)");
      String hat = scan.nextLine();
      hat = hat.toUpperCase();
      if (hat.equals("R")) board[end[0]][end[1]] = new Piece("R", piece.isWhite());
      else if (hat.equals("N")) board[end[0]][end[1]] = new Piece("N", piece.isWhite());
      else board[end[0]][end[1]] = new Piece("Q", piece.isWhite());
    }
      
    whiteT = !whiteT;
    board[end[0]][end[1]].madeAMove();
    lastMove[0] = start; lastMove[1] = end;
    castling = false; enpassant = false;
    return true;
    
  }

  public int checkStatus(boolean wT) {

    boolean inCheck = false;
    boolean canMove = false;

    inCheck = checkforCheck(findKing(wT), wT);

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board[i][j] != null && board[i][j].isWhite() == wT) {
          Piece piece = board[i][j];
          for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
              int[] start = {i, j}; int[] end = {a, b};
              //System.out.println("i: " + i + " j: " + j + " a: " + a + " b: " + b);
              if (moveValid(start, end, wT)) {
                board[i][j] = null;
                Piece temp = board[a][b];
                board[a][b] = piece;
                if (!checkforCheck(findKing(wT), wT)) {
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
  

  public boolean isWhiteTurn() {
    return whiteT;
  }

  private void reportError(String msg){
    System.out.println(msg);
  }


  private boolean moveValid(int[] start, int[] end, boolean wT) {
    Piece piece = board[start[0]][start[1]];
    String type = piece.getType();
    
    if (type.equals("P")) {
      if (end[1] == start[1] && board[end[0]][end[1]] == null &&
          ((wT && (end[0] == start[0] - 1 || (start[0] == 6 && end[0] == start[0] -2 && board[start[0]-1][start[1]] == null))) ||
          ((!wT && (end[0] == start[0] + 1 || (start[0] == 1 && end[0] == start[0] +2 && board[start[0]+1][start[1]] == null)))))) {
        return true;
      }
      
      if (board[end[0]][end[1]] != null && board[end[0]][end[1]].isWhite() != wT &&
         ((wT && end[0] == start[0] - 1 && Math.abs(start[1] - end[1]) == 1) ||
         (!wT && end[0] == start[0] + 1 && Math.abs(start[1] - end[1]) == 1))) {
        return true;
      }
      
      if ((wT && end[0] == start[0] - 1 && Math.abs(start[1] - end[1]) == 1 && lastMove[1][1] == end[1] && lastMove[1][0] == start[0] && lastMove[0][0] == 1 && board[lastMove[1][0]][lastMove[1][1]] != null && !board[lastMove[1][0]][lastMove[1][1]].isWhite() && board[lastMove[1][0]][lastMove[1][1]].getType() == "P") ||
         (!wT && end[0] == start[0] + 1 && Math.abs(start[1] - end[1]) == 1 && lastMove[1][1] == end[1] && lastMove[1][0] == start[0] && lastMove[0][0] == 6 && board[lastMove[1][0]][lastMove[1][1]] != null && board[lastMove[1][0]][lastMove[1][1]].isWhite() && board[lastMove[1][0]][lastMove[1][1]].getType() == "P")) {
        enpassant = true;
        return true;
      }
      return false;
    }
    

    if (type.equals("R")) {
      if (end[1] == start[1]) {
        for (int i = start[0]; i != end[0]; i += (end[0]-start[0])/Math.abs(end[0]-start[0])) {
          if (i != start[0] && board[i][end[1]] != null) {
            return false;
          }
        }
      } else if (end[0] == start[0]) {
        for (int i = start[1]; i != end[1]; i += (end[1]-start[1])/Math.abs(end[1]-start[1])) {
          if (i != start[1] && board[end[0]][i] != null) {
            return false;
          }
        }
      } else return false;
      if (board[end[0]][end[1]] == null || board[end[0]][end[1]].isWhite() != wT) { 
        return true;
      }
      return false;
    }
      

    if (type.equals("N")) {
      if ((board[end[0]][end[1]] == null || board[end[0]][end[1]].isWhite()!= wT) && Math.abs(start[0] - end[0]) * Math.abs(start[1] - end[1]) == 2) return true;
      return false;
    }

    
    if (type.equals("B")) {
      if (end[1] != start[1] && Math.abs((double)(start[0]-end[0]) / (start[1]-end[1])) == 1) {
        for (int i = 0; i != end[0]-start[0]; i += (end[0]-start[0])/Math.abs(end[0]-start[0])) {
          int j = Math.abs(i) * (end[1]-start[1])/Math.abs(end[1]-start[1]);
          if (j != 0 && i != 0 && board[start[0]+i][start[1]+j] != null) {
              return false;
          }
        }
      } else return false;
      if (board[end[0]][end[1]] == null || board[end[0]][end[1]].isWhite() != wT) { 
        return true;
      }
      return false;
    }

    
    if (type.equals("Q")) {
      if (end[1] == start[1]) {
        for (int i = start[0]; i != end[0]; i += (end[0]-start[0])/Math.abs(end[0]-start[0])) {
          if (i != start[0] && board[i][end[1]] != null) return false;
        }
      } else if (end[0] == start[0]) {
        for (int i = start[1]; i != end[1]; i += (end[1]-start[1])/Math.abs(end[1]-start[1])) {
          if (i != start[1] && board[end[0]][i] != null) return false;
        }
      } else if (end[1] != start[1] && Math.abs((double)(start[0]-end[0]) / (start[1]-end[1])) == 1) {
        for (int i = 0; i != end[0]-start[0]; i += (end[0]-start[0])/Math.abs(end[0]-start[0])) {
          int j = Math.abs(i) * (end[1]-start[1])/Math.abs(end[1]-start[1]);
          //System.out.println("Queen code: " + (start[0] + i) + " " + (start[1] + j));
          if (j != 0 && i != 0 && board[start[0]+i][start[1]+j] != null) {
              return false;
          }
        }
      } else return false;
      if (board[end[0]][end[1]] == null || board[end[0]][end[1]].isWhite() != wT) { 
        return true;
      }
      return false;
    }

    
    if (type.equals("K")) {
      if (((Math.abs(end[0] - start[0]) <= 1 && Math.abs(end[1] - start[1]) <= 1) && (Math.abs(end[0] - start[0]) != 0 || Math.abs(end[1] - start[1])!= 0))  && (board[end[0]][end[1]] == null || board[end[0]][end[1]].isWhite() != wT)) {
        return true;
      }
      
      if (piece.getMoves() == 0 && start[0] == end[0]) {
        if (end[1] == 2 && (board[end[0]][0] != null && board[end[0]][0].getType().equals("R") && board[end[0]][0].isWhite() == wT && board[end[0]][0].getMoves() == 0) &&
            (board[end[0]][end[1]-1] == null && board[end[0]][end[1]] == null && board[end[0]][end[1]+1] == null) && !checkforCheck(start, whiteT) && !checkforCheck(new int[] {start[0], start[1]-1}, whiteT) && !checkforCheck(new int[] {start[0], start[1]-2}, whiteT)) {
        castling = true;
        return true;
        }
        
        if (end[1] == 6 && (board[end[0]][7] != null && board[end[0]][7].getType().equals("R") && board[end[0]][7].isWhite() == wT && board[end[0]][7].getMoves() == 0) &&
            (board[end[0]][end[1]-1] == null && board[end[0]][end[1]] == null) && !checkforCheck(start, whiteT) && !checkforCheck(new int[] {start[0], start[1]+1}, whiteT) && !checkforCheck(new int[] {start[0], start[1]+2}, whiteT)) {
        castling = true;
        return true;
        }
        
      }


      return false;
    }

    
    return false;
  }

  private boolean checkforCheck(int[] kingPos, boolean isW) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board[i][j] != null && board[i][j].isWhite() != isW) {
          int[] enter = {i, j};
          if (moveValid(enter, kingPos, !isW)) {
            return true;
          }
        }
      } 
    }
    return false;
  }

  private int[] findKing(boolean isW) {
    int[] kingP = new int[2];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board[i][j] != null && board[i][j].isWhite() == isW && board[i][j].getType() == "K") {
          kingP[0] = i;
          kingP[1] = j;
        }
      }
    }
    return kingP;
  }
  
}