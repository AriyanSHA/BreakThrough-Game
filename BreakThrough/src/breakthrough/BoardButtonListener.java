package breakthrough;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardButtonListener implements ActionListener { //Explicit Implementation/ Listener Class
    private final BreakThroughGUI game;
    private final int row;
    private final int col;

    public BoardButtonListener(BreakThroughGUI game, int row, int col) {
        this.game = game;
        this.row = row;
        this.col = col;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!game.model.gameEnded) {
            JButton clickedButton = game.model.board[row][col];

            if ((game.model.currentPlayer == 1 && clickedButton.getBackground().equals(Color.BLACK)) ||
                (game.model.currentPlayer == 2 && clickedButton.getBackground().equals(Color.GREEN))) {

                int targetRow = Integer.MIN_VALUE;
                int targetCol = Integer.MIN_VALUE;

                int choice = JOptionPane.showOptionDialog(
                        game,
                        "Choose a direction:",
                        "Move Options",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"Forward-left", "Forward", "Forward-Right"},
                        "Forward");

                switch (choice) {
                    case 0 -> {
                        targetRow = row + (game.model.currentPlayer == 1 ? -1 : 1);
                        targetCol = col - 1;
                    }
                    case 1 -> {
                        targetRow = row + (game.model.currentPlayer == 1 ? -1 : 1);
                        targetCol = col;
                    }
                    case 2 -> {
                        targetRow = row + (game.model.currentPlayer == 1 ? -1 : 1);
                        targetCol = col + 1;
                    }
                }
                if (isValidMove(targetRow, targetCol) && (!game.model.board[targetRow][targetCol].getBackground().equals(Color.GREEN) || !game.model.board[targetRow][targetCol].getBackground().equals(Color.BLACK))) {
                    movePiece(row, col, targetRow, targetCol);
                    checkForVictory();
                    switchPlayers();
                }
            }
        }
    }

    private boolean isValidMove(int targetRow, int targetCol) {
        return targetRow >= 0 && targetRow < game.model.boardSize && targetCol >= 0 && targetCol < game.model.boardSize;
    }

    private void movePiece(int sourceRow, int sourceCol, int targetRow, int targetCol) {
        game.model.board[targetRow][targetCol].setBackground(game.model.board[sourceRow][sourceCol].getBackground());
        game.model.board[sourceRow][sourceCol].setBackground(Color.WHITE);
    }

    private void checkForVictory() {
        for (int col = 0; col < game.model.boardSize; col++) {
            if (game.model.board[game.model.boardSize - 1][col].getBackground().equals(Color.GREEN)) {
                game.model.gameEnded = true;
                JOptionPane.showMessageDialog(null, "Green is victorious!");
                game.resetGame();
                break;
            }
        }

        for (int col = 0; col < game.model.boardSize; col++) {
            if (game.model.board[0][col].getBackground().equals(Color.BLACK)) {
                game.model.gameEnded = true;
                JOptionPane.showMessageDialog(null, "Black is victorious!");
                game.resetGame();
                break;
            }
        }
    }

    private void switchPlayers() {
        game.model.currentPlayer = (game.model.currentPlayer == 1) ? 2 : 1;
    }
}
