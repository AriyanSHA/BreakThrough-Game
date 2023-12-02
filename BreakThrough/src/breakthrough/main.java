package breakthrough;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter board size (6, 8, or 10):");
        int boardSize = Integer.parseInt(input);

        if (boardSize != 6 && boardSize != 8 && boardSize != 10) {
            JOptionPane.showMessageDialog(null, "Invalid board size. Please enter 6, 8, or 10.");
            System.exit(0);
        }

        SwingUtilities.invokeLater(() -> new BreakThroughGUI(boardSize));
    }
}
