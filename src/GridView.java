import javax.swing.*;
import java.awt.*;

public class GridView extends JPanel {
    public Window window;
    protected Grid model;
    protected int gridSize;
    protected int gridStartX;
    protected int gridStartY;
    protected int squareSize;
    private Font font;
    private final String exit = "∩";
    private final String thesee = "Θ";

    /**
     * Manages the display of the grid
     */
    public GridView(Window window) {
        super();
        this.window = window;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(700, 500));
    }

    public void setGrid(Grid model) {
        this.model = model;
    }

    private void calculateProportions() {
        this.gridSize = Math.min((getHeight() - 50), getWidth()) - 10;
        this.gridStartX = (getWidth() - this.gridSize) / 2;
        this.gridStartY = (((getHeight() - 50) - this.gridSize) / 2) + 50;
        this.squareSize = this.gridSize / this.model.getSize();
        this.font = new Font("Arial", Font.PLAIN, (int) (this.squareSize * 0.75));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.model != null) {
            calculateProportions();

            // Draw squares
            for (int i = 0; i < this.model.getSize(); i++) {
                for (int j = 0; j < this.model.getSize(); j++) {
                    try {
                        if (this.model.getSquare(i, j).isWall()) g.setColor(new Color(122, 68, 25));
                        else g.setColor(new Color(77, 170, 87));
                        g.fillRect(this.gridStartX + (this.squareSize * j), this.gridStartY + (this.squareSize * i), this.squareSize, this.squareSize);

                        g.setColor(Color.BLACK);
                        FontMetrics metrics = g.getFontMetrics(this.font);
                        g.setFont(this.font);
                        // Draw exit
                        if (this.model.getSquare(i, j).isExit()) {
                            g.drawString(this.exit, (this.gridStartX + (this.squareSize * j)) + ((this.squareSize - metrics.stringWidth(this.exit)) / 2), (this.gridStartY + (this.squareSize * i)) + (((this.squareSize - metrics.getHeight()) / 2) + metrics.getAscent()));
                        }

                        // Draw Thésée
                        if (this.model.getThesee().getSquare() == this.model.getSquare(i, j)) {
                            g.drawString(this.thesee, (this.gridStartX + (this.squareSize * j)) + ((this.squareSize - metrics.stringWidth(this.thesee)) / 2), (this.gridStartY + (this.squareSize * i)) + (((this.squareSize - metrics.getHeight()) / 2) + metrics.getAscent()));
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
