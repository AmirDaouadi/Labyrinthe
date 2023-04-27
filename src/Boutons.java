import java.awt.*;
import javax.swing.*;
import java.io.File;

public class Boutons extends JPanel {
    public static JButton ChoisirGrille(JFrame window) {
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
                        // stocker la taille de la grille choisie
                        /*this.tailleGrille = taille;*/
                        // afficher la taille choisir dans la console
                        System.out.println("Les dimensions de la grille : " + taille + "x" + taille);
                        // Afficher la boîte de dialogue pour le choix du remplissage de la grille
                        String[] options = {"Remplir aléatoirement", "Partir d'une grille vide"};
                        int choix = JOptionPane.showOptionDialog(panel, "Choisissez comment remplir la grille :", "Remplissage de la grille", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        GridController grille = new GridController(new Grid(taille), new GridView());
                        switch (choix) {
                            case 0:
                                // afficher la grille aléatoirement
                                grille.random();
                                window.add(grille.getView());
                                window.validate();
                                break;
                            case 1:
                                window.add(grille.getView());
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

    public static JButton ImporterGrille(JFrame window) {
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
}
