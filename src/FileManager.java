import java.io.*;
/**
 * Class to manage file import/export
 * @version 1.1
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class FileManager {
    /**
     * Import a grid from a file
     * @param file The file to import
     * @return The imported grid
     * @throws Exception If an error occurs during the import
     */
    public static Grid importGrid(File file) throws Exception {
        Grid grid;
        try {
            FileInputStream fs = new FileInputStream(file);
            DataInputStream ds = new DataInputStream(fs);
            try {
                grid = new Grid(ds.read());
                grid.getThesee().setSquare(grid.getSquare(ds.read(), ds.read()));
                grid.getSquare(ds.read(), ds.read()).setExit();

                int bit = 8;
                int value = 0;
                for (int i = 0; i < grid.getSize(); i++) {
                    for (int j = 0; j < grid.getSize(); j++) {
                        if (bit == 8) {
                            value = ds.read();
                            bit = 0;
                        }
                        if (((value >> (7 - bit)) & 1) == 1) grid.getSquare(j, i).setWall();
                        bit++;
                    }
                }
                ds.close();
            } catch (Exception e) {
                throw new Exception("Une erreur est survenue lors de la lecture du fichier.");
            }
            return grid;
        } catch (FileNotFoundException e){
            throw new Exception("Fichier non trouvé.");
        }
    }

    /**
     * Export a grid to a file
     * @param grid The grid to export
     * @param file The file to export to
     * @throws Exception If an error occurs during the export
     */
    public static void exportGrid(Grid grid, File file) throws Exception {
        try {
            FileOutputStream fs = new FileOutputStream(file);
            DataOutputStream ds = new DataOutputStream(fs);
            try {
                // Écriture de la taille de la grille
                ds.writeByte(grid.getSize());

                // Écriture de la position de Thésée
                Square theseeSquare = grid.getThesee().getSquare();
                ds.writeByte(theseeSquare.getRow());
                ds.writeByte(theseeSquare.getColumn());

                // Écriture de la position de la sortie
                for (int i = 0; i < grid.getSize(); i++) {
                    for (int j = 0; j < grid.getSize(); j++) {
                        Square square = grid.getSquare(i, j);
                        if (square.isExit()) {
                            ds.writeByte(square.getRow());
                            ds.writeByte(square.getColumn());
                            break;
                        }
                    }
                }

                // Écriture des murs
                int bit = 0;
                byte value = 0;
                for (int i = 0; i < grid.getSize(); i++) {
                    for (int j = 0; j < grid.getSize(); j++) {
                        Square square = grid.getSquare(j, i);
                        if (square.isWall()) {
                            value |= 1 << (7 - bit);
                        }
                        bit++;
                        if (bit == 8) {
                            ds.writeByte(value);
                            value = 0;
                            bit = 0;
                        }
                    }
                }
                if (bit != 0) {
                    ds.writeByte(value);
                }
            } catch (Exception e) {
                throw new Exception("Une erreur est survenue lors de l'écriture du fichier.");
            }
        } catch (FileNotFoundException e){
            throw new Exception("Fichier non trouvé.");
        }
    }
}
