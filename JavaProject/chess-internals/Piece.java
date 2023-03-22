import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class Piece {

  public BufferedImage sprite;
  private String icon;
  private String piece;
  private final boolean w;
  private int moves;

  public Piece(String piece, boolean isWhite, int moves) {
    System.out.println(piece + " has been instantiated");
    this.piece = piece;
    w = isWhite;
    this.moves = moves;
    
    try{
    sprite = ImageIO.read(new File("JavaProject/images/CHESS_KING.png"));
  }
  catch(IOException e){
    e.printStackTrace();
    }

    if (piece == "K") {
      if (w) icon = "♔ "; 
      else icon = "♚ ";
    } else if (piece == "Q") {
      if (w) icon = "♕ ";
      else icon = "♛ ";
    } else if (piece == "R") {
      if (w) icon = "♖ ";
      else icon = "♜ ";
    } else if (piece == "B") {
      if (w) icon = "♗ ";
      else icon = "♝ ";
    } else if (piece == "N") {
      if (w) icon = "♘ ";
      else icon = "♞ ";
    } else {
      if (w) icon = "♙ ";
      else icon = "♟ ";
    }
  }

  public Piece(String piece, boolean isWhite) {
    this(piece, isWhite, 0);
  }

  public boolean isWhite() {
    return w;
  }

  public String getType() {
    return piece;
  }
  
  public String getIcon() {
    return icon;
  }

  public void madeAMove() {
    moves++;
  }

  public int getMoves() {
    return moves;
  }
  
}


   



