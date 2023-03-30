
class Main {
  public static void main(String[] args) {
  
    holder.gRun = new GameRunner();
    holder.board = new Board();
    holder.cListen = new ClickListener();
    
    MainWindow window = new MainWindow();
    
    GameRunner gRun = holder.gRun;
    gRun.start();
    }
  }