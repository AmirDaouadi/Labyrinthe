import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {
    private JFrame window;

    public HomeView(JFrame window) {
        this.window = window;

        JLabel texte = getTitre();

        //Récupération des boutons créés dans la classe Boutons
        JButton choisirGrille = Boutons.ChoisirGrille(this.window);
        JButton importerGrille = Boutons.ImporterGrille(this.window);

        // Création du panel pour le texte
        JPanel panelTexte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelTexte.add(texte);

        // Création du panel pour les boutons
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 200));
        panelBoutons.add(choisirGrille);
        panelBoutons.add(importerGrille);
        panelBoutons.setOpaque(false);

        // Ajout des composants au JPanel principal
        setLayout(new BorderLayout());
        add(panelTexte, BorderLayout.NORTH);
        add(panelBoutons, BorderLayout.CENTER);
    }

    private static JLabel getTitre() {
        JLabel texte = new JLabel("Choisissez votre type de grille", SwingConstants.CENTER);
        texte.setPreferredSize(new Dimension(800, 50));
        texte.setFont(new Font("Arial", Font.BOLD, 30));
        texte.setForeground(new Color(0, 200, 10));
        return texte;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("background.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}