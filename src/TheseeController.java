public class TheseeController {
    private final Thesee model;
    private GridView gridView;

    public TheseeController(Thesee model, GridView gridView) {
        this.model = model;
        this.gridView = gridView;
    }

    public TheseeController(Thesee model) {
        this.model = model;
    }

    /**
     * Move Thésée in the given direction
     * @param direction The direction to move Thésée to
     * @param simulation The simulation model
     * @return true if the move was successful, false otherwise.
     */
    public boolean move(Direction direction, Simulation simulation) {
        simulation.addMove();
        try {
            Square newSquare = this.model.getSquare(direction);
            newSquare.setVisited(true);
            // If the new square is a wall, add a move to simulate the rollback of the move
            if (newSquare.isWall()) {
                simulation.addMove();
                if (this.gridView != null) this.gridView.repaint();
                return false;
            }
            if (newSquare.isExit()) simulation.setEnded();
            else this.model.setSquare(newSquare);
            if (this.gridView != null) this.gridView.repaint();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the available directions for the next move.
     * It helps prevent Thésée from going out of the grid.
     * @return The available directions
     */
    public Direction[] getAvailableDirections() {
        Direction[] availableDirections = new Direction[4];
        int availableDirectionsCount = 0;

        try {
            this.model.getSquare(Direction.UP);
            availableDirections[availableDirectionsCount] = Direction.UP;
            availableDirectionsCount++;
        } catch (Exception ignored) {}

        try {
            this.model.getSquare(Direction.DOWN);
            availableDirections[availableDirectionsCount] = Direction.DOWN;
            availableDirectionsCount++;
        } catch (Exception ignored) {}

        try {
            this.model.getSquare(Direction.LEFT);
            availableDirections[availableDirectionsCount] = Direction.LEFT;
            availableDirectionsCount++;
        } catch (Exception ignored) {}

        try {
            this.model.getSquare(Direction.RIGHT);
            availableDirections[availableDirectionsCount] = Direction.RIGHT;
            availableDirectionsCount++;
        } catch (Exception ignored) {}

        Direction[] availableDirectionsTrimmed = new Direction[availableDirectionsCount];
        System.arraycopy(availableDirections, 0, availableDirectionsTrimmed, 0, availableDirectionsCount);
        return availableDirectionsTrimmed;
    }
}
