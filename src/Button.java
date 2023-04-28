import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button (String text) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 15));
        setBackground(new Color(96, 175, 255));
    }

    public Button(String text, Dimension dimension) {
        super(text);
        setPreferredSize(dimension);
        setFont(new Font("Arial", Font.BOLD, 20));
        setBackground(new Color(96, 175, 255));
    }
}
