import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

//for helper
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubWindow extends JFrame{

  JFrame subWindow;

  Piece piece;
  int locationX;
  int locationY;

  public SubWindow(Piece piece, int locationY, int locationX){
    this.piece = piece;
    this.locationY = locationY;
    this.locationX = locationX;
  }
  
  
  public void constructSubWindow(){
    System.out.println("yipeeeeeeeeeeeeee");
    subWindow = new JFrame();
    subWindow.setTitle("Promote Pawn!");
    subWindow.setLocation(0,0);
    subWindow.setLocationRelativeTo(MainWindow.window);
    
    subWindow.setSize(400,105);
    subWindow.setResizable(false);
    
    subWindow.setUndecorated(true);
    
    subWindow.setVisible(true);

    subWindow.setBackground(Color.black);

    subWindow.addMouseListener(holder.cListen);

    subWindow.setLayout(null);
    
    JButton optionA = new JButton(getQueenSprite());
    JButton optionB = new JButton(getBishopSprite());
    JButton optionC = new JButton(getKnightSprite());
    JButton optionD = new JButton(getRookSprite());
    
    optionA.setBounds(0,0,100,105); optionB.setBounds(100,0,100,105); optionC.setBounds(200,0,100,105); optionD.setBounds(300,0,100,105);

    optionA.setFocusPainted(false); optionB.setFocusPainted(false); optionC.setFocusPainted(false); optionD.setFocusPainted(false);
    
    
    if(piece.w){
optionA.setBackground(Color.black); optionB.setBackground(Color.black); optionC.setBackground(Color.black); optionD.setBackground(Color.black);}
    else{
    optionA.setBackground(Color.white); optionB.setBackground(Color.white); optionC.setBackground(Color.white); optionD.setBackground(Color.white);}
      
    
    subWindow.add(optionA);
    subWindow.add(optionB);
    subWindow.add(optionC);
    subWindow.add(optionD);

    ActionHandler handlerA = new ActionHandler();
    
    handlerA.optionA = optionA;
    handlerA.optionB = optionB;
    handlerA.optionC = optionC;
    handlerA.optionD = optionD;
    
    optionA.addActionListener(handlerA);
    optionB.addActionListener(handlerA);
    optionC.addActionListener(handlerA);
    optionD.addActionListener(handlerA);
  }
  
  private class ActionHandler implements ActionListener{
    
  JButton optionA;
  JButton optionB;
  JButton optionC;
  JButton optionD;
    
    public void actionPerformed(ActionEvent event){
      System.out.println("button was pushed!!!!!");

      if(event.getSource() == optionA){
        System.out.println("IT WAS A :O");
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("Q", piece.isWhite());
        subWindow.dispose();
      }
      
      if(event.getSource() == optionB){
        System.out.println("IT WAS B :O");
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("B", piece.isWhite());
        subWindow.dispose();
      }
          
      if(event.getSource() == optionC){
        System.out.println("IT WAS C :O");
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("N", piece.isWhite());
        subWindow.dispose();
      }
      
      if(event.getSource() == optionD){
        System.out.println("IT WAS D :O");
        Board.board[locationY][locationX] = null;
        Board.board[locationY][locationX] = new Piece("R", piece.isWhite());
        subWindow.dispose();
      }
      dispose();
    }
  }

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
