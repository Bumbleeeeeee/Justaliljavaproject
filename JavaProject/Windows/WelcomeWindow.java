import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.*;
import java.awt.Component;
import javax.swing.JLayeredPane;

public class WelcomeWindow extends JPanel{

  JButton button1A;
  JButton button1B;
    JButton button2B1;
    JButton button2B2;


  //as the name implies this manages the welcome window, currently unworking :sob: 
  public WelcomeWindow(){

    this.setLayout(null);
    this.setBounds(0,0,200,200);
    this.setBackground(Color.BLUE);
    this.setDoubleBuffered(true);

    button1A = new JButton("RAWR");
    button1B = new JButton("AAAAAAAAAAAA");

    button1A.setBounds(0,0,100,100); button1B.setBounds(100,0,100,100);

    this.setVisible(true);

    this.add(button1A);
    this.add(button1B);

    button1A.addActionListener(new Actioner(this));

    
  }

  private class Actioner implements ActionListener{

    JPanel welcome;
    public Actioner(JPanel welcome){
      this.welcome = welcome;}

    public void actionPerformed(ActionEvent e){
      System.out.println("IT WAS A :O");
      System.out.println(welcome);

      //JLayeredPane pane = (JLayeredPane)((JFrame)SwingUtilities.getWindowAncestor(welcome)).getContentPane()
      //System.out.println(pane);
      
      welcome.setVisible(false);
      
      //holder.gRun.start();
    }
  }
}