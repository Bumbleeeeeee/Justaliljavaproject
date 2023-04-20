import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.Component;

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
    
    button1A = new JButton("2 Player!");
    button1B = new JButton("VS Computer!");

    button1A.setBounds(48,96,144,96); button1B.setBounds(192,96,144,96);

    this.setVisible(true);

    this.add(button1A);
    this.add(button1B);

    button1A.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e){

        JButton sourceButton = (JButton) e.getSource();
        MainWindow win = (MainWindow)SwingUtilities.getWindowAncestor(sourceButton);
        Component[] comps = win.getComponents();
        WelcomeWindow welcome = (WelcomeWindow)comps[0];
        
        welcome.setVisible(false);
        holder.gRun.start();
      }
    });
    button1B.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e){
        holder.gRun.computer = new ComputerPlayer(holder.board, 2, false);
        holder.gRun.vsComputer = true;
      }
    });

    private class Actioner{

      public Actioner(WindowPane pane)
    }
  }
}