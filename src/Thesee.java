public class Thesee {
    private Square square;
    private Square intialSquare;

    public void setSquare(Square square) {
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
