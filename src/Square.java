/**
 * Represents a square in the grid
 * @see Grid
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class Square {
    /**
     * The row of the square in the grid
     */
    private final int row;

    /**
     * The column of the square in the grid
     */
    private final int column;

    /**
     * The type of the square
     * 0: empty
     * 1: wall
     * 2: exit
     */
    private int type = 0;

    /**
     * Whether the square has been visited or not
     */
    private boolean isVisited = false;

    /**
     * Whether the square is accessible from Thésée's position or not
     */
    private boolean isAccessible = false;

    /**
     * The grid model
     */
    private final Grid gridModel;

    /**
     * Constructor
     * @param gridModel The grid model
     * @param row The row of the square in the grid
     * @param column The column of the square in the grid
     */
    public Square(Grid gridModel, int row, int column) {
        this.gridModel = gridModel;
        this.row = row;
        this.column = column;
    }

    /**
     * Checks if the current square is a wall
     * @return true if the current square is a wall, false otherwise
     */
    public boolean isWall() {
        return this.type == 1;
    }

    /**
     * Checks if the current square is an exit
     * @return true if the current square is an exit, false otherwise
     */
    public boolean isExit() {
        return this.type == 2;
    }

    /**
     * Checks if the current square is Thésée's position
     * @return true if the current square is Thésée's position, false otherwise
     */
    public boolean isThesee() {
        return this.gridModel.getThesee().getSquare() == this;
    }

    /**
     * Sets the current square as a wall
     * @throws Exception If the current square is Thésée's position or the exit
     */
    public void setWall() throws Exception {
        if (this.gridModel.getThesee().getSquare() == this) throw new Exception("Vous ne pouvez pas placer un mur sur la même case que Thésée. Déplacez d'abord Thésée puis réessayez.");
        if (this.isExit()) throw new Exception("Vous ne pouvez pas placer un mur sur la même case que la sortie. Déplacez d'abord la sortie puis réessayez.");
        this.type = 1;
    }

    /**
     * Removes the existing exit from the grid (if it exists) and sets the current square as an exit
     * @throws Exception If the current square is Thésée's position
     */
    public void setExit() throws Exception {
        if (this.gridModel.getThesee().getSquare() == this) throw new Exception("Vous ne pouvez pas placer la sortie sur la même case que Thésée. Déplacez d'abord Thésée puis réessayez.");

        Square oldExit = this.gridModel.getExit();

        if (oldExit != null) {
            oldExit.setEmpty();
        }

        this.type = 2;
    }

    /**
     * Sets the current square as empty (removes wall or exit)
     */
    public void setEmpty() {
        this.type = 0;
    }

    /**
     * Get the row of the square in the grid
     * @return The row of the square in the grid
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Get the column of the square in the grid
     * @return The column of the square in the grid
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Get the grid model
     * @return The grid model
     */
    public Grid getGrid() {
        return this.gridModel;
    }

    /**
     * Checks if the square has been visited or not
     * @return true if the current square has been visited, false otherwise
     */
    public boolean isVisited() {
        return this.isVisited;
    }

    /**
     * Change the square visited status
     * @param isVisited Whether the current square has been visited or not
     */
    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    /**
     * Checks if the square is accessible from Thésée's position or not
     * @return true if the current square is accessible from Thésée's position, false otherwise
     */
    public boolean isAccessible() {
        return this.isAccessible;
    }

    /**
     * Change the square accessibility status
     * @param isAccessible Whether the current square is accessible from Thésée's position or not
     */
    public void setAccessible(boolean isAccessible) {
        this.isAccessible = isAccessible;
    }
}
