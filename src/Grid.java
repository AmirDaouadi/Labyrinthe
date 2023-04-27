public class Grid {
    private Square[][] squares;
    private int squareSize;
    private Thesee thesee = new Thesee();

    public Grid(int size) {
        this.squares = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.squares[i][j] = new Square(this, i, j);
            }
        }
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

    public Thesee getThesee() {
        return this.thesee;
    }
}