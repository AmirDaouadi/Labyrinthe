import java.awt.*;
import java.awt.event.MouseEvent;

public class EditorView extends GridView {
    public EditorView(Window window) {
        super(window);
        this.setBackground(new Color(193, 190, 180));
    }
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
