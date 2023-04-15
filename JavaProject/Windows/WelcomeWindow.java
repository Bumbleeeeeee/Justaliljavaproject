import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Window;
import java.awt.Component;

public class WelcomeWindow extends JPanel{

  JButton button1A;
  JButton button1B;
    JButton button2B1;
    JButton button2B2;


  //as the name implies this manages the welcome window, currently unworking :sob: 
  public WelcomeWindow(){

    this.setPreferredSize(new Dimension(GameRunner.screenWidth / 2,GameRunner.screenHeight / 2));
    this.setLayout(null);
    this.setBackground(Color.BLUE);
    this.setDoubleBuffered(true);

    button1A = new JButton("RAWR");
    button1B = new JButton("AAAAAAAAAAAA");

    button1A.setBounds(0,0,GameRunner.screenWidth / 2, GameRunner.screenHeight); button1B.setBounds(GameRunner.screenWidth / 2,0,GameRunner.screenWidth / 2, GameRunner.screenHeight / 2);

    this.setVisible(true);

    this.add(button1A);
    this.add(button1B);

    button1A.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent e){
        
        System.out.println("IT WAS A :O");

        JComponent comp = (JComponent) e.getSource();
        System.out.println("disposed");
        JFrame window = (JFrame)SwingUtilities.getRoot(comp);
        JRootPane groot = window.getRootPane();
        
        Component[] allComponents = groot.getComponents();
        JPanel dobop = (JPanel)allComponents[0];
        System.out.println(dobop);
        dobop.setVisible(false);

        holder.gRun.start();
      }
    });

    
  }
}