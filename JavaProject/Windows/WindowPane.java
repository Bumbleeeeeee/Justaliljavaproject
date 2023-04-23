import java.awt.*;
import javax.swing.JLayeredPane;
import java.awt.Dimension;

public class WindowPane extends JLayeredPane{


  public WindowPane(){
    this.setPreferredSize(new Dimension(GameRunner.screenWidth,GameRunner.screenHeight + 13));
    this.setBackground(Color.BLACK);
  }
  
  
  }