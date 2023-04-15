class Main {
  public static void main(String[] args) {
  
    holder.board = new Board();
    holder.cListen = new ClickListener();
    holder.gRun = new GameRunner();
    
    MainWindow window = new MainWindow();

    WelcomeWindow welcome = new WelcomeWindow();
    window.add(welcome,0);

    //holder.gRun.start();
    }
  }