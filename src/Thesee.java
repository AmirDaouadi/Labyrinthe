public class Thesee {
    private Square square;
    private Square intialSquare;

    public void setSquare(Square square) throws Exception {
        if (square.isExit()) throw new Exception("Vous ne pouvez pas placer Thesee sur la même case que la sortie. Déplacez d'abord la sortie puis réessayez.");
        square.setEmpty();
        this.square = square;
        if (this.intialSquare == null) this.intialSquare = this.square;
    }

    public Square getSquare() {
        return this.square;
    }

    public void reset() {
        this.square = this.intialSquare;
    }
}
