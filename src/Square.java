public class Square {
    public final int row;
    public final int column;
    public int type = 0;
    private Grid gridModel;

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
     * Checks if the current square is empty (not a wall and not an exit)
     * @return true if the current square is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.type == 0;
    }

    public boolean isThesee() {
        return this.gridModel.getThesee().getSquare() == this;
    }

    /**
     * Sets the current square as a wall
     */
    public void setWall() {
        this.type = 1;
    }

    /**
     * Removes the existing exit from the grid (if it exists) and sets the current square as an exit
     */
    public void setExit() {
        for (int i = 0; i < this.gridModel.getSize(); i++) {
            for (int j = 0; j < this.gridModel.getSize(); j++) {
                try {
                    if (this.gridModel.getSquare(i, j).isExit()) {
                        this.gridModel.getSquare(i, j).setEmpty();
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        this.type = 2;
    }

    /**
     * Sets the current square as empty (removes wall or exit)
     */
    public void setEmpty() {
        this.type = 0;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public Grid getGrid() {
        return this.gridModel;
    }
}
