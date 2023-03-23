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
      // saySomething("Mouse pressed; # of clicks: " + e.getClickCount(), e);
    }

    public void mouseReleased(MouseEvent e) {
      // saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
    }
  

    public void mouseEntered(MouseEvent e) {
       saySomething("Mouse entered", e);}
  

    public void mouseExited(MouseEvent e) {
       saySomething("Mouse exited", e);}
  

    public void mouseClicked(MouseEvent e) {
      System.out.println("Mouse clicked (# of clicks: " + e.getClickCount() + ")" + "\n\t@ " + (e.getX() / pManage.tileSize) + ", " + (e.getY() / pManage.tileSize));}
  

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on "
                        + e.getComponent().getClass().getName()
                        + ".\n");
    }
}