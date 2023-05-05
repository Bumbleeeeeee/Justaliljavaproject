import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Dimension;

import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class WelcomeWindow extends JPanel{
  
  JButton button1A;
  JButton button1B;
    JButton button2B0;
    JButton button2B1;
    JButton button2B2;
    JButton button2B3;

  JButton bORw_White;
  JButton bORw_Black;

  LiterallyJustASquare square;
  Border blackBorder = BorderFactory.createLineBorder(Color.black, 2, true);
  Border whiteBorder = BorderFactory.createLineBorder(Color.white, 2, true);


  //as the name implies this manages the welcome window, currently working :!sob: 
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
    button1A.setVisible(true); button1B.setVisible(true);
    
    button1A.setBounds(38,96,144,96); button1B.setBounds(202,96,144,96); //offset 10

    setDefaults(button1A, true, false); setDefaults(button1B, true, false);
    
    //

    button2B0 = new JButton("vs a literal baby [Internal: 0]");
    button2B1 = new JButton("vs Computer lvl1 [Internal: 1]");
    button2B2 = new JButton("vs Computer lvl2 [Internal: 3]");
    button2B3 = new JButton("vs Computer lvl3 [Internal: 4]");
    button2B0.setVisible(false); button2B1.setVisible(false); button2B2.setVisible(false); button2B3.setVisible(false);

    button2B0.setBounds(0,0,240,96); button2B1.setBounds(48,96,240,96); button2B2.setBounds(96,192,240,96); button2B3.setBounds(144,288,240,96);
    
    setDefaults(button2B0,false, false ); setDefaults(button2B1,false, false); setDefaults(button2B2,false, false); setDefaults(button2B3,false, false);
     
    //
    
    bORw_White = new JButton();
    bORw_Black = new JButton();
    bORw_White.setVisible(false); bORw_Black.setVisible(false);

    bORw_White.setBounds(10,96,144,192); bORw_Black.setBounds(230,96,144,192); //offset 10

    setDefaults(bORw_White, true, true); setDefaults(bORw_Black,false, true);
    



    this.add(button1A); this.add(button1B);
    this.add(button2B0); this.add(button2B1); this.add(button2B2); this.add(button2B3);
    this.add(bORw_White); this.add(bORw_Black);


    this.setVisible(true);
    
    //
    
    button1A.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e){
        
        JButton sourceButton = (JButton) e.getSource();
        
        MainWindow win = (MainWindow)SwingUtilities.getWindowAncestor(sourceButton);
        WindowPane pane = (WindowPane)win.getContentPane();
        pane.setPreferredSize(new Dimension(GameRunner.screenWidth, GameRunner.screenHeight + 13));
        win.pack();
        
        exitAndStart(sourceButton);
      }
    });

    //

    button1B.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e){
        button2B0.setVisible(true); button2B1.setVisible(true); button2B2.setVisible(true); button2B3.setVisible(true);
        button1A.setVisible(false); button1B.setVisible(false);
      }
    });
    ////////SUB1B////////

      button2B0.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e){
          holder.gRun.computer = new ComputerPlayer(holder.board, 0, false);
          holder.gRun.vsComputer = true;
          
          bMethod();
        }
      });
      //
      button2B1.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e){
          holder.gRun.computer = new ComputerPlayer(holder.board, 1, false);
          holder.gRun.vsComputer = true;

          bMethod();
        }
      });
      //   
      button2B2.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e){
          holder.gRun.computer = new ComputerPlayer(holder.board, 2, false);
          holder.gRun.vsComputer = true;

          bMethod();
        }
      });
      // 
      button2B3.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e){
          holder.gRun.computer = new ComputerPlayer(holder.board, 4, false);
          holder.gRun.vsComputer = true;

          bMethod();
        }
      });

    //COLOR//

    bORw_White.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e){
        
        ComputerPlayer computer = holder.gRun.computer;
        computer.setIsWhite(false);
        
        //Exits introframe
        JButton sourceButton = (JButton) e.getSource();
        exitAndStart(sourceButton);
      }
    });

    bORw_Black.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e){

        ComputerPlayer computer = holder.gRun.computer;
        computer.setIsWhite(true);
      
        //Exits introframe
        JButton sourceButton = (JButton) e.getSource();
        exitAndStart(sourceButton);
      }
    });
  }
  
  
  
  //////////HELPERS//////////


//manages rollover for JButtons
  private class ListenHelper implements MouseListener{

    boolean white;
    boolean isColorHelper;
    
    public ListenHelper(boolean white, boolean isColor){ this.white = white; isColorHelper = isColor;}
    
    
    public void mouseExited(MouseEvent e){
        JButton button = (JButton)e.getSource();
      
          helperhelper(button, white, isColorHelper);
    }

    public void mouseEntered(MouseEvent e){
      JButton button = (JButton)e.getSource();

      if(isColorHelper && white)
        button.setBorder(blackBorder);

      else if(isColorHelper && !white)
        button.setBorder(whiteBorder);
      
      else if(white){
        button.setBackground(Color.black); button.setForeground(Color.white);
        button.setBorder(whiteBorder);}
      
      else{
        button.setBackground(Color.white); button.setForeground(Color.black);
        button.setBorder(blackBorder);}
    }
    

    ////unused////
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}  
  }
  
  
  
  
  
  
  private void setDefaults(JButton button, boolean white, boolean isColor){
    
    button.setFocusPainted(false); 
    button.setRolloverEnabled(false);

    button.addMouseListener(new ListenHelper(white, isColor));
    helperhelper(button, white, isColor);
  }
  
  //
  
  private static void exitAndStart(JButton sourceButton){

    MainWindow win = (MainWindow)SwingUtilities.getWindowAncestor(sourceButton);
    WindowPane pane = (WindowPane)win.getContentPane();
        
    WelcomeWindow welcome = (WelcomeWindow)pane.getComponent(0);
    LiterallyJustASquare square = (LiterallyJustASquare)pane.getComponent(1);

    welcome.setVisible(false);
    square.clearWelcomeRectangle();

    holder.gRun.start();
  }


  //

  private void bMethod(){
    button2B0.setVisible(false);
    button2B1.setVisible(false);
    button2B2.setVisible(false);
    button2B3.setVisible(false);
          
    bORw_White.setVisible(true);
    bORw_Black.setVisible(true);
  }

  //helpers helper
  
  private void helperhelper(JButton button, boolean white, boolean isColor){
    //colorbuttons
    
    if(isColor && white){
      button.setBackground(Color.white); button.setBorder(whiteBorder);}

    else if(isColor && !white){
      button.setBackground(Color.black); button.setBorder(blackBorder);}

    //

    else if(white){
      button.setBackground(Color.white); button.setForeground(Color.black); button.setBorder(blackBorder);}

    else{
      button.setBackground(Color.black); button.setForeground(Color.white);button.setBorder(whiteBorder);}
  }
}