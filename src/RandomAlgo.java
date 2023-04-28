import java.util.Random;

/**
 * Random algorithm
 * @version 0.1
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class RandomAlgo implements Algo {
    /**
     * The grid model
     */
    private final Grid grid;

    /**
     * The simulation model
     */
    private final Simulation simulation;

    /**
     * The Thésée controller
     */
    private final TheseeController theseeController;

    /**
     * Random number generator
     */
    private final Random random = new Random();

    /**
     * Constructor
     * @param grid The grid model
     * @param simulation The simulation model
     */
    public RandomAlgo(Grid grid, Simulation simulation) {
        this.grid = grid;
        this.simulation = simulation;
        this.theseeController = new TheseeController(this.grid.getThesee());
        this.grid.getThesee().getSquare().setVisited(true);
    }

    /**
     * Makes the next move of the algorithm
     */
    public void nextMove() {
        if (this.simulation.isEnded()) return;
        Direction[] availableDirection = this.theseeController.getAvailableDirections();

        Direction randomDirection = availableDirection[this.random.nextInt(availableDirection.length)];

        this.theseeController.move(randomDirection, this.simulation);

        this.theseeController.getAvailableDirections();
        if (!this.simulation.isEnded() && this.grid.isEnded()) {
            this.simulation.setEnded();
        }
    }

}
