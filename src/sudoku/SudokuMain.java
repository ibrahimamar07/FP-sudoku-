package sudoku;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
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
    Puzzle puzzle=new Puzzle();

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
        //default pas mulai pertama adalah level easy tolong jangan dirubah yaaa
        board.newGame(10);
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
            board.newGame(10);
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
        fileMenu.addSeparator(); //  pemisah
        fileMenu.add(exitItem);

        // Options Menu
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem easyItem = new JMenuItem("Easy");
        easyItem.addActionListener(e -> {
            board.newGame(10);
            statusBar.setText("Easy difficulty selected.");
        });

        JMenuItem intermediateItem = new JMenuItem("Intermediate");
        intermediateItem.addActionListener(e -> {
            statusBar.setText("Intermediate difficulty selected.");
            board.newGame(20);
        });

        JMenuItem difficultItem = new JMenuItem("Difficult");
        difficultItem.addActionListener(e -> {
            statusBar.setText("Difficult difficulty selected.");
            board.newGame(50);
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