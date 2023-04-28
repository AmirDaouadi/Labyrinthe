import java.awt.*;

/**
 * The manual simulation view
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class ManualSimulationView extends GridView {
    /**
     * The simulation model
     */
    private Simulation model;

    /**
     * Constructor
     * @param window The window
     * @param model The simulation model
     */
    public ManualSimulationView(Window window, Simulation model) {
        super(window);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        String movesStr = "Coups : " + this.model.getMoves();
        g.drawString(movesStr, 5, 5);
    }
}
