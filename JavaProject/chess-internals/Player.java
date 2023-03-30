//pretty sure this is depricated

import java.util.Scanner;
class Player {

  Scanner scan = new Scanner(System.in);

  public Player() {};
  
  public int[][] getMove() {
        System.out.println("What are the coordinates of the piece you would like to move? (enter row number first, column number second)");
    int a = scan.nextInt(), b = scan.nextInt();
    System.out.println("What are the coordinates of the square you would like to move it to? (enter row number first, column number second)");
    int c = scan.nextInt(), d = scan.nextInt();
    return (new int[][]{{a, b}, {c, d}});
  } 
}
  