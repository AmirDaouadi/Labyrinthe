import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Fenetre extends JFrame {
    public Fenetre() {
        super("Accueil");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = new Dimension(screenSize.width, screenSize.height);
        this.setSize(d);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}