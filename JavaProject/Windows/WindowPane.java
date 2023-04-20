import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.Dimension;

public class WindowPane extends JLayeredPane{


  public WindowPane(){
    this.setPreferredSize(new Dimension(holder.gRun.screenWidth,holder.gRun.screenHeight + 13));
    this.setBackground(Color.BLACK);
  }
  
  
  }