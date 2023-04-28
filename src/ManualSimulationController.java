import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Simulation model;
    /**
     * The manual simulation view
     */
    private ManualSimulationView view;
    /**
     * The grid model
     */
    private Grid grid;
    /**
     * The algorithm used for the simulation
     */
    private Algo algo;
    /**
     * The restart button
     */
    private Button restartButton = new Button("Recommencer");
    /**
     * The next button
     */
    private Button nextButton = new Button("Coup suivant");

    /**
     * Constructor
     * @param model The simulation model
     * @param view The manual simulation view
     * @param algo The algorithm used for the simulation
     */
    public ManualSimulationController(Simulation model, ManualSimulationView view, Algo algo) {
        this.model = model;
        this.view = view;
        this.algo = algo;

        JPanel buttons = new JPanel();

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                algo.reset();
            }
        });
        buttons.add(restartButton);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                algo.nextMove();
                if (model.isSuccess()) {
                    nextButton.setEnabled(false);
                }
            }
        });
        buttons.add(nextButton);

        this.view.add(buttons, BorderLayout.NORTH);
    }
}
