/**
 * GridController
 * @version 1.0
 * @author Amir Daouadi
 * @author Lyanis Souidi
 */
public class GridController {
    /**
     * Constructor
     * @param model The grid
     * @param view The grid's view
     */
    public GridController(Grid model, GridView view) {
        view.setGrid(model);
        new TheseeController(model.getThesee(), view);
    }
}
