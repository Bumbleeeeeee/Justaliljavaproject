import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

public class WelcomeWindow extends JPanel{
  
  JButton button1A;
  JButton button1B;
    JButton button2B1;
    JButton button2B2;

    LiterallyJustASquare square;


  //as the name implies this manages the welcome window, currently unworking :sob: 
  public WelcomeWindow(){

    WindowPane pane = (WindowPane)holder.window.getContentPane();
    square = (LiterallyJustASquare)pane.getComponent(0);
    
    this.setLayout(null);
    this.setBounds(0,0,GameRunner.screenWidth, GameRunner.screenHeight);
    
    //this.setBackground(Color.BLUE);
    this.setOpaque(false);

    this.setDoubleBuffered(true);
    
    button1A = new JButton("RAWR");
    button1B = new JButton("AAAAAAAAAAAA");

    button1A.setBounds(48,96,144,96); button1B.setBounds(192,96,144,96);

    this.setVisible(true);

    this.add(button1A);
    this.add(button1B);

    button1A.addActionListener(new Actioner(this));
    button1B.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e){
        square.repaint();
      }
    });
  }

  private class Actioner implements ActionListener{

    JPanel welcome;
    public Actioner(JPanel welcome){
      this.welcome = welcome;}

    public void actionPerformed(ActionEvent e){
      System.out.println("IT WAS A :O");
      System.out.println(welcome);

      square.clearWelcomeRectangle();

      welcome.setVisible(false);
      holder.gRun.start();
    }
  }
}