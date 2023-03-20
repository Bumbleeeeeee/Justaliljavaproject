class Piece {

  private String symbol;
  private String piece;
  private final boolean w;
  private int moves;

  public Piece(String pi, boolean wh) {
    piece = pi;
    w = wh;
    moves = 0;
    
    if (piece == "K") {
      if (w) symbol = "♔ "; 
      else symbol = "♚ ";
    } else if (piece == "Q") {
      if (w) symbol = "♕ ";
      else symbol = "♛ ";
    } else if (piece == "R") {
      if (w) symbol = "♖ ";
      else symbol = "♜ ";
    } else if (piece == "B") {
      if (w) symbol = "♗ ";
      else symbol = "♝ ";
    } else if (piece == "N") {
      if (w) symbol = "♘ ";
      else symbol = "♞ ";
    } else {
      if (w) symbol = "♙ ";
      else symbol = "♟ ";
    }
  }

  public boolean isWhite() {
    return w;
  }

  public String getType() {
    return piece;
  }
  
  public String getIcon() {
    return symbol;
  }

  public void madeAMove() {
    moves++;
  }

  public int getMoves() {
    return moves;
  }
  
}


   



