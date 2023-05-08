import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import javax.swing.JLabel;
//for helper
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class ExitWindow extends JPanel{

  JLabel text;
  
  JButton restart;
  JButton exit;
  
  Color back1 = new Color(84,96,82,255);
  Color fore1 = new Color(211,224,207,255);
    
  Border neutralBorder = BorderFactory.createLineBorder(fore1, 3, true);
  Border blackBorder = BorderFactory.createLineBorder(Color.black, 3, true);
  Border whiteBorder = BorderFactory.createLineBorder(Color.white, 3, true);
  
  public ExitWindow(String endingText){
      
    int identity = -1;
    if(endingText.equals("STALEMATE")) identity = 0;
    else if(endingText.equals("WHITE WINS!")) identity = 1;
    else if(endingText.equals("BLACK WINS!")) identity = 2;
      
    //

    this.setBackground(back1);
    this.setBorder(neutralBorder);
    this.setBounds(48,96,288,192);
      
    MainWindow window = holder.window;
    JLayeredPane pane = (JLayeredPane)window.getContentPane();
    pane.add(this, JLayeredPane.POPUP_LAYER);

      //
        
    text = new JLabel(endingText, SwingConstants.CENTER);
    text.setBounds(0,0,288,144);
    text.setOpaque(true);
    setColors(identity);
    text.setVisible(true);
        
      //

    restart = new JButton("Restart?");
    exit = new JButton("Exit?");

    restart.setBounds(10,147,110,38); exit.setBounds(168,147,110,38);
    restart.setVisible(true); exit.setVisible(true);
      
    restart.setRolloverEnabled(false); exit.setRolloverEnabled(false);
    restart.setFocusPainted(false); exit.setFocusPainted(false);

    restart.setBackground(back1); restart.setForeground(fore1);
    exit.setBackground(back1); exit.setForeground(fore1);

    
    ////ACTION LISTENERS////
    
    restart.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent e){
      System.out.println("RESTART");

      Main.restart(holder.window);
      }
    });

    exit.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent e){
        System.out.println("EXIT");

        holder.window.dispose();
      }
    });

    //////////
      
    
    this.add(text); this.add(restart); this.add(exit);
        
    this.setVisible(true);    
  }

  public void setColors(int identity){
    
    if(identity == 0){
      text.setBackground(back1);
      text.setForeground(fore1);
      text.setBorder(neutralBorder);}

    if(identity == 1){
      text.setBackground(Color.WHITE);
      text.setForeground(Color.BLACK);
      text.setBorder(blackBorder);}

    if(identity == 2){
      text.setBackground(Color.BLACK);
      text.setForeground(Color.WHITE);
      text.setBorder(whiteBorder);}
  }
}
