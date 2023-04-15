import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
    private int x;
    private int y;

    public GameController(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            y--;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            y++;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            x--;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            x++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
