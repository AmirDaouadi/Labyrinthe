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
     * If the simulation has been successful or not
     */
    private boolean success = false;

    /**
     * Get the number of moves of the simulation
     * @return The number of moves
     */
    public int getMoves() {
        return this.moves;
    }

    /**
     * Get if the simulation has been successful or not
     * @return If the simulation has been successful or not
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * Add a move to the simulation
     */
    public void addMove() {
        this.moves++;
    }

    /**
     * Set if the simulation has been successful or not
     * @param success If the simulation has been successful or not
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
