/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #1
 * 1 - 5026231195- ibrahim amar alfanani
 * 2 - 5026231219- ghifari rabbani A
 * 3 - 5026231180 - favian astama
 */



package sudoku;
import java.util.*;

public class Puzzle {
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    public Puzzle() {
        super();
    }

    /**
     * Generate a new puzzle given the number of cells to be guessed.
     * This method updates the arrays `numbers` and `isGiven`.
     *
     * @param cellsToGuess jumlah sel yang harus ditebak.
     */
    public void newPuzzle(int cellsToGuess) {
        // Generate angka-angka awal secara acak
        generateSolvedPuzzle();

        // Tentukan petunjuk berdasarkan jumlah sel yang harus ditebak
        Random rand = new Random();
        int totalCells = SudokuConstants.GRID_SIZE * SudokuConstants.GRID_SIZE;
        int clues = totalCells - cellsToGuess;

        // Tandai semua sebagai "not given" awalnya
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                isGiven[row][col] = false;
            }
        }

        // Pilih "clues" secara acak untuk ditandai sebagai "given"
        Set<Integer> givenPositions = new HashSet<>();
        while (givenPositions.size() < clues) {
            int position = rand.nextInt(totalCells);
            givenPositions.add(position);
        }

        for (int position : givenPositions) {
            int row = position / SudokuConstants.GRID_SIZE;
            int col = position % SudokuConstants.GRID_SIZE;
            isGiven[row][col] = true;
        }
    }

    /**
     * Generate a solved Sudoku puzzle using randomized backtracking.
     */
    private boolean generateSolvedPuzzle() {
        // Reset grid numbers
        for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
            Arrays.fill(numbers[i], 0);
        }
        return solve(0, 0);
    }

    /**
     * Solve the Sudoku puzzle using backtracking.
     */
    private boolean solve(int row, int col) {
        // Jika kolom melebihi ukuran grid, lanjutkan ke baris berikutnya
        if (col == SudokuConstants.GRID_SIZE) {
            col = 0;
            row++;
            // Jika baris juga melebihi, berarti seluruh grid sudah terisi
            if (row == SudokuConstants.GRID_SIZE) {
                return true;
            }
        }

        // Jika sel sudah terisi (angka yang diberikan), lewati
        if (numbers[row][col] != 0) {
            return solve(row, col + 1);
        }

        // Coba angka secara acak
        List<Integer> randomNumbers = generateRandomNumbers();
        for (int num : randomNumbers) {
            if (isSafe(row, col, num)) {
                numbers[row][col] = num;
                if (solve(row, col + 1)) {
                    return true;
                }
                // Jika gagal, reset dan coba angka berikutnya
                numbers[row][col] = 0;
            }
        }

        return false; // Jika tidak ada angka yang bisa ditempatkan, kembali
    }

    /**
     * Periksa apakah angka aman untuk ditempatkan pada posisi tertentu
     */
    private boolean isSafe(int row, int col, int num) {
        // Periksa baris
        for (int c = 0; c < SudokuConstants.GRID_SIZE; c++) {
            if (numbers[row][c] == num) {
                return false;
            }
        }

        // Periksa kolom
        for (int r = 0; r < SudokuConstants.GRID_SIZE; r++) {
            if (numbers[r][col] == num) {
                return false;
            }
        }

        // Periksa subgrid 3x3
        int startRow = row - row % SudokuConstants.SUBGRID_SIZE;
        int startCol = col - col % SudokuConstants.SUBGRID_SIZE;
        for (int r = startRow; r < startRow + SudokuConstants.SUBGRID_SIZE; r++) {
            for (int c = startCol; c < startCol + SudokuConstants.SUBGRID_SIZE; c++) {
                if (numbers[r][c] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Generate a list of numbers from 1 to GRID_SIZE in random order.
     */
    private List<Integer> generateRandomNumbers() {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 1; i <= SudokuConstants.GRID_SIZE; i++) {
            randomNumbers.add(i);
        }
        Collections.shuffle(randomNumbers);
        return randomNumbers;
    }

}
