import java.awt.event.MouseListener;
import java.awt.event.*;
public class ClickListener implements MouseListener {

public PanelManager pManage = holder.pManage;
//hehe steals code
        
//where initialization occurs:
//Register for mouse events on blankArea and the panel.
    public ClickListener(){    
        pManage.addMouseListener(this);
    }

  
  //maybe not needed
  public void mousePressed(MouseEvent e) {
    int Xselect = e.getX() / pManage.tileSize;
    int Yselect = e.getY() / pManage.tileSize;
    System.out.println("Mouse clicked (# of clicks: " + e.getClickCount() + ")" + "\n\t@ " + (Xselect) + ", " + (Yselect));
    
    if(PieceManager.curSelection == null)
      PieceManager.onClick(Xselect, Yselect);
    else{
      int[] end = new int[]{Yselect,Xselect};
      
      holder.board.movePiece(PieceManager.curSelection, end);
      PieceManager.curSelection = null;
    }
  }

    public void mouseReleased(MouseEvent e) {
      //saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
    }
  

    public void mouseEntered(MouseEvent e) {
       saySomething("Mouse entered", e);}
  

    public void mouseExited(MouseEvent e) {
       saySomething("Mouse exited", e);}
  

    public void mouseClicked(MouseEvent e) {
    }
  

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on "
                        + e.getComponent().getClass().getName()
                        + ".\n");
    }
}