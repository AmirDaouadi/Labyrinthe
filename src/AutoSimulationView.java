import javax.swing.*;
import java.awt.*;

/**
 * The view for the auto simulation
 * Display directly the success rate and the average number of moves of the simulations
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class AutoSimulationView extends JPanel {
    /**
     * The simulations to display
     */
    private Simulation[] simulations;
    /**
     * The success rate of the simulations
     */
    private float success = 0;

    /**
     * The average number of moves of the simulations
     */
    private float moves = 0;

    /**
     * Constructor
     * @param simulations The simulations to display
     */
    public AutoSimulationView(Simulation[] simulations) {
        super();
        this.simulations = simulations;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(700, 500));
    }

    /**
     * Calculate the success rate and the average number of moves
     */
    private void calculate() {
        for (Simulation simulation : simulations) {
            this.success += simulation.isSuccess() ? 1 : 0;
            this.moves += simulation.getMoves();
        }

        this.success = (this.success / simulations.length) * 100;

        this.moves = this.moves / simulations.length;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        calculate();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        String successStr = "Taux de réussite : " + this.success + "%";
        g.drawString(successStr, (getWidth() - metrics.stringWidth(successStr)) / 2, ((getHeight() - metrics.getHeight()) / 2 + metrics.getAscent()) - 50);

        String movesStr = "Nombre de mouvements moyen : " + this.moves;
        g.drawString(movesStr, (getWidth() - metrics.stringWidth(movesStr)) / 2, ((getHeight() - metrics.getHeight()) / 2 + metrics.getAscent()) + 50);
    }
}
