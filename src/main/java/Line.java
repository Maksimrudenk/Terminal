public class Line {

    private final Cell[] cells;

    private static class Cell {
        private char value;
        private CellAttributes attributes;
    }

    public Line(int width) {
        cells = new Cell[width];
    }

    public Cell getCellAt(int index){
        return cells[index];
    }

}
