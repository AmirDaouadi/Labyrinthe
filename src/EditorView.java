import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This the view for the editor
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class EditorView extends GridView {
    /**
     * Constructor
     * @param window the window
     */
    public EditorView(Window window) {
        super(window);
        this.setBackground(new Color(193, 190, 180));
    }

    /**
     * Get the square at the mouse position
     * @param e the mouse event
     * @return the square at the mouse position, or null if there is no square at this position
     */
    public Square click(MouseEvent e) {
        if ((e.getX() < this.gridStartX) || (e.getX() > (this.gridStartX + this.gridSize)) || (e.getY() < this.gridStartY) || (e.getY() > (this.gridStartY + this.gridSize))) return null;
        int x = (e.getX() - this.gridStartX) / this.squareSize;
        int y = (e.getY() - this.gridStartY) / this.squareSize;
        if ((x >= 0) && (x < this.model.getSize()) && (y >= 0) && (y < this.model.getSize())) {
            try {
                return this.model.getSquare(y, x);
            } catch (Exception ex) {
                return null;
            }
        }
        return null;
    }
}
