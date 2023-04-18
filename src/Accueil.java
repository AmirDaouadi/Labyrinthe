import javax.swing.*;
import java.awt.*;

public class Accueil extends JPanel {
    public static void main(String[] args) {
        // Appel de la méthode fenetreAccueil pour créer la fenêtre d'accueil
        fenetreAccueil();
    }

    public static void fenetreAccueil() {

        // Création de la fenêtre d'accueil
        Fenetre fenetre = new Fenetre();

        // Création de l'accueil
        Accueil accueil = new Accueil();
        fenetre.setContentPane(accueil);

        // Création du texte
        JLabel texte = new JLabel("Choisissez votre type de grille", SwingConstants.CENTER);
        texte.setPreferredSize(new Dimension(800, 50));
        texte.setFont(new Font("Arial", Font.BOLD, 30));
        texte.setForeground(new Color(0, 200, 10));

        // Création des boutons
        Boutons boutons = new Boutons(fenetre);

        //Récupération des boutons créés dans la classe Boutons
        JButton choisirGrille = boutons.getChoisirGrille();
        JButton importerGrille = boutons.getImporterGrille();

        // Création du panel pour le texte
        JPanel panelTexte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelTexte.add(texte);

        // Création du panel pour les boutons
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 200));
        panelBoutons.add(choisirGrille);
        panelBoutons.add(importerGrille);
        panelBoutons.setOpaque(false);

        // Ajout des composants au JPanel principal
        accueil.setLayout(new BorderLayout());
        accueil.add(panelTexte, BorderLayout.NORTH);
        accueil.add(panelBoutons, BorderLayout.CENTER);

        fenetre.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("background.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
