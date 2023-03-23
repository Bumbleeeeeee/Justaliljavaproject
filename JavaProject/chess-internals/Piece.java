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
    this.piece = piece;
    w = isWhite;
    this.moves = moves;
    
    try{
      //hehe *steals your code*
      
      if (piece == "K") 
        if (w) sprite = ImageIO.read(new File("JavaProject/images/king_WHITE.png"));
        else sprite = ImageIO.read(new File("JavaProject/images/king_BLACK.png"));

      else if (piece == "Q") 
        if (w) sprite = ImageIO.read(new File("JavaProject/images/queen_WHITE.png"));
        else sprite = ImageIO.read(new File("JavaProject/images/queen_BLACK.png"));

      else if (piece == "R") 
        if (w) sprite = ImageIO.read(new File("JavaProject/images/rook_WHITE.png"));
        else sprite = ImageIO.read(new File("JavaProject/images/rook_BLACK.png"));

      else if (piece == "B") 
        if (w) sprite = ImageIO.read(new File("JavaProject/images/bishop_WHITE.png"));
        else sprite = ImageIO.read(new File("JavaProject/images/bishop_BLACK.png"));

      else if (piece == "N") 
        if (w) sprite = ImageIO.read(new File("JavaProject/images/knight_WHITE.png"));
        else sprite = ImageIO.read(new File("JavaProject/images/knight_BLACK.png"));

      else 
        if (w) sprite = ImageIO.read(new File("JavaProject/images/pawn_WHITE.png"));
        else sprite = ImageIO.read(new File("JavaProject/images/pawn_BLACK.png"));    
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


   



