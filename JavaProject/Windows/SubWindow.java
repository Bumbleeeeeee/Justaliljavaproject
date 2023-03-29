import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;

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
    
    subWindow.setSize(600,200);
    subWindow.setResizable(false);
    subWindow.setVisible(true);

    subWindow.setBackground(Color.black);

    subWindow.addMouseListener(holder.cListen);

    subWindow.setLayout(null);
    
    JButton optionA = new JButton("butonny");
    JButton optionB = new JButton("its a button, but 2");
    JButton optionC = new JButton("a pirates favorite letter");
    JButton optionD = new JButton("OMG its a button again, what a coincidence");
    optionA.setBounds(0,0,150,200); optionB.setBounds(150,0,150, 200); optionC.setBounds(300,0,150,200); optionD.setBounds(450,0,150, 200); 
    
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
    }
  }
}