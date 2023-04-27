import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GridController {
    private Grid model;
    private GridView view;
    private enum Mode { DISABLED, WALL, THESEE, EXIT }
    private Mode editMode = Mode.DISABLED;
    private JButton editTheseeButton;
    private JButton editExitButton;
    private JButton editWallButton;

    public GridController(Grid model, GridView view) {
        this.model = model;
        this.view = view;

        this.view.setGrid(this.model);

        TheseeController theseeController = new TheseeController(this.model.getThesee(), this.view);

        this.view.setPreferredSize(new Dimension(700, 500));
        this.view.setBackground(Color.WHITE);

        this.view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Square square = view.click(e);

                if (square != null) {
                    if (editMode == Mode.WALL) {
                        if (square.isWall()) {
                            square.setEmpty();
                        } else {
                            square.setWall();
                        }
                        view.repaint();
                    } else if (editMode == Mode.THESEE) {
                        model.getThesee().setSquare(square);
                        view.repaint();
                    } else if (editMode == Mode.EXIT) {
                        square.setExit();
                        view.repaint();
                    }
                }
            }
        });

        editWallButton = new JButton("Enlever/Ajouter Murs");
        editWallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (editMode == Mode.DISABLED) {
                    editMode = Mode.WALL;
                    editWallButton.setText("Mode Dessin");
                    editTheseeButton.setEnabled(false);
                    editExitButton.setEnabled(false);
                } else {
                    editMode = Mode.DISABLED;
                    editWallButton.setText("Enlever/Ajouter Murs");
                    editTheseeButton.setEnabled(true);
                    editExitButton.setEnabled(true);
                }
            }
        });
        this.view.add(editWallButton);

        editTheseeButton = new JButton("Placer Joueur");
        editTheseeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (editMode == Mode.DISABLED) {
                    editMode = Mode.THESEE;
                    editTheseeButton.setText("Mode Dessin");
                    editWallButton.setEnabled(false);
                    editExitButton.setEnabled(false);
                } else {
                    editMode = Mode.DISABLED;
                    editTheseeButton.setText("Placer Joueur");
                    editWallButton.setEnabled(true);
                    editExitButton.setEnabled(true);
                }
            }
        });
        this.view.add(editTheseeButton);

        editExitButton = new JButton("Placer Sortie");
        editExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (editMode == Mode.DISABLED) {
                    editMode = Mode.EXIT;
                    editExitButton.setText("Mode Dessin");
                    editWallButton.setEnabled(false);
                    editTheseeButton.setEnabled(false);
                } else {
                    editMode = Mode.DISABLED;
                    editExitButton.setText("Placer Sortie");
                    editWallButton.setEnabled(true);
                    editTheseeButton.setEnabled(true);
                }
            }
        });
        this.view.add(editExitButton);
    }

    /**
     * Randomly fills the grid with walls, an exit and Thésée
     */
    public void random() {
        try {
            Random rand = new Random();
            for (int i = 0; i < this.model.getSize(); i++) {
                for (int j = 0; j < this.model.getSize(); j++) {
                    if (rand.nextInt(3) == 0) this.model.getSquare(i, j).setWall();
                }
            }

            this.model.getSquare(rand.nextInt(this.model.getSize()), rand.nextInt(this.model.getSize())).setExit();

            this.model.getThesee().setSquare(this.model.getSquare(rand.nextInt(this.model.getSize()), rand.nextInt(this.model.getSize())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GridView getView() {
        return this.view;
    }
}
