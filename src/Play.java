import javax.swing.*;
import java.awt.*;

public class Play extends JFrame {
    public Play() {
        super("Démarrage");
        this.setSize(250, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelDemarrage = new JPanel(new BorderLayout());
        panelDemarrage.setBackground(Color.WHITE);

        JButton play = new JButton("Jouer");
        play.setPreferredSize(new Dimension(100, 50));
        play.setFont(new Font("Arial", Font.BOLD, 20));
        play.setBackground(new Color(0, 150, 200));
        play.addActionListener(e -> {
            this.dispose(); // ferme la fenêtre de démarrage
            Accueil.fenetreAccueil(); // ouvre la fenêtre d'accueil
        });

        JPanel panelPlay = new JPanel();
        panelPlay.add(play, BorderLayout.CENTER);

        panelDemarrage.add(panelPlay, BorderLayout.CENTER);
        panelDemarrage.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));
        this.setContentPane(panelDemarrage);
        this.setResizable(false);
        this.setVisible(true);
    }
}