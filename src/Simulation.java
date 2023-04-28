/**
 * This class is used to store the number of moves and if the simulation was successful or not
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class Simulation {
    /**
     * The number of moves of the simulation
     */
    private int moves = 0;

    /**
     * If the simulation has ended or not
     */
    private boolean end = false;

    /**
     * Get the number of moves of the simulation
     * @return The number of moves
     */
    public int getMoves() {
        return this.moves;
    }

    /**
     * Check if the simulation has ended or not
     * @return If the simulation has ended or not
     */
    public boolean isEnded() {
        return this.end;
    }

    /**
     * Set the simulation as ended
     */
    public void setEnded() {
        this.end = true;
    }

    /**
     * Add a move to the simulation
     */
    public void addMove() {
        this.moves++;
    }

}
