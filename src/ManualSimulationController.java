import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Controller for the manual simulation view.
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class ManualSimulationController {
    /**
     * The simulation model
     */
    private final ManualSimulation model;

    /**
     * The manual simulation view
     */
    private final ManualSimulationView view;

    /**
     * The algorithm used for the simulation
     */
    private Algo algo;

    /**
     * The next button
     */
    private final Button nextButton = new Button("Coup suivant");

    /**
     * Constructor
     * @param model The manual simulation model
     * @param view The manual simulation view
     */
    public ManualSimulationController(ManualSimulationView view, ManualSimulation model) {
        this.model = model;
        this.view = view;

        JPanel buttons = new JPanel();

        nextButton.addActionListener(e -> move());
        buttons.add(nextButton);

        this.view.add(buttons, BorderLayout.NORTH);

        this.view.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                move();
            }
        });
        this.view.setFocusable(true);
        this.view.requestFocusInWindow();

        run();
    }

    /**
     * Run the simulation
     */
    public void run() {
        if (this.model.getAlgoType() == AlgoType.RANDOM) {
            this.algo = new RandomAlgo(this.model.getGrid(), this.model.getSimulation());
        } else {
            this.algo = new DeterministicAlgo(this.model.getGrid(), this.model.getSimulation());
        }
    }

    private void move() {
        if (model.getSimulation().isEnded()) return;
        this.algo.nextMove();
        this.view.repaint();
        if (model.getSimulation().isEnded()) {
            nextButton.setEnabled(false);
            JOptionPane.showMessageDialog(view, "Partie terminée en " + model.getSimulation().getMoves() + " coups !", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
