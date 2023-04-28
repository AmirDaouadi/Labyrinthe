/**
 * This class is used to store the data of the auto simulations
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class AutoSimulation {
    /**
     * The grid to use for the simulations
     */
    private final Grid grid;

    /**
     * The algorithm type to use for the simulations
     */
    private final AlgoType algoType;

    /**
     * The simulations
     */
    private final Simulation[] simulations = new Simulation[100];

    /**
     * Constructor
     * @param grid The grid to use for the simulations
     * @param algoType The algorithm type to use for the simulations
     */
    public AutoSimulation(Grid grid, AlgoType algoType) {
        this.grid = grid;
        this.algoType = algoType;

        for (int i = 0; i < this.simulations.length; i++) {
            simulations[i] = new Simulation();
        }
    }

    /**
     * Get the grid used for the simulations
     * @return The grid
     */
    public Grid getGrid() {
        return this.grid;
    }

    /**
     * Get the algorithm type used for the simulations
     * @return The algorithm type
     */
    public AlgoType getAlgoType() {
        return this.algoType;
    }

    /**
     * Get the simulations
     * @return The simulations
     */
    public Simulation[] getSimulations() {
        return this.simulations;
    }

    /**
     * Get the average number of moves of the simulations
     * @return The average number of moves
     */
    public float getAverageMoves() {
        int endedSimulations = 0;
        float averageMoves = 0;

        for (Simulation simulation : simulations) {
            if (simulation.isEnded()) {
                endedSimulations++;
                averageMoves += simulation.getMoves();
            }
        }

        if (endedSimulations == 0) return 0;

        averageMoves = averageMoves / endedSimulations;

        return averageMoves;
    }

    /**
     * Get the number of ended simulations
     * @return The number of ended simulations
     */
    public int getNumberOfEndedSimulations() {
        int endedSimulations = 0;

        for (Simulation simulation : simulations) {
            if (simulation.isEnded()) {
                endedSimulations++;
            }
        }

        return endedSimulations;
    }
}
