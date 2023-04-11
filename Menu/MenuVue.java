import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuVue extends JFrame {
    private JButton btnRandomGrid;
    private JButton btnExistingGrid;

    public MenuVue() {
        ImageIcon icon = new ImageIcon("background.jpg");
        JLabel label = new JLabel(icon);

        btnRandomGrid = new JButton("Charger une grille aléatoire");
        btnExistingGrid = new JButton("Charger une grille existante");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRandomGrid);
        buttonPanel.add(btnExistingGrid);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(label, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addRandomGridListener(ActionListener listener) {
        btnRandomGrid.addActionListener(listener);
    }

    public void addExistingGridListener(ActionListener listener) {
        btnExistingGrid.addActionListener(listener);
    }
}
