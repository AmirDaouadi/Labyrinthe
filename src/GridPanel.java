import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;

public class GridPanel extends JPanel {
    private int gridSize;
    private int cellSize = 50;
    private Color[][] gridColors;
    private boolean editMode = false;
    private boolean placeJoueurMode = false;
    private boolean placeTMode = false;
    private Point joueurPos = null;
    private Point tPos = null;
    private JButton placeJoueurButton;
    private JButton placeTButton;
    private JButton editButton;

    public GridPanel(int gs) {
        this.gridSize = gs;
        setPreferredSize(new Dimension(gridSize * cellSize, gridSize * cellSize));
        setBackground(Color.WHITE);
        
        gridColors = new Color[gridSize][gridSize];
        Random rand = new Random();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridColors[i][j] = rand.nextBoolean() ? Color.BLACK : Color.WHITE;
            }
        }
    
        
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / cellSize;
                int col = e.getX() / cellSize;
                if (editMode) {
                    gridColors[row][col] = gridColors[row][col] == Color.BLACK ? Color.WHITE : Color.BLACK;
                    repaint();
                } else if (placeJoueurMode) {
                    joueurPos = new Point(col, row);
                    repaint();
                } else if (placeTMode) {
                    tPos = new Point(col, row);
                    repaint();
                }
            }
        });
        
        editButton = new JButton("Enlever/Ajouter Murs");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editMode = !editMode;
                if (editMode) {
                    editButton.setText("Mode Auditeur");
                    placeJoueurButton.setEnabled(false);
                    placeTButton.setEnabled(false);
                } else {
                    editButton.setText("Enlever/Ajouter Murs");
                    placeJoueurButton.setEnabled(true);
                    placeTButton.setEnabled(true);
                }
            }
        });
        add(editButton);
        
        placeJoueurButton = new JButton("Placer Thésée");
        placeJoueurButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                placeJoueurMode = !placeJoueurMode;
                if (placeJoueurMode) {
                    placeJoueurButton.setText("Mode Auditeur");
                    editButton.setEnabled(false);
                    placeTButton.setEnabled(false);
                } else {
                    placeJoueurButton.setText("Placer Thésée");
                    editButton.setEnabled(true);
                    placeTButton.setEnabled(true);
                }
            }
        });
        add(placeJoueurButton);
        
        placeTButton = new JButton("Placer Sortie");
        placeTButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                placeTMode = !placeTMode;
                if (placeTMode) {
                    placeTButton.setText("Mode Auditeur");
                    editButton.setEnabled(false);
                    placeJoueurButton.setEnabled(false);
                } else {
                    placeTButton.setText("Placer Sortie");
                    editButton.setEnabled(true);
                    placeJoueurButton.setEnabled(true);
                }
            }
        });
        add(placeTButton);        
    }
    

public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    // Draw grid cells
    for (int i = 0; i < gridSize; i++) {
        for (int j = 0; j < gridSize; j++) {
            g.setColor(gridColors[i][j]);
            g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
        }
    }
    
    // Draw horizontal lines
    g.setColor(Color.BLACK);
    for (int i = 1; i < gridSize; i++) {
        int y = i * cellSize;
        g.drawLine(0, y, getWidth(), y);
    }
    
    // Draw vertical lines
    for (int j = 1; j < gridSize; j++) {
        int x = j * cellSize;
        g.drawLine(x, 0, x, getHeight());
    }
    
    // Draw player and T
    if (joueurPos != null){
        g.setColor(Color.BLUE);
        g.fillOval(joueurPos.x * cellSize, joueurPos.y * cellSize, cellSize, cellSize);
    }
    if (tPos != null) {
        g.setColor(Color.RED);
        g.fillRect(tPos.x * cellSize, tPos.y * cellSize, cellSize, cellSize);
    }
}

    
/*public static void main(String[] args) {
    JFrame frame = new JFrame("GridPanel");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BorderLayout());
    GridPanel gridPanel = new GridPanel();
    frame.getContentPane().add(gridPanel, BorderLayout.CENTER);
    frame.getContentPane().add(gridPanel.getComponents()[0], BorderLayout.NORTH);
    frame.pack();
    frame.setVisible(true);
}*/
}
