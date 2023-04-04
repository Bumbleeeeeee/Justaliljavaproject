import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;

public class WelcomeWindow extends JPanel{

  JButton button1A;
  JButton button2A;
    JButton button2B;
    JButton button2C;


  public WelcomeWindow(){

    this.setBounds(0,0,60,30);

    this.setBackground(Color.BLUE);

    button1A = new JButton("test UnU");
    button2A = new JButton("test2 UvU");
  }
}