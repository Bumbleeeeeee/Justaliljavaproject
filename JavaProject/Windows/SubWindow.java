import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
//for helper
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubWindow extends JPanel{

  static boolean subWindowExists = false;

  JButton optionA;
  JButton optionB;
  JButton optionC;
  JButton optionD;
  
  Piece piece;
  int locationX;
  int locationY;
  
  MainWindow window;


  public SubWindow(Piece piece, int locationY, int locationX, MainWindow window){
    
    this.piece = piece;
    this.locationY = locationY;
    this.locationX = locationX;

    this.window = window;
    
    subWindowExists = true;
    System.out.println("yipeeeeeeeeeeeeee");

    this.setLocation(0,0);
    
    this.setSize(384,105);
    
    this.setLayout(null);
    
    //delete if JPanel
    //this.setUndecorated(true);
    
    optionA = new JButton(getQueenSprite());
    optionB = new JButton(getBishopSprite());
    optionC = new JButton(getKnightSprite());
    optionD = new JButton(getRookSprite());

  
    optionA.setFocusable(false); optionB.setFocusable(false); optionC.setFocusable(false); optionD.setFocusable(false);
    
    optionA.setBounds(0,0,96,105); optionB.setBounds(96,0,96,105); optionC.setBounds(192,0,96,105); optionD.setBounds(288,0,96,105);

    optionA.setFocusPainted(false); optionB.setFocusPainted(false); optionC.setFocusPainted(false); optionD.setFocusPainted(false);
    
    
  if(piece.w){
optionA.setBackground(Color.black); optionB.setBackground(Color.black); optionC.setBackground(Color.black); optionD.setBackground(Color.black);}
    else{
    optionA.setBackground(Color.white); optionB.setBackground(Color.white); optionC.setBackground(Color.white); optionD.setBackground(Color.white);}
      
    
    this.add(optionA);
    this.add(optionB);
    this.add(optionC);
    this.add(optionD);

    
    //add if Jpanel
    JLayeredPane.putLayer(this, 5);

    this.setVisible(true);
    
    optionA.addActionListener(new ActionHandler("Q", this));
    optionB.addActionListener(new ActionHandler("B", this));
    optionC.addActionListener(new ActionHandler("N", this));
    optionD.addActionListener(new ActionHandler("R", this));

    holder.gRun.update();
    holder.gRun.repaint();
  }

  private class ActionHandler implements ActionListener{

  JPanel subwindow;
  String id;
    
    public ActionHandler(String id, SubWindow subwindow){
      this.id = id; this.subwindow = subwindow;
    }
    
    public void actionPerformed(ActionEvent event){
      System.out.println("button was pushed!!!!!");

      Board.board[locationY][locationX] = null;  
        
      Board.board[locationY][locationX] = new Piece(id, piece.isWhite());

      JButton button = (JButton)event.getSource();
      MainWindow window = (MainWindow)SwingUtilities.getWindowAncestor(button);
      WindowPane pane = (WindowPane)window.getContentPane();
      
      subWindowExists = false;
      holder.gRun.update();
      holder.gRun.repaint();
      pane.remove(subwindow);
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
