import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.BorderLayout;
//for helper
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubWindow extends JPanel{

  static boolean subWindowExists = false;


  Piece piece;
  int locationX;
  int locationY;
  
  MainWindow window;


  public SubWindow(Piece piece, int locationY, int locationX, MainWindow window){
    this.piece = piece;
    this.locationY = locationY;
    this.locationX = locationX;

    this.window = window;
  }
  
  
  public void constructSubWindow(){
    
    subWindowExists = true;
    System.out.println("yipeeeeeeeeeeeeee");
    window.add(this,BorderLayout.SOUTH);

    this.setLocation(0,0);
    
    this.setSize(384,105);
    
    this.setLayout(null);
    
    JButton optionA = new JButton(getQueenSprite());
    JButton optionB = new JButton(getBishopSprite());
    JButton optionC = new JButton(getKnightSprite());
    JButton optionD = new JButton(getRookSprite());

    optionA.setFocusable(false); optionB.setFocusable(false); optionC.setFocusable(false); optionD.setFocusable(false);
    
    optionA.setBounds(0,0,96,105); optionB.setBounds(96,0,96,105); optionC.setBounds(192,0,96,105); optionD.setBounds(288,0,96,105);

    optionA.setFocusPainted(false); optionB.setFocusPainted(false); optionC.setFocusPainted(false); optionD.setFocusPainted(false);
    
    
  //if(piece.w){
//optionA.setBackground(Color.black); optionB.setBackground(Color.black); optionC.setBackground(Color.black); optionD.setBackground(Color.black);}
    //else{
    //optionA.setBackground(Color.white); optionB.setBackground(Color.white); optionC.setBackground(Color.white); optionD.setBackground(Color.white);}
      
    
    this.add(optionA);
    this.add(optionB);
    this.add(optionC);
    this.add(optionD);

    this.setVisible(true);

    /*ActionHandler handlerA = new ActionHandler();
    
    handlerA.optionA = optionA;
    handlerA.optionB = optionB;
    handlerA.optionC = optionC;
    handlerA.optionD = optionD;*/
    
    
    
    
    
    optionA.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        
        System.out.println("IT WAS A :O");
        
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("Q", piece.isWhite());

        subWindowExists = false;

        JComponent comp = (JComponent) e.getSource();
        System.out.println("disposed");
        SwingUtilities.getWindowAncestor(comp).dispose();
      }
    });

    optionB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        
        System.out.println("IT WAS B :O");
        
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("B", piece.isWhite());

        subWindowExists = false;

        JComponent comp = (JComponent) e.getSource();
        System.out.println("disposed");
        SwingUtilities.getWindowAncestor(comp).dispose();
      }
    });

    optionC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        
        System.out.println("IT WAS C :O");
        
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("N", piece.isWhite());

        subWindowExists = false;

        JComponent comp = (JComponent) e.getSource();
        System.out.println("disposed");
        SwingUtilities.getWindowAncestor(comp).dispose();
      }
    });

    optionD.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e){
        
        System.out.println("IT WAS D :O");
        
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("R", piece.isWhite());

        subWindowExists = false;

        JComponent comp = (JComponent) e.getSource();
        System.out.println("disposed");
        SwingUtilities.getWindowAncestor(comp).dispose();
      }
    });

  }
  
  /*private class ActionHandler implements ActionListener{
    
  JButton optionA;
  JButton optionB;
  JButton optionC;
  JButton optionD;

  public ActionHandler(){
    System.out.println("aHandler made");
  }
    
    public void actionPerformed(ActionEvent event){
      System.out.println("button was pushed!!!!!");

      if(event.getSource() == optionA){
        System.out.println("IT WAS A :O");
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("Q", piece.isWhite());
      }
      
      if(event.getSource() == optionB){
        System.out.println("IT WAS B :O");
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("B", piece.isWhite());
        //JComponent comp = (JComponent) event.getSource();
        //System.out.println("disposed");
        //SwingUtilities.getWindowAncestor(comp).dispose();
      }
          
      if(event.getSource() == optionC){
        System.out.println("IT WAS C :O");
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("N", piece.isWhite());
        //JComponent comp = (JComponent) event.getSource();
        //System.out.println("disposed");
        //SwingUtilities.getWindowAncestor(comp).dispose();
      }
      
      if(event.getSource() == optionD){
        System.out.println("IT WAS D :O");
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("R", piece.isWhite());
        //JComponent comp = (JComponent) event.getSource();
        //System.out.println("disposed");
        //SwingUtilities.getWindowAncestor(comp).dispose();
      }

      subWindowExists = false;

      JComponent comp = (JComponent) event.getSource();
        System.out.println("disposed");
        SwingUtilities.getWindowAncestor(comp).dispose();
      
    }
  }*/

  public ImageIcon getQueenSprite(){
    BufferedImage image = null; 
    
    try{
        if(piece.w)    
          image = ImageIO.read(new File("JavaProject/images/queen_WHITE.png"));
        else
          image = ImageIO.read(new File("JavaProject/images/queen_BLACK.png"));}
      
    catch(IOException e){
        e.printStackTrace();}
    return new ImageIcon(image);
  }

  public ImageIcon getRookSprite(){
    BufferedImage image = null; 
    
    try{
        if(piece.w)    
          image = ImageIO.read(new File("JavaProject/images/rook_WHITE.png"));
        else
          image = ImageIO.read(new File("JavaProject/images/rook_BLACK.png"));}
      
    catch(IOException e){
        e.printStackTrace();}
    return new ImageIcon(image);
  }

  public ImageIcon getKnightSprite(){
    BufferedImage image = null; 
    
    try{
        if(piece.w)    
          image = ImageIO.read(new File("JavaProject/images/knight_WHITE.png"));
        else
          image = ImageIO.read(new File("JavaProject/images/knight_BLACK.png"));}
      
    catch(IOException e){
        e.printStackTrace();}
    
    return new ImageIcon(image);
  }

  public ImageIcon getBishopSprite(){
    BufferedImage image = null; 
    
    try{
        if(piece.w)    
          image = ImageIO.read(new File("JavaProject/images/bishop_WHITE.png"));
        else
          image = ImageIO.read(new File("JavaProject/images/bishop_BLACK.png"));}
      
    catch(IOException e){
        e.printStackTrace();}
    
    return new ImageIcon(image);
  }
}
