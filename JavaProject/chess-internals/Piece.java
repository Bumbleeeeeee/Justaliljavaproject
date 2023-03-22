
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class Piece {

  public BufferedImage image;
  private String piece;
  private final boolean w;
  private int moves;

  public Piece(String piece, boolean isWhite) {
    this.piece = piece;
    w = isWhite;
    moves = 0;
    
    try{
    image = ImageIO.read(new File("JavaProject/images/CHESS_KING.png"));
  }
  catch(IOException e){
    e.printStackTrace();
    }
  }

  public boolean isWhite() {
    return w;
  }

  public String getType() {
    return piece;
  }
  
  public String getIcon() {
    return "W";
  }

  public void madeAMove() {
    moves++;
  }

  public int getMoves() {
    return moves;
  }
  
}


   



