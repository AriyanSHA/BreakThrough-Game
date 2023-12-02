
package breakthrough;

import javax.swing.JButton;

public class BreakThroughModel {
    public final int boardSize;
    public final JButton[][] board;
    public int currentPlayer;
    public boolean gameEnded;

    public BreakThroughModel(int boardSize) {
        this.boardSize = boardSize;
        this.board = new JButton[boardSize][boardSize];
        this.currentPlayer = 1;
        this.gameEnded = false;
    }
}
