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
    private final ManualSimulation model;

    /**
     * Constructor
     * @param window The window
     * @param model The simulation model
     */
    public ManualSimulationView(Window window, ManualSimulation model) {
        super(window);
        this.model = model;
        super.setGrid(this.model.getGrid());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));

        String movesStr = "Coups : " + this.model.getSimulation().getMoves();
        g.drawString(movesStr, 10, 20);
    }
}
