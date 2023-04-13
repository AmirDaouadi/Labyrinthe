import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VueMenu extends JFrame {
   private JPanel panel;
   private JLabel label;
   private JButton boutonAleatoire;
   private JButton boutonExistante;

   public VueMenu() {
      this.setTitle("Menu principal");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setSize(800, 600);
      ImageIcon image = new ImageIcon("background.png");
      label = new JLabel("", image, JLabel.CENTER);
      label.setBounds(0, 0, 800, 600);
      boutonAleatoire = new JButton("Charger une grille aléatoire");
      boutonAleatoire.setBounds(150, 200, 250, 50);

      boutonExistante = new JButton("Charger une grille existante");
      boutonExistante.setBounds(400, 200, 250, 50);
      panel = new JPanel();
      panel.setLayout(null);
      panel.add(boutonAleatoire);
      panel.add(boutonExistante);
      panel.add(label);
      this.add(panel);
   }

   public void afficher() {
      this.setVisible(true);
   }
   public void addChargerGrilleAleatoireListener(ActionListener listener) {
      boutonAleatoire.addActionListener(listener);
   }

   public void addChargerGrilleExistanteListener(ActionListener listener) {
      boutonExistante.addActionListener(listener);
   }
}
