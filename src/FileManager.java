import java.io.*;

public class FileManager {
    public static Grid importGrid(File file) throws Exception {
        Grid grid;
        try {
            FileInputStream fs = new FileInputStream(file);
            DataInputStream ds = new DataInputStream(fs);
            try {
                grid = new Grid(ds.readByte());
                grid.getThesee().setSquare(grid.getSquare(ds.readByte(), ds.readByte()));
                grid.getSquare(ds.readByte(), ds.readByte()).setExit();

                int bit = 8;
                byte value = 0;
                for (int i = 0; i < grid.getSize(); i++) {
                    for (int j = 0; j < grid.getSize(); j++) {
                        if (bit == 8) {
                            value = ds.readByte();
                            bit = 0;
                        }
                        if (((value >> (7 - bit)) & 1) == 1) grid.getSquare(i, j).setWall();
                        bit++;
                    }
                }
                ds.close();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new Exception("Le fichier est corrompu.");
            } catch (IOException e) {
                throw new Exception("Une erreur est survenue lors de la lecture du fichier.");
            }
            return grid;
        } catch (FileNotFoundException e){
            throw new Exception("Fichier non trouvé.");
        }
    }

    public static void exportGrid(Grid grid, File file) throws Exception {
        try (FileOutputStream fs = new FileOutputStream(file);
             DataOutputStream ds = new DataOutputStream(fs)) {

            // Écriture de la taille de la grille
            ds.writeByte(grid.getSize());

            // Écriture de la position de Thésée
            Square theseeSquare = grid.getThesee().getSquare();
            ds.writeByte(theseeSquare.getRow());
            ds.writeByte(theseeSquare.getColumn());

            // Écriture de la position de la sortie
            for (int i = 0; i < grid.getSize(); i++) {
                for (int j = 0; j < grid.getSize(); j++) {
                    Square square = grid.getSquare(i,j);
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
                    Square square = grid.getSquare(i, j);
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
        } catch (IOException e) {
            throw new Exception("Une erreur est survenue lors de l'écriture du fichier.");
        }
    }

    // Test (à retirer)
    public static void main(String[] args) throws Exception {
        Grid grid = importGrid(new File("./src/petit.lab"));

        System.out.println("Grille " + grid.getSize() + "x" + grid.getSize() + " :");
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                Square square = grid.getSquare(i, j);
                System.out.println("\tCase " + i + "x" + j + " : isEmpty:" + square.isEmpty() + ", isWall:" + square.isWall() + ", isExit:" + square.isExit() + ", isThesee:" + square.isThesee());
            }
        }

        exportGrid(grid, new File("./src/petit2.lab"));
    }
}
