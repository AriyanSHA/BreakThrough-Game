package breakthrough;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BreakThroughGUI extends JFrame {  //View Class

    public final BreakThroughModel model;

    public BreakThroughGUI(int boardSize) {
        this.model = new BreakThroughModel(boardSize);
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Break-Through Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(model.boardSize, model.boardSize));

        initializeBoard();
        createMenuBar();

        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < model.boardSize; i++) {
            for (int j = 0; j < model.boardSize; j++) {
                model.board[i][j] = new JButton();
                if (i == 0 || i == 1) {
                    if ((i + j) % 2 != 0) {
                        model.board[i][j].setBackground(Color.GREEN);
                    } else {
                        model.board[i][j].setBackground(Color.WHITE);
                    }
                } else if (i == model.boardSize - 2 || i == model.boardSize - 1) {
                    if ((i + j) % 2 == 0) {
                        model.board[i][j].setBackground(Color.BLACK);
                    } else {
                        model.board[i][j].setBackground(Color.WHITE);
                    }
                } else {
                    model.board[i][j].setBackground(Color.WHITE);
                }
                //addActionListener -> Register
                model.board[i][j].addActionListener(new BoardButtonListener(this, i, j)); //BUTTON SOURCE
                add(model.board[i][j]);
            }
        }
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener((ActionEvent e) -> {
            resetGame();
        });
        gameMenu.add(newGameItem);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener((ActionEvent e) -> {
            exitApplication();
        });
        gameMenu.add(exitItem);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
    }

    private void exitApplication() {
        int option = JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


    public void resetGame() {
        getContentPane().removeAll();

        initializeBoard();

        model.currentPlayer = 1;
        model.gameEnded = false;
    }
}
