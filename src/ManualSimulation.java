/**
 * This class is used to store the data of a manual simulation
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class ManualSimulation {
    /**
     * The grid to use for the simulations
     */
    private final Grid grid;

    /**
     * The algorithm type to use for the simulations
     */
    private final AlgoType algoType;

    /**
     * The simulation
     */
    private final Simulation simulation = new Simulation();

    /**
     * Constructor
     * @param grid The grid to use for the simulation
     * @param algoType The algorithm type to use for the simulation
     */
    public ManualSimulation(Grid grid, AlgoType algoType) {
        this.grid = grid;
        this.algoType = algoType;
    }

    /**
     * Get the grid used for the simulation
     * @return The grid
     */
    public Grid getGrid() {
        return this.grid;
    }

    /**
     * Get the algorithm type used for the simulation
     * @return The algorithm type
     */
    public AlgoType getAlgoType() {
        return this.algoType;
    }

    /**
     * Get the simulation
     * @return The simulation
     */
    public Simulation getSimulation() {
        return this.simulation;
    }

}
