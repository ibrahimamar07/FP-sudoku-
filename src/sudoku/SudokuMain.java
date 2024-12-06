package sudoku;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
///**
// * The main Sudoku program
// */
//public class SudokuMain extends JFrame {
//    private static final long serialVersionUID = 1L;  // to prevent serial warning
//
//    // private variables
//    GameBoardPanel board = new GameBoardPanel();
//    JButton btnNewGame = new JButton("New Game");
//
//    // Constructor
//    public SudokuMain() {
//        Container cp = getContentPane();
//        cp.setLayout(new BorderLayout());
//
//        cp.add(board, BorderLayout.CENTER);
//
//        // Add a button to the south to re-start the game via board.newGame()
//        // ......
//
//        // Initialize the game board to start the game
//        board.newGame();
//
//        pack();     // Pack the UI components, instead of using setSize()
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
//        setTitle("Sudoku");
//        setVisible(true);
//    }
//
//    /** The entry main() entry method */
//    public static void main(String[] args) {
//        // [TODO 1] Check "Swing program template" on how to run
//        //  the constructor of "SudokuMain"
//        // .........
//    }
//}




//package sudoku;
public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // private variables
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");

    // Constructor
    public SudokuMain() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Add the game board to the center of the layout
        cp.add(board, BorderLayout.CENTER);

        // Add a button to the south to re-start the game
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(btnNewGame);
        cp.add(controlPanel, BorderLayout.SOUTH);

        // Attach action listener to "New Game" button
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.newGame(); // Start a new game
            }
        });

        // Initialize the game board to start the game
        board.newGame();

        pack(); // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to handle window-closing
        setTitle("Sudoku");
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // Run the constructor of "SudokuMain" on the Event-Dispatching Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuMain(); // Create and show the Sudoku main window
            }
        });
    }
}