import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class PieceManager {
    public static Piece[][] pieceArray = new Piece[8][8];

    public static void instantiatePieces(){
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
  
  
  
  
  
  
    public static void DrawPieces(Graphics2D g2){
      System.out.println("DRAWING PIECES");
      
      PanelManager pManage = holder.pManage;

      int curX = 0;
      int curY = 0;

      for(Piece[] arr : pieceArray){ 
          for(Piece cur : arr){
            System.out.println(cur);  

            if(cur != null){ 
                BufferedImage currentSprite = cur.sprite;
                System.out.println(currentSprite);
                g2.drawImage(currentSprite, curX, curY, pManage.tileSize, pManage.tileSize, null);
              }

              curX += pManage.tileSize;
            }
            curY += pManage.tileSize;
            curX = 0;
        }
    }
}

