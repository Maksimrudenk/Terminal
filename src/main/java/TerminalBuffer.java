import java.util.ArrayDeque;
import java.util.Deque;

public class TerminalBuffer {

    private final Deque<Line> scrollBack;
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
        this.scrollBack = new ArrayDeque<>(scrollBackLimit);
        this.screen = new Line[width];
        this.cursorColumn = 0;
        this.cursorRow = 0;
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
        throw new UnsupportedOperationException("Not implemented yet");
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void insertText(String text) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void fillCurrentLine(Character fillChar) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void insertEmptyLineAtBottom() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void clearScreen() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void clearAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Character getCharacterAt(int globalRow, int column) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public CellAttributes getAttributesAt(int globalRow, int column) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getLineAsString(int globalRow) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getScreenAsString() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getAllContentAsString() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getScrollbackLineCount() {
        return scrollBack.size();
    }

    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
