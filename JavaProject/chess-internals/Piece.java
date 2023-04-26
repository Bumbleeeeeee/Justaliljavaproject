import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class Piece {

  public BufferedImage sprite;
  private String piece;
  public final boolean w;
  private int moves;

  public Piece(String piece, boolean isWhite, int moves) {
    this.piece = piece;
    w = isWhite;
    this.moves = moves;
    sprite = getPieceIcon(piece, isWhite);
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

  public void madeAMove() {
    moves++;
  }

  public int getMoves() {
    return moves;
  }

  public static BufferedImage getPieceIcon(String identity, boolean w){
    BufferedImage imageOut = null;
    
    try{
      //hehe *steals your code*
      
      if (identity == "K") 
        if (w) imageOut = ImageIO.read(new File("JavaProject/images/king_WHITE.png"));
        else imageOut = ImageIO.read(new File("JavaProject/images/king_BLACK.png"));

      else if (identity == "Q") 
        if (w) imageOut = ImageIO.read(new File("JavaProject/images/queen_WHITE.png"));
        else imageOut = ImageIO.read(new File("JavaProject/images/queen_BLACK.png"));

      else if (identity == "R") 
        if (w) imageOut = ImageIO.read(new File("JavaProject/images/rook_WHITE.png"));
        else imageOut = ImageIO.read(new File("JavaProject/images/rook_BLACK.png"));

      else if (identity == "B") 
        if (w) imageOut = ImageIO.read(new File("JavaProject/images/bishop_WHITE.png"));
        else imageOut = ImageIO.read(new File("JavaProject/images/bishop_BLACK.png"));

      else if (identity == "N") 
        if (w) imageOut = ImageIO.read(new File("JavaProject/images/knight_WHITE.png"));
        else imageOut = ImageIO.read(new File("JavaProject/images/knight_BLACK.png"));

      else 
        if (w) imageOut = ImageIO.read(new File("JavaProject/images/pawn_WHITE.png"));
        else imageOut = ImageIO.read(new File("JavaProject/images/pawn_BLACK.png"));    
  }
  catch(IOException e){
    e.printStackTrace();
    }

    return imageOut;
  }
}


   



