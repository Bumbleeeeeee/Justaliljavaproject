import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

import java.awt.Color;
import java.awt.Rectangle;

class Board {
  public static Piece[][] board;
  public boolean whiteT;
  public Scanner scan;
  public int[][] lastMove;

  public static BufferedImage background;

  
  //draws squares on the board
  public static void drawBoard(Graphics2D g2){
    GameRunner gRun = holder.gRun;
    boolean flag = true;

    int curX = 0;
    int curY = 0;
    
    for(int currentRow = 0; currentRow < gRun.screenRows; currentRow++){
      for(int currentCollumn = 0; currentCollumn < gRun.screenColumns; currentCollumn++){

          
          try{
            background = ImageIO.read(new File("JavaProject/images/white.png"));
            if(flag)
            background = ImageIO.read(new File("JavaProject/images/black.png"));
          }
          catch(IOException e){
            e.printStackTrace();
            }

          g2.drawImage(background, curX, curY, gRun.tileSize, gRun.tileSize, null);

          //draws selection square, tbd if this works
          if(PieceManager.curSelection != null && ((PieceManager.curSelection[0] * 48) == curY && (PieceManager.curSelection[1] * 48) == curX)){
            Color color = new Color(0, 123, 255, 126); 
            g2.setPaint(color);
          
            Rectangle Square = new Rectangle(curX, curY, gRun.tileSize,gRun.tileSize);
            g2.fill(Square);
          }
          
          curX += gRun.tileSize;
          flag = !flag;
      }
      flag = !flag;
      curY += gRun.tileSize; curX = 0;
    }
  }


  public Piece[][] getBoard(){
    return board;
  }
  
  
  public Board() {
    board = PieceManager.pieceArray;
    scan = new Scanner(System.in);
    lastMove = new int[2][2];
    lastMove[0][0] = -1; lastMove[0][1] = -1; lastMove[1][0] = -1; lastMove[1][1] = -1;

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
      System.out.println("What would you like to promote your pawn to? (enter Q for queen, N for knight, or R for rook)");
      SubWindow tempWin = new SubWindow(piece, end[0], end[1]);
      tempWin.constructSubWindow();
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
  

  public boolean isWhiteTurn() {
    return whiteT;
  }

  private void reportError(String msg){
    //System.out.println(msg);
  }

  public int[][] getLastMove() {
    return lastMove;
  }

  
}