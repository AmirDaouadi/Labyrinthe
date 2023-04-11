public class GridController {
    private Grid model;
    private GridView view;

    public GridController(Grid model, GridView view) {
        this.model = model;
        this.view = view;
    }

    public void setGridExit(int x, int y) {
        this.model.setExit(x, y);
    }

    public void setGridWall(int x, int y) {
        this.model.setWall(x, y);
    }

    public boolean isGridWall(int x, int y) {
        return this.model.isWall(x, y);
    }

    public boolean isGridExit(int x, int y) {
        return this.model.isExit(x, y);
    }

    public void updateView() {
        this.view.printGridInfo(this.model.getRows(), this.model.getColumns());
    }
}
