import java.awt.*;

public class PeiceManager {
    public static Piece[][] board;

    public static void DrawPieces(Graphics2D g2){
        board = holder.board.getBoard();
        PanelManager pManage = holder.pManage;

        int curX = 0;
        int curY = 0;

        for(Piece[] arr : board){
            for(Piece cur : arr){
                g2.drawImage(cur.getImage(), curX, curY, pManage.tileSize, pManage.tileSize, null);
                curX += pManage.tileSize;
            }
            curY += pManage.tileSize;
            curX = 0;
        }
    }
}

