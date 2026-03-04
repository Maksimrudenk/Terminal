import java.util.ArrayDeque;
import java.util.Deque;

public class TerminalBuffer {

    private final Deque<Line> scrollBack;
    private final Line[] screen;
    private final int width;
    private final int height;
    private final int scrollBackLimit;
    private int CursorColumn;
    private int CursorRow;
    private CellAttributes currentAttributes = CellAttributes.DEFAULT;


    public TerminalBuffer(int width, int height, int scrollBackLimit) {
        this.width = width;
        this.height = height;
        this.scrollBackLimit = scrollBackLimit;
        this.scrollBack = new ArrayDeque<>(scrollBackLimit);
        this.screen = new Line[width];
        this.CursorColumn = 0;
        this.CursorRow = 0;
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
        return CursorColumn;
    }

    public int getCursorRow() {
        return CursorRow;
    }

    public void setCursorPosition(int column, int row) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void moveCursorUp(int cells) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void moveCursorDown(int cells) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void moveCursorLeft(int cells) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void moveCursorRight(int cells) {
        throw new UnsupportedOperationException("Not implemented yet");
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
