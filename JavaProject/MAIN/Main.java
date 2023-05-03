import javax.swing.JLayeredPane;

class Main {
  public static void main(String[] args) {
  
    holder.board = new Board();
    holder.cListen = new ClickListener();
    
    
    MainWindow window = new MainWindow();
    window.setIconImage(Piece.getPieceIcon("K", false));

    WindowPane pane = new WindowPane();

    window.setContentPane(pane);


    LiterallyJustASquare square = new LiterallyJustASquare();
    pane.add(square, JLayeredPane.PALETTE_LAYER);
    square.repaint();

    holder.gRun = new GameRunner();
    pane.add(holder.gRun, JLayeredPane.DEFAULT_LAYER);
    
    WelcomeWindow welcome = new WelcomeWindow();
    pane.add(welcome,JLayeredPane.POPUP_LAYER);

    window.setVisible(true);
    window.pack();

    /* 
    JFrame frame = new Frame();
    frame.setDefaultCloseOperation(JFrame_EXIT_ON_CLOSE);

    JPanel panel = new Panel();
    panel.setBounds(x,y,width,height);
    panel.setVisible(true);

    frame.setVisible(true);
    frame.add(panel);
    frame.pack();
    */
    
    }
  }