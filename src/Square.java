public class Square {
    private final int row;
    private final int column;
    private int type = 0;

    public Square(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean isWall() {
        return this.type == 1;
    }

    public boolean isExit() {
        return this.type == 2;
    }

    public boolean isEmpty() {
        return this.type == 0;
    }

    public void setWall() {
        this.type = 1;
    }

    public void setExit() {
        this.type = 2;
    }

    public void setEmpty() {
        this.type = 0;
    }
}
