import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class EditorController extends GridController {
    private Editor model;
    private EditorView view;
    private enum Mode { DISABLED, WALL, THESEE, EXIT }
    private Mode editMode = Mode.DISABLED;
    private JButton editTheseeButton = new JButton("Placer Joueur");
    private JButton editExitButton = new JButton("Placer Sortie");
    private JButton editWallButton = new JButton("Enlever/Ajouter Murs");

    public EditorController(Editor model, EditorView view) {
        super(model.getGrid(), view);
        this.model = model;
        this.view = view;

        this.view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                edit(view.click(e));
            }
        });

        JPanel buttons = new JPanel();
        
        editWallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (editMode == Mode.DISABLED) {
                    editWallButton.setText("Mode Dessin");
                    setEditMode(Mode.WALL);
                } else {
                    editWallButton.setText("Enlever/Ajouter Murs");
                    setEditMode(Mode.DISABLED);
                }
            }
        });
        buttons.add(editWallButton);
        
        editTheseeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (editMode == Mode.DISABLED) {
                    editTheseeButton.setText("Mode Dessin");
                    setEditMode(Mode.THESEE);
                } else {
                    editTheseeButton.setText("Placer Joueur");
                    setEditMode(Mode.DISABLED);
                }
            }
        });
        buttons.add(editTheseeButton);

        editExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (editMode == Mode.DISABLED) {
                    editExitButton.setText("Mode Dessin");
                    setEditMode(Mode.EXIT);
                } else {
                    editExitButton.setText("Placer Sortie");
                    setEditMode(Mode.DISABLED);
                }
            }
        });
        buttons.add(editExitButton);
        this.view.add(buttons, BorderLayout.NORTH);
    }

    private void edit(Square square) {
        if (square != null) {
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
                this.view.repaint();
            } else if (this.editMode == Mode.THESEE) {
                try {
                    this.model.getGrid().getThesee().setSquare(square);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this.view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                this.view.repaint();
            } else if (this.editMode == Mode.EXIT) {
                try {
                    square.setExit();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this.view, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                this.view.repaint();
            }
        }
    }

    /**
     * Sets the edit mode.
     * @param mode The mode to set.
     */
    private void setEditMode(Mode mode) {
        this.editMode = mode;
        if (mode != Mode.DISABLED) {
            this.editTheseeButton.setEnabled(mode == Mode.THESEE);
            this.editExitButton.setEnabled(mode == Mode.EXIT);
            this.editWallButton.setEnabled(mode == Mode.WALL);
        } else {
            this.editTheseeButton.setEnabled(true);
            this.editExitButton.setEnabled(true);
            this.editWallButton.setEnabled(true);
        }
    }

    /**
     * Randomly fills the grid with an exit, Thésée and walls.
     */
    public void random() {
        try {
            Random rand = new Random();
            Grid gridModel = this.model.getGrid();

            gridModel.getSquare(rand.nextInt(gridModel.getSize()), rand.nextInt(gridModel.getSize())).setExit();
            gridModel.getThesee().setSquare(gridModel.getSquare(rand.nextInt(gridModel.getSize()), rand.nextInt(gridModel.getSize())));

            for (int i = 0; i < gridModel.getSize(); i++) {
                for (int j = 0; j < gridModel.getSize(); j++) {
                    if (!gridModel.getSquare(i, j).isExit() && !gridModel.getSquare(i, j).isThesee() && rand.nextInt(3) == 0) gridModel.getSquare(i, j).setWall();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
