import javax.swing.*;
import java.io.File;
public class Main {
    public static void main(String[] args) {
        Window window = new Window();

        // If the first argument is a .lab file, try to open it
        if (args.length > 0 && args[0].toLowerCase().endsWith(".lab")) {
            Grid grid = null;
            try {
                File file = new File(args[0]);
                grid = FileManager.importGrid(new File(file.getAbsolutePath()));
            } catch (Exception ignored) {}

            if (grid != null && HomeView.sizeWarning(new JPanel(), grid.getSize())) {
                window.setPageTitle(args[0]);
                EditorView editorView = new EditorView(window);
                new EditorController(new Editor(grid), editorView);
                window.setContentPane(editorView);
                window.setVisible(true);
                return;
            }
        }

        // Else, open the home page
        HomeView homeView = new HomeView(window);
        window.setContentPane(homeView);
        window.setVisible(true);
    }
}