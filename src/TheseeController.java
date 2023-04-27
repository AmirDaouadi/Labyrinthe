import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TheseeController implements KeyListener {
    private Thesee model;
    private GridView gridView;

    public TheseeController(Thesee model, GridView gridView) {
        this.model = model;
        this.gridView = gridView;
    }

    public boolean moveUp() {
        try {
            Square currentSquare = this.model.getSquare();
            Square newSquare = currentSquare.getGrid().getSquare(currentSquare.getRow(), currentSquare.getColumn() - 1);
            if (newSquare.isWall()) return false;
            this.model.setSquare(newSquare);
            this.gridView.repaint();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean moveDown() {
        try {
            Square currentSquare = this.model.getSquare();
            Square newSquare = currentSquare.getGrid().getSquare(currentSquare.getRow(), currentSquare.getColumn() + 1);
            if (newSquare.isWall()) return false;
            this.model.setSquare(newSquare);
            this.gridView.repaint();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean moveLeft() {
        try {
            Square currentSquare = this.model.getSquare();
            Square newSquare = currentSquare.getGrid().getSquare(currentSquare.getRow() - 1, currentSquare.getColumn());
            if (newSquare.isWall()) return false;
            this.model.setSquare(newSquare);
            this.gridView.repaint();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean moveRight() {
        try {
            Square currentSquare = this.model.getSquare();
            Square newSquare = currentSquare.getGrid().getSquare(currentSquare.getRow() + 1, currentSquare.getColumn());
            if (newSquare.isWall()) return false;
            this.model.setSquare(newSquare);
            this.gridView.repaint();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        boolean moved;
        if (keyCode == KeyEvent.VK_UP) {
            moved = moveUp();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moved = moveDown();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            moved = moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moved = moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
