public class Line {

    private final Cell[] cells;

    private static class Cell {
        private char value;
        private CellAttributes attributes;

        private Cell(){
            value = 0;
            attributes = CellAttributes.DEFAULT;
        }
    }

    public Line(int width) {
        cells = new Cell[width];

        for (int i = 0; i < width; i++) {
            cells[i] = new Cell();
        }
    }

    public char getValueAt(int index){
        return cells[index].value;
    }

    public void setValueAt(char value, int index){
        cells[index].value = value;
    }

    public CellAttributes getAttributesAt(int index){
        return cells[index].attributes;
    }

    public void setAttributesAt(CellAttributes attributes, int index){
        cells[index].attributes = attributes;
    }

    public void fillLineWith(char value){
        int width = cells.length;
        for (int i = 0; i < width; i++) {
            cells[i].value = value;
        }
    }

    public String asString(){
        StringBuilder sb = new StringBuilder();
        for (Cell cell : cells) {
            sb.append(cell.value);
        }
        return sb.toString();
    }

}
