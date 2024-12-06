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
    private JTextField statusBar = new JTextField("Welcome to Sudoku!");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu difficultyMenu = new JMenu("Difficulty");
    private JMenu helpMenu = new JMenu("Help");

    // Constructor
    public SudokuMain() {
        initializeUI();
    }

    private void initializeUI() {
        // Container setup
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Tambahkan game board
        cp.add(board, BorderLayout.CENTER);

        // Tambahkan status bar di bagian bawah
        statusBar.setEditable(false);
        cp.add(statusBar, BorderLayout.SOUTH);

        // Atur menu bar
        setJMenuBar(createMenuBar());

        // Mulai permainan
        board.newGame();
        pack(); // Sesuaikan ukuran jendela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);
    }
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> {
            board.newGame();
            statusBar.setText("New game started!");
        });

        JMenuItem resetGameItem = new JMenuItem("Reset Game");
        resetGameItem.addActionListener(e -> {
            board.resetGame();
            statusBar.setText("Game reset!");
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newGameItem);
        fileMenu.add(resetGameItem);
        fileMenu.addSeparator(); // Tambahkan pemisah
        fileMenu.add(exitItem);

        // Options Menu
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem easyItem = new JMenuItem("Easy");
        easyItem.addActionListener(e -> {
            board.newGameWithDifficulty(10); // Contoh: 10 cell untuk ditebak
            statusBar.setText("Easy difficulty selected.");
        });

        JMenuItem intermediateItem = new JMenuItem("Intermediate");
        intermediateItem.addActionListener(e -> {
            board.newGameWithDifficulty(30); // Contoh: 30 cell untuk ditebak
            statusBar.setText("Intermediate difficulty selected.");
        });

        JMenuItem difficultItem = new JMenuItem("Difficult");
        difficultItem.addActionListener(e -> {
            board.newGameWithDifficulty(50); // Contoh: 50 cell untuk ditebak
            statusBar.setText("Difficult difficulty selected.");
        });

        optionsMenu.add(easyItem);
        optionsMenu.add(intermediateItem);
        optionsMenu.add(difficultItem);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Sudoku Game\nCreated by [Your Name]\nEnjoy the game!",
                "About Sudoku", JOptionPane.INFORMATION_MESSAGE));

        helpMenu.add(aboutItem);

        // Tambahkan menu ke menu bar
        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);

        return menuBar;
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