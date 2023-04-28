import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class HomeView extends JPanel {
    public final Window window;

    public HomeView(Window window) {
        this.window = window;

        JLabel texte = getTitre();

        //Récupération des boutons créés dans la classe Boutons
        JButton choisirGrille = choisirGrille();
        JButton importerGrille = importerGrille();

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

    private Button choisirGrille() {
        Button choisirGrille = new Button("Générer une grille", new Dimension(250, 50));

        choisirGrille.addActionListener(e -> {
            String strTaille = JOptionPane.showInputDialog(this, "Entrez la taille de la grille :", "Taille de la grille", JOptionPane.PLAIN_MESSAGE);
            if (strTaille != null && !strTaille.isEmpty()) {
                if (!Character.isDigit(strTaille.charAt(0))) {
                    JOptionPane.showMessageDialog(this, "Le premier caractère doit être un chiffre ou nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int taille = Integer.parseInt(strTaille);
                    if (taille >= 2 && taille <= 255) {
                        if (!sizeWarning(this, taille)) return;

                        String[] options = {"Remplir aléatoirement", "Partir d'une grille vide"};
                        int choix = JOptionPane.showOptionDialog(this, "Choisissez comment remplir la grille :", "Remplissage de la grille", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                        EditorView editorView = new EditorView(window);
                        EditorController editorController = new EditorController(new Editor(new Grid(taille)), editorView);
                        switch (choix) {
                            case 0 -> {
                                // afficher la grille aléatoirement
                                editorController.random();
                                window.setContentPane(editorView);
                                window.validate();
                            }
                            case 1 -> {
                                window.setContentPane(editorView);
                                window.validate();
                            }
                            default ->
                                // gérer le cas où aucun choix n'a été fait
                                    JOptionPane.showMessageDialog(this, "Aucun choix n'a été fait.", "Attention", JOptionPane.WARNING_MESSAGE);
                        }

                    } else {
                        String errorMessage = "La taille doit être supérieur ou égale à 2.";
                        if (taille > 255) {
                            errorMessage = "La taille doit être inférieur ou égale à 255.";
                        }
                        JOptionPane.showMessageDialog(this, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Tapez " + strTaille.charAt(0) + " pour une grille " + strTaille.charAt(0) +"x"+ strTaille.charAt(0) +".", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return choisirGrille;
    }

    private Button importerGrille() {
        Button importerGrille = new Button("Importer une grille", new Dimension(250, 50));

        importerGrille.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Sélectionnez le fichier où se trouve votre grille");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Fichier labyrinthe (*.lab)", "lab"));
            int choix = fileChooser.showOpenDialog(this);
            if (choix == JFileChooser.APPROVE_OPTION) {
                File fichier = fileChooser.getSelectedFile();
                try {
                    Grid grid = FileManager.importGrid(fichier);
                    if (!sizeWarning(this, grid.getSize())) return;
                    EditorView editorView = new EditorView(window);
                    new EditorController(new Editor(grid), editorView);
                    window.setContentPane(editorView);
                    window.validate();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return importerGrille;
    }

    /**
     * Shows a warning message if the grid size is too big
     * @param size the size of the grid
     * @return true if the user wants to continue, false otherwise
     */
    public static boolean sizeWarning(JComponent parentComponent, int size) {
        if (size <= 25) return true;
        String[] options = {"Abandonner", "Continuer avec cette grille"};
        int choice = JOptionPane.showOptionDialog(parentComponent, "Vous essayez d'ouvrir une grille de taille " + size + "x" + size + ".\nEn continuant avec cette grille, vous pourrez rencontrer des difficultées lors de l'edition et la durée des simulations de résolutions pourra être impactée.\nVoulez-vous continuer quand même ?", "Attention", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        return choice != 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("./resources/img/background.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}