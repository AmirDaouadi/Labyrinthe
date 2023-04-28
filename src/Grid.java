import java.io.File;
import java.util.Stack;

/**
 * This class is used to store the grid's data
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class Grid {
    /**
     * The grid's squares
     */
    private Square[][] squares;

    /**
     * Thésée
     */
    private Thesee thesee = new Thesee();

    /**
     * The grid's name
     */
    private File file;

    /**
     * Constructor
     * @param size The size of the grid (number of squares on a row/column)
     */
    public Grid(int size) {
        createGrid(size, new File("Sans titre"));
    }

    /**
     * Constructor
     * @param size The size of the grid (number of squares on a row/column)
     * @param file The name of the grid
     */
    public Grid(int size, File file) {
        createGrid(size, file);
    }

    /**
     * Create the grid
     * @param size The size of the grid (number of squares on a row/column)
     * @param file The name of the grid
     */
    private void createGrid(int size, File file) {
        this.file = file;
        this.squares = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.squares[i][j] = new Square(this, i, j);
            }
        }
    }


    /**
     * Empty the grid
     * Removes the exit, Thésée and walls
     * @see Square#setEmpty()
     */
    public void empty() {
        this.thesee = new Thesee();
        for (Square[] row : this.squares) {
            for (Square square : row) {
                square.setEmpty();
            }
        }
    }

    /**
     * Get the grid's name
     * @return The grid's name
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Set the grid's name
     * @param file The grid's name
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Get the grid's size (number of squares on a row/column)
     * @return The grid's size
     */
    public int getSize() {
        return this.squares.length;
    }

    /**
     * Get a square from the grid
     * @param row The row of the square
     * @param column The column of the square
     * @return The square at the given position
     * @throws Exception If the position is out of grid's bounds
     */
    public Square getSquare(int row, int column) throws Exception {
        try {
            return this.squares[row][column];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("No square found at position (" + row + ", " + column + ")");
        }
    }

    /**
     * Get Thésée
     * @return Thésée
     */
    public Thesee getThesee() {
        return this.thesee;
    }

    /**
     * Checks if all the accessible squares have been visited
     * @return true if all the accessible squares have been visited, false otherwise
     */
    public boolean isEnded() {
        for (Square[] row : this.squares) {
            for (Square square : row) {
                if (square.isAccessible() && !square.isVisited()) return false;
            }
        }
        return true;
    }

    /**
     * Resets Thésée's position and all the squares' visited and accessible status
     */
    public void reset() {
        this.thesee.reset();
        for (Square[] row : this.squares) {
            for (Square square : row) {
                square.setVisited(false);
            }
        }
    }

    /**
     * Get the exit square
     * @return The exit square
     */
    public Square getExit() {
        for (Square[] row : this.squares) {
            for (Square square : row) {
                if (square.isExit()) return square;
            }
        }
        return null;
    }

    /**
     * Validates the grid
     * @return true if the grid can be solved, false otherwise
     */
    public boolean validate() {
        reset();
        Square theseeSquare = this.thesee.getSquare();
        if (theseeSquare == null) return false;

        Square exitSquare = getExit();
        if (exitSquare == null) return false;

        if (theseeSquare == exitSquare) return false;

        discover(theseeSquare);

        return exitSquare.isAccessible();
    }

    /**
     * Discovers all the accessible squares from a given square
     * @param square The square to start from
     */
    private void discover(Square square) {
        Stack<Square> discoveredSquares = new Stack<>();
        discoveredSquares.push(square);
        while (!discoveredSquares.isEmpty()) {
            Square discoveredSquare = discoveredSquares.pop();

            discoveredSquare.setAccessible(true);
            try {
                Square upSquare = getSquare(discoveredSquare.getRow() - 1, discoveredSquare.getColumn());
                if (!upSquare.isWall() && !upSquare.isAccessible()) discoveredSquares.push(upSquare);
            } catch (Exception ignored) {}

            try {
                Square downSquare = getSquare(discoveredSquare.getRow() + 1, discoveredSquare.getColumn());
                if (!downSquare.isWall() && !downSquare.isAccessible()) discoveredSquares.push(downSquare);
            } catch (Exception ignored) {}

            try {
                Square leftSquare = getSquare(discoveredSquare.getRow(), discoveredSquare.getColumn() - 1);
                if (!leftSquare.isWall() && !leftSquare.isAccessible()) discoveredSquares.push(leftSquare);
            } catch (Exception ignored) {}

            try {
                Square rightSquare = getSquare(discoveredSquare.getRow(), discoveredSquare.getColumn() + 1);
                if (!rightSquare.isWall() && !rightSquare.isAccessible()) discoveredSquares.push(rightSquare);

            } catch (Exception ignored) {}
        }
    }
}