import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Window extends JFrame {
    public Window() {
        super("Labyrinthe");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = new Dimension(screenSize.width-150, screenSize.height-150);
        this.setSize(d);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(750, 750));
    }
}