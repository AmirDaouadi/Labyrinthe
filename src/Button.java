import javax.swing.*;
import java.awt.*;

/**
 * Class containing custom settings for JButtons used in the application.
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class Button extends JButton {
    /**
     * Constructor
     * @param text The text of the button
     */
    public Button (String text) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 15));
        setBackground(new Color(96, 175, 255));
    }

    /**
     * Constructor
     * @param text The text of the button
     * @param dimension The dimension of the button
     */
    public Button(String text, Dimension dimension) {
        super(text);
        setPreferredSize(dimension);
        setFont(new Font("Arial", Font.BOLD, 20));
        setBackground(new Color(96, 175, 255));
    }
}
