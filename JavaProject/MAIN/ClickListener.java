import java.awt.event.MouseListener;
import java.awt.event.*;
public class ClickListener implements MouseListener {

public GameRunner gRun = holder.gRun;
//hehe steals code
        
//where initialization occurs:
//Register for mouse events on blankArea and the panel.
    public ClickListener(){}

//THIS IS THE WORKING VERSION
  
  //maybe not needed
  public void mousePressed(MouseEvent e) {
    
    //locations of the selection as if it were array (not pixel based)
    int Xselect = e.getX() / gRun.tileSize;
    int Yselect = e.getY() / gRun.tileSize;

    //Returns click
    System.out.println("Mouse clicked (# of clicks: " + e.getClickCount() + ")" + "\n\t@ " + (Xselect) + ", " + (Yselect));
    
    System.out.println(ChessRunner.gameActive);
    if(ChessRunner.gameActive){
    //checks if there is a selection(for the highlighting) managed in PieceManager :)
      if(PieceManager.curSelection == null)
        PieceManager.onClick(Xselect, Yselect);

      //if theres a selection it carries out the move
      else{
        int[] end = new int[]{Yselect,Xselect};
      
        holder.board.movePiece(PieceManager.curSelection, end);
        PieceManager.curSelection = null;
      }
    }
  }

    public void mouseReleased(MouseEvent e) {
      //saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
    }
  

    public void mouseEntered(MouseEvent e) {
       //saySomething("Mouse entered", e);
      }
  

    public void mouseExited(MouseEvent e) {
       //saySomething("Mouse exited", e);
      }
  

    public void mouseClicked(MouseEvent e) {
    }
  

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on "
                        + e.getComponent().getClass().getName()
                        + ".\n");
    }
}