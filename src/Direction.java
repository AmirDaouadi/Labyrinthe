/**
 * Enum for the direction of Thésée's movement.
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public enum Direction {
    /**
     * Up direction
     */
    UP,
    
    /**
     * Down direction
     */
    DOWN,

    /**
     * Left direction
     */
    LEFT,

    /**
     * Right direction
     */
    RIGHT;

    /**
     * Get the index to add to the row of the current square to get the row of the next square.
     * @return The index to add to the row
     */
    public int row() {
        return switch (this) {
            case UP -> -1;
            case DOWN -> 1;
            default -> 0;
        };
    }

    /**
     * Get the index to add to the column of the current square to get the column of the next square.
     * @return The index to add to the column
     */
    public int column() {
        return switch (this) {
            case LEFT -> -1;
            case RIGHT -> 1;
            default -> 0;
        };
    }

    /**
     * Get the opposite direction
     * @return The opposite direction
     */
    public Direction opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
