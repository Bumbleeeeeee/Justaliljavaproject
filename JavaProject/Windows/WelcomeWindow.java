import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class WelcomeWindow extends JPanel{

  JButton button1A;
  JButton button1B;
    JButton button2B1;
    JButton button2B2;


  public WelcomeWindow(){

    this.setPreferredSize(new Dimension(GameRunner.screenWidth,GameRunner.screenHeight));
    this.setBackground(Color.BLUE);
    this.setDoubleBuffered(true);

    button1A = new JButton("RAWR");
    button1B = new JButton("AAAAAAAAAAAA");

    button1A.setBounds(0,0,GameRunner.screenWidth / 2, GameRunner.screenHeight); button1B.setBounds(GameRunner.screenWidth / 2,0,GameRunner.screenWidth / 2, GameRunner.screenHeight);

    
  }
}