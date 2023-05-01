/**
 * Represents Thésée in the labyrinth.
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class Thesee {
    /**
     * The square where Thésée is.
     */
    private Square square;

    /**
     * The initial square where Thésée is.
     */
    private Square intialSquare;

    /**
     * Moves Thésée to the given square.
     * @param square The square to move Thesee to
     * @throws Exception If the given square is a wall
     */
    public void setSquare(Square square) throws Exception {
        setSquare(square, false);
    }

    /**
     * Moves Thésée to the given square.
     * @param square The square to move Thesee to
     * @param initialPosition Whether the square is the initial position
     * @throws Exception If the given square is a wall
     */
    public void setSquare(Square square, boolean initialPosition) throws Exception {
        if (square.isExit()) throw new Exception("Vous ne pouvez pas placer Thésée sur la même case que la sortie. Déplacez d'abord la sortie puis réessayez.");
        square.setEmpty();
        this.square = square;
        if (initialPosition) {
            this.intialSquare = this.square;
        } else {
            if (this.intialSquare == null) this.intialSquare = this.square;
        }
    }

    /**
     * Get the square where Thésée is.
     * @return The square where Thésée is
     */
    public Square getSquare() {
        return this.square;
    }

    /**
     * Get a square next to Thésée.
     * @param direction The direction to get the square from
     * @return The requested square
     * @throws Exception If the position is out of grid's bounds
     */
    public Square getSquare(Direction direction) throws Exception {
        return this.square.getGrid().getSquare(this.square.getRow() + direction.row(), this.square.getColumn() + direction.column());
    }

    /**
     * Resets Thésée's position to the initial square/
     */
    public void reset() {
        this.square = this.intialSquare;
    }
}
