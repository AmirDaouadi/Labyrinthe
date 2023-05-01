/**
 * Class used to edit the grid model
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class Editor {
    /**
     * The grid model
     */
    private final Grid gridModel;

    /**
     * Constructor
     * @param gridModel The grid model
     */
    public Editor(Grid gridModel) {
        this.gridModel = gridModel;
    }

    /**
     * Get the grid model
     * @return The grid model
     */
    public Grid getGrid() {
        return this.gridModel;
    }
}
