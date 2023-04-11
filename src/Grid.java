import java.util.Arrays;

public class Grid {   
    private Square[][] grid;

    public Grid(int taille) {
        this.grid = new Square[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.grid[i][j] = new Square(i, j);
            }
        }
    }

    public void setWall(int x, int y) throws ArrayIndexOutOfBoundsException {
        this.grid[x][y].setWall();
    }

    public void setExit(int x, int y) throws ArrayIndexOutOfBoundsException {
        for (Square[] line : this.grid) {
            for (Square square : line) {
                if (square.isExit()) square.setEmpty();
            }
        }
        this.grid[x][y].setExit();
    }

    public int getRows() {
        return this.grid.length;
    }

    public int getColumns() {
        return this.grid[0].length;
    }

    public boolean isWall(int x, int y) {
        return this.grid[x][y].isWall();
    }

    public boolean isExit(int x, int y) {
        return this.grid[x][y].isEmpty();
    }
}