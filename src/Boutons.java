import java.awt.*;
import javax.swing.*;
import java.io.File;

public class Boutons extends JPanel {

    private JButton choisirGrille;
    private JButton importerGrille;
    private JFrame fenetrePrincipale;
    
    public Boutons(JFrame fenetrePrincipale) {
        // Création des boutons
        choisirGrille = new JButton("Générer une grille");
        choisirGrille.setPreferredSize(new Dimension(250, 50));
        choisirGrille.setFont(new Font("Arial", Font.BOLD, 20));
        choisirGrille.setBackground(Color.CYAN);
    
        importerGrille = new JButton("Importer une grille");
        importerGrille.setPreferredSize(new Dimension(250, 50));
        importerGrille.setFont(new Font("Arial", Font.BOLD, 20));
        importerGrille.setBackground(Color.GREEN);

        this.fenetrePrincipale = fenetrePrincipale;
    
        // Ajout des listeners aux boutons + gestion des erreurs
        choisirGrille.addActionListener(e -> {
            String strTaille = JOptionPane.showInputDialog(this, "Entrez la taille de la grille :", "Taille de la grille", JOptionPane.PLAIN_MESSAGE);
            if (strTaille != null && !strTaille.isEmpty()) {
                if (!Character.isDigit(strTaille.charAt(0))) {
                    JOptionPane.showMessageDialog(this, "Le premier caractère doit être un chiffre ou nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int taille = Integer.parseInt(strTaille);
                    if (taille > 3 && taille < 21) {
                        // afficher la taille choisir dans la console
                        System.out.println("Les dimensions de la grille : " + taille + "x" + taille);
                        // Afficher la boîte de dialogue pour le choix du remplissage de la grille
                        String[] options = {"Remplir aléatoirement", "Partir d'une grille vide"};
                        int choix = JOptionPane.showOptionDialog(this, "Choisissez comment remplir la grille :", "Remplissage de la grille", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        switch (choix) {
                            case 0:
                                // afficher la grille aléatoirement
                                GridPanel grille = new GridPanel();
                                this.fenetrePrincipale.add(grille);
                                this.fenetrePrincipale.validate();
                                break;
                            case 1:
                                // TODO: partir d'une grille vide
                                break;
                            default:
                                // gérer le cas où aucun choix n'a été fait
                                JOptionPane.showMessageDialog(this, "Aucun choix n'a été fait.", "Attention", JOptionPane.WARNING_MESSAGE);
                                return;
                        }
    
                    } else {
                        String errorMessage = "La taille doit être au moins de 4.";
                        if (taille >= 21) {
                            errorMessage = "La taille ne doit pas dépasser 20.";
                        }
                        JOptionPane.showMessageDialog(this, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Tapez " + strTaille.charAt(0) + " pour une grille " + strTaille.charAt(0) +"x"+ strTaille.charAt(0) +".", "Erreur", JOptionPane.ERROR_MESSAGE);
               

                }
            }
        });

        importerGrille.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choisissez un fichier de grille");
            int choix = fileChooser.showOpenDialog(this);
            if (choix == JFileChooser.APPROVE_OPTION) {
                File fichier = fileChooser.getSelectedFile();
                // TODO: charger la grille depuis le fichier
            }
        });
    }

    public JButton getChoisirGrille() {
        return choisirGrille;
    }

    public JButton getImporterGrille() {
        return importerGrille;
    }
}