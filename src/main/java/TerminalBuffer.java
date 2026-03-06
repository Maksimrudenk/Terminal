import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class TerminalBuffer {

    private final LinkedList<Line> scrollBack;
    private final Line[] screen;
    private final int width;
    private final int height;
    private final int scrollBackLimit;
    private int cursorColumn;
    private int cursorRow;
    private CellAttributes currentAttributes = CellAttributes.DEFAULT;


    public TerminalBuffer(int width, int height, int scrollBackLimit) {
        this.width = width;
        this.height = height;
        this.scrollBackLimit = scrollBackLimit;
        this.cursorColumn = 0;
        this.cursorRow = 0;
        this.scrollBack = new LinkedList<Line>();
        this.screen = new Line[height];

        for (int i = 0; i < height; i++) {
            screen[i] = new Line(width);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScrollBackLimit() {
        return scrollBackLimit;
    }

    public void setCurrentAttributes(CellAttributes attributes) {
        currentAttributes = attributes;
    }

    public CellAttributes getCurrentAttributes() {
        return currentAttributes;
    }

    public int getCursorColumn() {
        return cursorColumn;
    }

    public int getCursorRow() {
        return cursorRow;
    }

    public void setCursorPosition(int column, int row) {
        if (column < 0) column = 0;
        else if (column >= width) column = width - 1;

        if (row < 0) row = 0;
        else if (row >= height) row = height - 1;

        cursorColumn = column;
        cursorRow = row;
    }

    public void moveCursorUp(int cells) {
        setCursorPosition(cursorColumn - cells, cursorRow);
    }

    public void moveCursorDown(int cells) {
        setCursorPosition(cursorColumn + cells, cursorRow);
    }

    public void moveCursorLeft(int cells) {
        setCursorPosition(cursorColumn, cursorRow - cells);
    }

    public void moveCursorRight(int cells) {
        setCursorPosition(cursorColumn, cursorRow + cells);
    }

    public void writeText(String text) {
        char[] chars = text.toCharArray();
        for (char c : chars) {
            writeChar(c);
        }
    }

    private boolean writeChar(char c) {
        screen[cursorRow].setValueAt(c, cursorColumn);
        screen[cursorRow].setAttributesAt(currentAttributes, cursorColumn);
        if (cursorColumn == width-1){
            cursorRow++;
            cursorColumn = 0;
            return true; // wrapped
        }
        cursorColumn++;
        return false; // not wrapped
    }

    public void insertText(String text) {
        int shift = cursorColumn + text.length();
        int returnRow = shift / width;
        int returnColumn = shift % width;

        Deque<Character> queue = new ArrayDeque<>();
        char[] chars = text.toCharArray();
        for (char c : chars) {
            queue.add(c);
        }

        while (!queue.isEmpty()) {
            char c = getCharacterAt(cursorRow,cursorColumn);
            if (c != '\0') queue.add(c);
            writeChar(queue.remove());
        }

        cursorRow = returnRow;
        cursorColumn = returnColumn;
    }

    public void fillCurrentLine(Character fillChar) {
        if (fillChar == null) fillChar = '\0';
        screen[cursorRow].fillLineWith(fillChar);
    }

    public void insertNewLine() {
        scrollBack.add(screen[0]);
        if (scrollBack.size() > scrollBackLimit) scrollBack.remove();
        for (int i = 0; i < height-1; i++) {
            screen[i] = screen[i + 1];
        }
        screen[height-1] = new Line(width);
        cursorRow = height - 1;
        cursorColumn = 0;
    }

    public void clearScreen() {
        for (int i = 0; i < height; i++) {
            screen[i] = new Line(width);
        }
        cursorColumn = 0;
        cursorRow = 0;
    }

    public void clearAll() {
        clearScreen();
        scrollBack.clear();
    }

    public Character getCharacterAt(int row, int column) {
        return screen[row].getValueAt(column);
    }

    public CellAttributes getAttributesAt(int row, int column) {
        return screen[row].getAttributesAt(column);
    }

    public String getLineAsString(int row) {
        return screen[row].asString();
    }

    public String getScreenAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height-1; i++) {
            sb.append(screen[i].asString());
            sb.append("\n");
        }
        sb.append(screen[height-1].asString());
        return sb.toString();
    }

    public String getAllContentAsString() {
        StringBuilder sb = new StringBuilder();
        for (Line line: scrollBack) {
            sb.append(line.asString());
            sb.append("\n");
        }
        sb.append(getScreenAsString());
        return sb.toString();
    }

    public int getScrollbackLineCount() {
        return scrollBack.size();
    }

    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getScrollBackLineAsString(int row){
        return scrollBack.get(row).asString();
    }

    public Character getScrollBackCharacterAt(int row, int column) {
        return scrollBack.get(row).getValueAt(column);
    }

}
