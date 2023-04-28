public class GridController {
    public GridController(Grid model, GridView view) {
        view.setGrid(model);
        new TheseeController(model.getThesee(), view);
    }
}
