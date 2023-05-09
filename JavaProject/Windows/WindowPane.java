import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowPane extends JLayeredPane{

  JButton button;

  public WindowPane(){
    this.setPreferredSize(new Dimension(GameRunner.screenWidth,GameRunner.screenHeight -1)); //13
    this.setBackground(Color.BLACK);
  }
  
  public void addJButton(){
    button = new JButton("reset?");
    button.setBounds(293,384,92,13);
    button.setFocusPainted(false); button.setRolloverEnabled(false);

    Color back1 = new Color(84,96,82,255);
    Color fore1 = new Color(211,224,207,255);
    button.setBackground(fore1); button.setForeground(back1);

    button.setVisible(true);
    this.add(button, JLayeredPane.POPUP_LAYER);

    button.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent event){
        System.out.println("reset");
        Main.restart(holder.window);
      }
    });
  }
}