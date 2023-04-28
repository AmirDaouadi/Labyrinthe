import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Window extends JFrame {
    private static final String programTitle = "Labyrinthe";
    private String pageTitle = "";
    public Window() {
        super(programTitle);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = new Dimension(screenSize.width-150, screenSize.height-150);
        this.setSize(d);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 850));
    }

    public String getPageTitle() {
        return this.pageTitle;
    }

    public void setPageTitle(String title) {
        this.pageTitle = title;
        this.setTitle(this.pageTitle + " - " + Window.programTitle);
    }
}