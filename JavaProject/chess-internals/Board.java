import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

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
    boolean flag = true;

    int curX = 0;
    int curY = 0;
    
    if(!SubWindow.subWindowExists){
    for(int currentRow = 0; currentRow < GameRunner.screenRows; currentRow++){
      for(int currentCollumn = 0; currentCollumn < GameRunner.screenColumns; currentCollumn++){

          
          try{
            background = ImageIO.read(new File("JavaProject/images/black.png"));
            if(flag)
            background = ImageIO.read(new File("JavaProject/images/white.png"));
          }
          catch(IOException e){
            e.printStackTrace();
            }

          g2.drawImage(background, curX, curY, GameRunner.tileSize, GameRunner.tileSize, null);

          //draws selection square
          if(PieceManager.curSelection != null)
            drawSelectionSquare(g2, curX, curY);
          
          curX += GameRunner.tileSize;
          flag = !flag;
      }
      flag = !flag;
      curY += GameRunner.tileSize; curX = 0;
    }

    drawTurnBar(holder.board.whiteT, g2);
    }
  }

  private static void drawTurnBar(boolean whiteTurn, Graphics2D g2){
    
    Rectangle Square = new Rectangle(0,384,383,12);
    
    if(whiteTurn)
      g2.setPaint(Color.WHITE);
    else
      g2.setPaint(Color.BLACK);

    g2.fill(Square);

    //
    
    Color back1 = new Color(84,96,82,255);
    
    Rectangle Square2 = new Rectangle(0,384,383,12);
    g2.setStroke(new BasicStroke((float) 5));

    g2.setPaint(back1);

    g2.draw(Square2);

    //
    
    Rectangle Square3 = new Rectangle(3, 387, 377, 6);
    g2.setStroke(new BasicStroke((float) 0.5));
    
    if(!whiteTurn)
      g2.setPaint(Color.WHITE);
    else
      g2.setPaint(Color.BLACK);

    g2.draw(Square3);
  } 

  private static void drawSelectionSquare(Graphics2D g2, int curX, int curY){
    
    if((PieceManager.curSelection[0] * 48) == curY && (PieceManager.curSelection[1] * 48) == curX){
      Color color = new Color(0, 123, 255, 126); 
      g2.setPaint(color);

      Rectangle Square = new Rectangle(curX, curY, GameRunner.tileSize,GameRunner.tileSize);
      g2.fill(Square);}
  

    

    //draws possible moves

    Board boardObj = holder.board;
    
    Piece piece = board[PieceManager.curSelection[0]][PieceManager.curSelection[1]];
    ArrayList<int[]> possibleMoves = boardObj.getPossibleMoves(piece, piece.isWhite(), boardObj.lastMove, new int[] {curX / 48, curY / 48});
    
    for(int[] curArr : possibleMoves){
      if(curArr[0] == curY / 48 && curArr[1] == curX / 48){
        Color color = new Color(180, 255, 180, 180); 
        g2.setPaint(color);
        
        Rectangle Square = new Rectangle(curArr[1] * 48, curArr[0] * 48, GameRunner.tileSize,GameRunner.tileSize);
        g2.fill(Square);
      }
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
  
  public boolean movePiece(int[] start, int[] end) {
    Verifier verify = new Verifier(board, whiteT, lastMove);
    Piece piece = board[start[0]][start[1]];
    if (!verify.moveValid(start, end)) {
      return false;
    }
    board[start[0]][start[1]] = null;
    
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
      
      SubWindow.subWindowExists = true;
      SubWindow tempWin = new SubWindow(piece, end[0], end[1], holder.window);
      holder.window.getContentPane().add(tempWin,JLayeredPane.POPUP_LAYER);
    }
    
    whiteT = !whiteT;

    
    
    board[end[0]][end[1]].madeAMove();
    lastMove[0] = start; lastMove[1] = end;
    return true;
  }

  public int checkStatus(boolean wT) {
    Piece[][] b = copyBoard(board);
    Verifier verify = new Verifier(b, wT, lastMove);
    boolean inCheck = false;
    boolean canMove = false;
    inCheck = verify.checkforCheck(verify.findKing(wT), wT);

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (b[i][j] != null && b[i][j].isWhite() == wT) {
          Piece piece = b[i][j];
          for (int a = 0; a < 8; a++) {
            for (int r = 0; r < 8; r++) {
              int[] start = {i, j}; int[] end = {a, r};
              //System.out.println("i: " + i + " j: " + j + " a: " + a + " b: " + b);
              if (verify.checkStage1(start, end, wT)) {
                b[start[0]][start[1]] = null;
                Piece temp = b[end[0]][end[1]];
                b[end[0]][end[1]] = piece;
                if (verify.castling) {
                  if (end[1] == 2) {b[end[0]][3] = b[end[0]][0]; b[end[0]][0] = null;}
                  else if (end[1] == 6) {b[end[0]][5] = b[end[0]][7]; b[end[0]][7] = null;}}
                if (verify.enpassant) {
                  b[start[0]][end[1]] = null;
                }
                if (!verify.checkforCheck(verify.findKing(wT), wT)) {
                  canMove = true;
                } 
                b[start[0]][start[1]] = piece;
                b[end[0]][end[1]] = temp;
                if (verify.castling) {
                  if (end[1] == 2) {b[end[0]][0] = b[end[0]][3]; b[end[0]][3] = null;}
                  else if (end[1] == 6) {b[end[0]][7] = b[end[0]][5]; b[end[0]][5] = null;}
    }
                if (verify.enpassant) {
      b[start[0]][end[1]] = new Piece("P", !wT);
    }
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

  public int[][] getLastMove() {
    return lastMove;
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

  private ArrayList<int[]> getPossibleMoves(Piece piece, boolean white, int[][] lastMove, int[] curLocation) {
    Verifier verify = new Verifier(board, white, lastMove);
    ArrayList<int[]> moves = new ArrayList<int[]>();

      for (int j = 0; j < GameRunner.screenRows; j++) {
        for (int k = 0; k < GameRunner.screenColumns; k++) {
          
          int[] posMove = {j, k};

          if (verify.moveValid(getPieceLocation(piece), posMove)){
            moves.add(posMove);}
        }
      }
      
      return moves;
  }

  private int[] getPieceLocation(Piece piece){
    for(int i = 0; i < GameRunner.screenRows; i++){
      for(int j = 0; j < GameRunner.screenColumns; j++){
        if(piece.equals(board[i][j]))
          return new int[] {i,j};
      }
    }
    return new int[] {0,0};
  }
}