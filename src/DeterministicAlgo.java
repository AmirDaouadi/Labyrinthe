import java.util.Stack;

/**
 * Deterministic algorithm
 * @version 0.1
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class DeterministicAlgo implements Algo {
    /**
     * The grid model
     */
    private final Grid grid;

    /**
     * The simulation model
     */
    private final Simulation simulation;

    /**
     * The Thésée model
     */
    private final Thesee thesee;

    /**
     * The Thésée controller
     */
    private final TheseeController theseeController;

    /**
     * The directions stack used to go back
     */
    private final Stack<Direction> directions = new Stack<>();

    /**
     * Constructor
     * @param grid The grid model
     * @param simulation The simulation model
     */
    public DeterministicAlgo(Grid grid, Simulation simulation) {
        this.grid = grid;
        this.simulation = simulation;
        this.thesee = this.grid.getThesee();
        this.theseeController = new TheseeController(this.thesee);
        this.thesee.getSquare().setVisited(true);
    }

    /**
     * Makes the next move of the algorithm
     */
    @Override
    public void nextMove() {
        if (this.simulation.isEnded()) return;
        Direction[] availableDirection = this.theseeController.getAvailableDirections();

        Direction nextUnvisitedDirection = null;
        for (Direction direction : availableDirection) {
            try {
                if (!this.thesee.getSquare(direction).isVisited()) {
                    nextUnvisitedDirection = direction;
                    break;
                }
            } catch (Exception ignored) {}
        }
        if (nextUnvisitedDirection != null) {
            if (this.theseeController.move(nextUnvisitedDirection, this.simulation)) this.directions.push(nextUnvisitedDirection);
        } else this.theseeController.move(this.directions.pop().opposite(), this.simulation);

        if (!this.simulation.isEnded() && this.grid.isEnded()) {
            this.simulation.setEnded();
        }
    }

}
