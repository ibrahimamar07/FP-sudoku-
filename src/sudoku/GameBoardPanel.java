/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group 15
 * 1 - 5026231195- ibrahim amar alfanani
 * 2 - 5026231219- ghifari rabbani A
 * 3 - 5026231180 - favian astama
 */




package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    // Define named constants for UI sizes
    public static final int CELL_SIZE = 60; // Cell width/height in pixels
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    // Define properties
    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle = new Puzzle();

    /** Constructor */
    public GameBoardPanel() {
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE)); // JPanel

        // Allocate the 2D array of Cell and add to JPanel
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);
            }
        }

        // Allocate a common listener as the ActionEvent listener for all Cells
        CellInputListener listener = new CellInputListener();

        // Adds this common listener to all editable cells
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].isEditable()) {
                    cells[row][col].addActionListener(listener);
                }
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    /** Generate a new puzzle and reset the game board of cells */
    public void newGame(int a) {
        puzzle.newPuzzle(a);

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }

    /** Return true if the puzzle is solved */
    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Listener Inner Class for all editable Cells */
    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get a reference to the JTextField that triggers this action event
            Cell sourceCell = (Cell) e.getSource();

            try {
                // Retrieve the int entered
                int numberIn = Integer.parseInt(sourceCell.getText());

                // For debugging
                System.out.println("You entered " + numberIn);

                // Check the numberIn against sourceCell.number
                if (numberIn == sourceCell.number) {
                    sourceCell.status = CellStatus.CORRECT_GUESS;
                } else {
                    sourceCell.status = CellStatus.WRONG_GUESS;
                }

                // Repaint the cell based on its status
                sourceCell.paint();

                // Check if the puzzle is solved
                if (isSolved()) {
                    JOptionPane.showMessageDialog(GameBoardPanel.this, "Congratulations! You solved the puzzle!");
                }
            } catch (NumberFormatException ex) {
                sourceCell.setText(""); // Clear invalid input
                JOptionPane.showMessageDialog(GameBoardPanel.this, "Invalid input. Please enter a number.");
            }
        }
    }

    public void resetGame() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }

}

