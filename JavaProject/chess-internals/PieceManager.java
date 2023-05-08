import java.awt.*;
import java.awt.image.BufferedImage;

public class PieceManager {
    public static Piece[][] pieceArray = new Piece[8][8];
    public static int[] curSelection;

    public static void instantiatePieces(){

    //clears
      for(int row = 0; row < pieceArray.length; row++)
        for(int col = 0; col < pieceArray[row].length; col++)
          pieceArray[row][col] = null;
      
    //instantiates  
      
      for (int i = 0; i < pieceArray[0].length; i++) {
        pieceArray[1][i] = new Piece("P", false); 
        pieceArray[6][i] = new Piece("P", true); 
    }

    pieceArray[0][0] = new Piece("R", false);
    pieceArray[0][1] = new Piece("N", false);
    pieceArray[0][2] = new Piece("B", false);
    pieceArray[0][3] = new Piece("Q", false);
    pieceArray[0][4] = new Piece("K", false);
    pieceArray[0][5] = new Piece("B", false);
    pieceArray[0][6] = new Piece("N", false);
    pieceArray[0][7] = new Piece("R", false);
    
    pieceArray[7][0] = new Piece("R", true);
    pieceArray[7][1] = new Piece("N", true);
    pieceArray[7][2] = new Piece("B", true);
    pieceArray[7][3] = new Piece("Q", true);
    pieceArray[7][4] = new Piece("K", true);
    pieceArray[7][5] = new Piece("B", true);
    pieceArray[7][6] = new Piece("N", true);
    pieceArray[7][7] = new Piece("R", true);
    }


    public static void onClick(int X, int Y){
      Piece piece = holder.board.getBoard()[Y][X];
      
      if (isPiece(X,Y) && (piece == null || holder.board.whiteT == piece.isWhite())) 
        if(curSelection == null || !(curSelection[0] == Y && curSelection[1] == X))
          curSelection = new int[]{Y,X};
    }

    public static boolean isPiece(int X, int Y){
      for(int curY = 0; curY < pieceArray.length; curY++)
        for(int curX = 0; curX < pieceArray.length; curX++)
          if((curX == X && curY == Y) && pieceArray[curY][curX] != null)
            return true;
      return false;
    }
  
  
  
  
  
    public static void DrawPieces(Graphics2D g2){

      int curX = 0;
      int curY = 0;

      for(Piece[] arr : pieceArray){ 
          for(Piece cur : arr){  

            if(cur != null){ 
                BufferedImage currentSprite = cur.sprite;
                g2.drawImage(currentSprite, curX, curY, GameRunner.tileSize, GameRunner.tileSize, null);
              }

              curX += GameRunner.tileSize;
            }
            curY += GameRunner.tileSize;
            curX = 0;
        }
    }
}

