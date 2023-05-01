import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Window class that extends JFrame with custom settings for the program.
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class Window extends JFrame {
    /**
     * The title of the program.
     */
    private static final String programTitle = "Labyrinthe";

    /**
     * The title of the current page.
     */
    private String pageTitle = "";

    /**
     * Constructor
     */
    public Window() {
        super(programTitle);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = new Dimension(screenSize.width-150, screenSize.height-150);
        this.setSize(d);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 850));
    }

    /**
     * Get the title of the current page.
     * @return The title of the current page.
     */
    public String getPageTitle() {
        return this.pageTitle;
    }

    /**
     * Set the title of the current page.
     * @param title The title of the current page.
     */
    public void setPageTitle(String title) {
        this.pageTitle = title;
        this.setTitle(this.pageTitle + " - " + Window.programTitle);
    }
}