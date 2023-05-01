import javax.swing.*;
import java.awt.*;

/**
 * Manages the display of the grid
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class GridView extends JPanel {
    /**
     * The window
     */
    public final Window window;

    /**
     * The grid model
     */
    protected Grid model;

    /**
     * The grid size in pixels
     */
    protected int gridSize;

    /**
     * The grid x start position in pixels
     */
    protected int gridStartX;

    /**
     * The grid y start position in pixels
     */
    protected int gridStartY;

    /**
     * The size of one square in pixels
     */
    protected int squareSize;

    /**
     * The font used to display the characters
     */
    private Font font;

    /**
     * Constructor
     * @param window The window
     */
    public GridView(Window window) {
        super();
        this.window = window;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(700, 500));
    }

    /**
     * Sets the grid model
     * @param model The grid model
     */
    public void setGrid(Grid model) {
        this.model = model;
    }

    /**
     * Calculates the proportions of the grid
     */
    private void calculateProportions() {
        this.gridSize = Math.min((getHeight() - 50), getWidth()) - 10;
        this.gridStartX = (getWidth() - this.gridSize) / 2;
        this.gridStartY = (((getHeight() - 50) - this.gridSize) / 2) + 50;
        this.squareSize = this.gridSize / this.model.getSize();
        this.font = new Font("Arial", Font.PLAIN, (int) (this.squareSize * 0.75));
    }

    /**
     * Paints the grid
     * @param g The graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.model != null) {
            calculateProportions();

            // Draw squares
            for (int i = 0; i < this.model.getSize(); i++) {
                for (int j = 0; j < this.model.getSize(); j++) {
                    try {
                        Square square = this.model.getSquare(i, j);

                        if (square.isWall() && square.isVisited()) g.setColor(new Color(82, 79, 47));
                        else if (square.isWall()) g.setColor(new Color(122, 68, 25));
                        else if (square.isVisited()) g.setColor(new Color(41, 90, 69));
                        else g.setColor(new Color(77, 170, 87));
                        g.fillRect(this.gridStartX + (this.squareSize * j), this.gridStartY + (this.squareSize * i), this.squareSize, this.squareSize);

                        g.setColor(Color.BLACK);
                        FontMetrics metrics = g.getFontMetrics(this.font);
                        g.setFont(this.font);
                        // Draw exit
                        if (square.isExit()) {
                            String exit = "∩";
                            g.drawString(exit, (this.gridStartX + (this.squareSize * j)) + ((this.squareSize - metrics.stringWidth(exit)) / 2), (this.gridStartY + (this.squareSize * i)) + (((this.squareSize - metrics.getHeight()) / 2) + metrics.getAscent()));
                        }

                        // Draw Thésée
                        if (this.model.getThesee().getSquare() == square) {
                            String thesee = "Θ";
                            g.drawString(thesee, (this.gridStartX + (this.squareSize * j)) + ((this.squareSize - metrics.stringWidth(thesee)) / 2), (this.gridStartY + (this.squareSize * i)) + (((this.squareSize - metrics.getHeight()) / 2) + metrics.getAscent()));
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // Draw horizontal lines
            g.setColor(new Color(73, 88, 103));
            for (int i = 0; i < this.model.getSize()+1; i++) {
                g.drawLine(this.gridStartX, this.gridStartY + (this.squareSize * i), this.gridStartX + (this.squareSize * this.model.getSize()), this.gridStartY + (this.squareSize * i));
            }

            // Draw vertical lines
            for (int i = 0; i < this.model.getSize()+1; i++) {
                g.drawLine(this.gridStartX + (this.squareSize * i), this.gridStartY, this.gridStartX + (this.squareSize * i), this.gridStartY + (this.squareSize * this.model.getSize()));
            }
        }
    }
}
