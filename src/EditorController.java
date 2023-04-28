import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

public class EditorController extends GridController {
    private final Editor model;
    private final EditorView view;
    private enum Mode { DISABLED, WALL, THESEE, EXIT }
    private Mode editMode = Mode.DISABLED;
    private boolean edited = false;
    private final Button editTheseeButton = new Button("Modifier Thésée");
    private final Button editExitButton = new Button("Modifier Sortie");
    private final Button editWallButton = new Button("Modifier Murs");
    private final Button exportButton = new Button("Exporter");
    private final Button startButton = new Button("Démarrer");

    public EditorController(Editor model, EditorView view) {
        super(model.getGrid(), view);
        this.model = model;
        this.view = view;

        this.view.window.setPageTitle(this.model.getGrid().getFile().getName());

        this.view.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                edit(view.click(e));
            }
        });

        JPanel buttons = new JPanel();

        Button randomizeButton = new Button("Aléatoire");
        randomizeButton.addActionListener(e -> {
            random();
            if (!edited) {
                edited = true;
                view.window.setPageTitle("*" + view.window.getPageTitle());
            }
            view.repaint();
        });
        buttons.add(randomizeButton);

        this.editWallButton.addActionListener(e -> setEditMode(Mode.WALL));
        buttons.add(editWallButton);

        this.editTheseeButton.addActionListener(e -> setEditMode(Mode.THESEE));
        buttons.add(this.editTheseeButton);

        this.editExitButton.addActionListener(e -> setEditMode(Mode.EXIT));
        buttons.add(this.editExitButton);

        this.exportButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Sélectionnez le fichier dans lequel vous souhaitez enregistrer votre grille");
            fileChooser.setSelectedFile(model.getGrid().getFile());
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Fichier labyrinthe (*.lab)", "lab"));
            int choix = fileChooser.showSaveDialog(view);
            if (choix == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.toString().toLowerCase().endsWith(".lab")) {
                    file = new File(file + ".lab");
                }
                view.window.setPageTitle(file.getName());
                model.getGrid().setFile(file);
                try {
                    FileManager.exportGrid(model.getGrid());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttons.add(this.exportButton);

        this.startButton.addActionListener(e -> {
            if (!this.model.getGrid().validate()) {
                JOptionPane.showMessageDialog(view, "La grille n'est pas valide.\nAssurez-vous que la grille contienne Thésée ainsi qu'une sortie, et que Thésée peut aller jusqu'à la sortie.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String[] algoOptions = {"Aléatoire", "Déterministe"};
            String[] viewOptions = {"Automatique", "Manuel"};
            int algoChoice = JOptionPane.showOptionDialog(view, "Choisissez l'algorithme à utiliser :", "Choix de l'algorithme", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, algoOptions, algoOptions[0]);

            if (algoChoice == -1) {
                JOptionPane.showMessageDialog(view, "Aucun choix n'a été fait.", "Attention", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int viewChoice = JOptionPane.showOptionDialog(view, "Choisissez l'affichage à utiliser :", "Choix de l'affichage", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, viewOptions, viewOptions[0]);

            if (viewChoice == -1) {
                JOptionPane.showMessageDialog(view, "Aucun choix n'a été fait.", "Attention", JOptionPane.WARNING_MESSAGE);
                return;
            }

            AlgoType algoType = algoChoice == 0 ? AlgoType.RANDOM : AlgoType.DETERMINISTIC;

            if (viewChoice == 0) {
                AutoSimulation autoSimulation = new AutoSimulation(model.getGrid(), algoType);
                AutoSimulationView autoSimulationView = new AutoSimulationView(autoSimulation);
                new AutoSimulationController(autoSimulationView, autoSimulation);
                view.window.setContentPane(autoSimulationView);
            } else {
                ManualSimulation manualSimulation = new ManualSimulation(model.getGrid(), algoType);
                ManualSimulationView manualSimulationView = new ManualSimulationView(view.window, manualSimulation);
                new ManualSimulationController(manualSimulationView, manualSimulation);
                view.window.setContentPane(manualSimulationView);
            }
            view.window.validate();
        });

        buttons.add(this.startButton);

        boolean validGrid = this.model.getGrid().validate();
        this.exportButton.setEnabled(validGrid);
        this.startButton.setEnabled(validGrid);

        this.view.add(buttons, BorderLayout.NORTH);
    }


    /**
     * Process the click on a square
     * @param square The square clicked
     */
    private void edit(Square square) {
        if (square != null) {
            if (!this.edited) {
                this.edited = true;
                this.view.window.setPageTitle("*" + this.view.window.getPageTitle());
            }
            if (this.editMode == Mode.WALL) {
                if (square.isWall()) {
                    square.setEmpty();
                } else {
                    try {
                        square.setWall();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this.view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (this.editMode == Mode.THESEE) {
                try {
                    this.model.getGrid().getThesee().setSquare(square, true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this.view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else if (this.editMode == Mode.EXIT) {
                try {
                    square.setExit();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this.view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            boolean validGrid = this.model.getGrid().validate();
            this.exportButton.setEnabled(validGrid);
            this.startButton.setEnabled(validGrid);
            this.view.repaint();
        }
    }

    /**
     * Sets the edit mode.
     * @param mode The mode to set.
     */
    private void setEditMode(Mode mode) {
        this.editMode = mode;

        this.editTheseeButton.setEnabled(mode != Mode.THESEE);
        this.editExitButton.setEnabled(mode != Mode.EXIT);
        this.editWallButton.setEnabled(mode != Mode.WALL);
    }

    /**
     * Randomly fills the grid with an exit, Thésée and walls.
     */
    public void random() {
        try {
            Random rand = new Random();
            Grid gridModel = this.model.getGrid();

            gridModel.empty();

            gridModel.getSquare(rand.nextInt(gridModel.getSize()), rand.nextInt(gridModel.getSize())).setExit();
            try {
                gridModel.getThesee().setSquare(gridModel.getSquare(rand.nextInt(gridModel.getSize()), rand.nextInt(gridModel.getSize())));
            } catch (Exception ignored) {}

            for (int i = 0; i < gridModel.getSize(); i++) {
                for (int j = 0; j < gridModel.getSize(); j++) {
                    if (!gridModel.getSquare(i, j).isExit() && !gridModel.getSquare(i, j).isThesee() && rand.nextInt(3) == 0) gridModel.getSquare(i, j).setWall();
                }
            }

            // If the grid is invalid, try again.
            if (!gridModel.validate()) random();
            this.exportButton.setEnabled(true);
            this.startButton.setEnabled(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
