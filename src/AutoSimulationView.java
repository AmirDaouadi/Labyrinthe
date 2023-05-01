import javax.swing.*;
import java.awt.*;

/**
 * The view for the automatic simulation
 * Display directly the success rate and the average number of moves of the simulations
 * @version 1.1
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class AutoSimulationView extends JPanel {
    /**
     * The automatic simulation model
     */
    public final AutoSimulation model;

    /**
     * Constructor
     * @param model The automatic simulation model
     */
    public AutoSimulationView(AutoSimulation model) {
        super();
        this.model = model;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(700, 500));
    }

    /**
     * Paint the view
     * @param g The graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.model.getSimulations()[0].getMoves() == 0) return;

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        int y = 0;

        int endedSimulations = this.model.getNumberOfEndedSimulations();
        int totalSimulations = this.model.getSimulations().length;
        int index = endedSimulations >= totalSimulations ? totalSimulations - 1 : endedSimulations;
        Simulation lastSimulation = this.model.getSimulations()[index];

        if  (!lastSimulation.isEnded()) {
            y = 50;
            g.setColor(Color.RED);
            String simulationStr = "Simulation " + endedSimulations + "/" + totalSimulations + " : " + lastSimulation.getMoves() + " mouvements";
            g.drawString(simulationStr, (getWidth() - metrics.stringWidth(simulationStr)) / 2, ((getHeight() - metrics.getHeight()) / 2 + metrics.getAscent()) - 50);
        }

        g.setColor(Color.BLACK);
        String movesStr = "Nombre de mouvements moyen : " + this.model.getAverageMoves();
        g.drawString(movesStr, (getWidth() - metrics.stringWidth(movesStr)) / 2, ((getHeight() - metrics.getHeight()) / 2 + metrics.getAscent()) + y);
    }
}
