import javax.swing.*;
import java.awt.*;
import java.io.File;

public class HomeView extends JPanel {
    private Window window;

    public HomeView(Window window) {
        this.window = window;

        JLabel texte = getTitre();

        //Récupération des boutons créés dans la classe Boutons
        JButton choisirGrille = choisirGrille(this.window);
        JButton importerGrille = importerGrille(this.window);

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

    private static JButton choisirGrille(Window window) {
        JPanel panel = new JPanel();
        JButton choisirGrille = new JButton("Générer une grille");
        choisirGrille.setPreferredSize(new Dimension(250, 50));
        choisirGrille.setFont(new Font("Arial", Font.BOLD, 20));
        choisirGrille.setBackground(Color.GRAY);

        choisirGrille.addActionListener(e -> {
            String strTaille = JOptionPane.showInputDialog(panel, "Entrez la taille de la grille :", "Taille de la grille", JOptionPane.PLAIN_MESSAGE);
            if (strTaille != null && !strTaille.isEmpty()) {
                if (!Character.isDigit(strTaille.charAt(0))) {
                    JOptionPane.showMessageDialog(panel, "Le premier caractère doit être un chiffre ou nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int taille = Integer.parseInt(strTaille);
                    if (taille > 3 && taille < 21) {
                        String[] options = {"Remplir aléatoirement", "Partir d'une grille vide"};
                        int choix = JOptionPane.showOptionDialog(panel, "Choisissez comment remplir la grille :", "Remplissage de la grille", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        GridView gridView = new GridView(window);
                        GridController grille = new GridController(new Grid(taille), gridView);
                        switch (choix) {
                            case 0:
                                // afficher la grille aléatoirement
                                grille.random();
                                window.setContentPane(gridView);
                                window.validate();
                                break;
                            case 1:
                                window.setContentPane(gridView);
                                window.validate();
                                break;
                            default:
                                // gérer le cas où aucun choix n'a été fait
                                JOptionPane.showMessageDialog(panel, "Aucun choix n'a été fait.", "Attention", JOptionPane.WARNING_MESSAGE);
                                return;
                        }

                    } else {
                        String errorMessage = "La taille doit être au moins de 4.";
                        if (taille >= 21) {
                            errorMessage = "La taille ne doit pas dépasser 20.";
                        }
                        JOptionPane.showMessageDialog(panel, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Tapez " + strTaille.charAt(0) + " pour une grille " + strTaille.charAt(0) +"x"+ strTaille.charAt(0) +".", "Erreur", JOptionPane.ERROR_MESSAGE);


                }
            }
        });

        return choisirGrille;
    }

    private static JButton importerGrille(JFrame window) {
        JPanel panel = new JPanel();
        JButton importerGrille = new JButton("Importer une grille");
        importerGrille.setPreferredSize(new Dimension(250, 50));
        importerGrille.setFont(new Font("Arial", Font.BOLD, 20));
        importerGrille.setBackground(Color.GRAY);

        importerGrille.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selectionnez le fichier ou se trouve votre grille");
            int choix = fileChooser.showOpenDialog(panel);
            if (choix == JFileChooser.APPROVE_OPTION) {
                File fichier = fileChooser.getSelectedFile();
                // TODO: charger la grille depuis le fichier
            }
        });

        return importerGrille;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("background.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}