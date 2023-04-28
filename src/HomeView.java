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
        panelTexte.setOpaque(false);

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

    private static Button choisirGrille(Window window) {
        JPanel panel = new JPanel();
        Button choisirGrille = new Button("Générer une grille", new Dimension(250, 50));

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

                        EditorView editorView = new EditorView(window);
                        EditorController editorController = new EditorController(new Editor(new Grid(taille)), editorView);
                        switch (choix) {
                            case 0:
                                // afficher la grille aléatoirement
                                editorController.random();
                                window.setContentPane(editorView);
                                window.validate();
                                break;
                            case 1:
                                window.setContentPane(editorView);
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

    private static Button importerGrille(Window window) {
        JPanel panel = new JPanel();
        Button importerGrille = new Button("Importer une grille", new Dimension(250, 50));

        importerGrille.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selectionnez le fichier ou se trouve votre grille");
            int choix = fileChooser.showOpenDialog(panel);
            if (choix == JFileChooser.APPROVE_OPTION) {
                File fichier = fileChooser.getSelectedFile();
                try {
                    EditorView editorView = new EditorView(window);
                    new EditorController(new Editor(FileManager.importGrid(fichier)), editorView);
                    window.setContentPane(editorView);
                    window.validate();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return importerGrille;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("./resources/img/background.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}