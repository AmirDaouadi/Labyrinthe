/**
 * Controller for the automatic simulation
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class AutoSimulationController {
    /**
     * The automatic simulation view
     */
    private final AutoSimulationView view;

    /**
     * The automatic simulation model
     */
    private final AutoSimulation model;

    /**
     * Constructor
     * @param view The automatic simulation view
     * @param model The automatic simulation model
     */
    public AutoSimulationController(AutoSimulationView view, AutoSimulation model) {
        this.view = view;
        this.model = model;

        new Thread(this::run).start();
    }

    /**
     * Run the simulations
     */
    public void run() {
        for (Simulation simulation : this.model.getSimulations()) {
            if (!simulation.isEnded()) {
                Algo algo;
                if (this.model.getAlgoType() == AlgoType.RANDOM) {
                    algo = new RandomAlgo(this.model.getGrid(), simulation);
                } else {
                    algo = new DeterministicAlgo(this.model.getGrid(), simulation);
                }
                while (!simulation.isEnded()) {
                    algo.nextMove();
                    this.view.repaint();
                }

                this.model.getGrid().reset();
            }
        }
    }
}
