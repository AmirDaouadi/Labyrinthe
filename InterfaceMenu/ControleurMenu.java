import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurMenu {
   private VueMenu vue;

   public ControleurMenu(VueMenu vue) {
      this.vue = vue;
      vue.addChargerGrilleAleatoireListener(new ChargerGrilleAleatoireListener());
      vue.addChargerGrilleExistanteListener(new ChargerGrilleExistanteListener());
   }
   class ChargerGrilleAleatoireListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
      }
   }
   class ChargerGrilleExistanteListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
      }
   }
}
