import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HGameController implements KeyListener {
    private Player player;

    public HGameController(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            player.move(-1, 0);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            player.move(1, 0);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            player.move(0, -1);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            player.move(0, 1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
