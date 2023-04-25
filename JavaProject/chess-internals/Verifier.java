public class Verifier {

  public Piece[][] board;
  public boolean whiteT;
  public boolean castling;
  public boolean enpassant;
  public int[][] lastMove;
  
  public Verifier(Piece[][] board, boolean whiteT, int[][] lastMove) {
    this.board = board; this.whiteT = whiteT; this.lastMove = lastMove;
    castling = false; enpassant = false;
  }
  
  public Piece[][] getBoard(){
    return board;
  }
  
  public boolean moveValid(int[] start, int[] end) {
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

    if (!checkStage1(start, end, whiteT)) {
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
        if (end[1] == 2) {board[end[0]][0] = board[end[0]][3]; board[end[0]][3] = null;}
        else if (end[1] == 6) {board[end[0]][7] = board[end[0]][5]; board[end[0]][5] = null;}
      }
      if (enpassant) {
        board[start[0]][end[1]] = new Piece("P", !whiteT);
      }
      return false;
    }
    
    board[start[0]][start[1]] = piece;
    board[end[0]][end[1]] = temp;
    if (castling) {
      if (end[1] == 2) {board[end[0]][0] = board[end[0]][3]; board[end[0]][3] = null;}
      else if (end[1] == 6) {board[end[0]][7] = board[end[0]][5]; board[end[0]][5] = null;}
    }
    if (enpassant) {
      board[start[0]][end[1]] = new Piece("P", !whiteT);
    }
    return true;
  }

  private void reportError(String msg){
    //System.out.println(msg);
  }

  public boolean checkStage1(int[] start, int[] end, boolean wT) {
    Piece piece = board[start[0]][start[1]];
    if (piece == null) return false;
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

  public boolean checkforCheck(int[] kingPos, boolean isW) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board[i][j] != null && board[i][j].isWhite() != isW) {
          int[] enter = {i, j};
          if (checkStage1(enter, kingPos, !isW)) {
            return true;
          }
        }
      } 
    }
    return false;
  }

  public int[] findKing(boolean isW) {
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