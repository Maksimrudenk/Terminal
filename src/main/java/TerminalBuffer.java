public class TerminalBuffer {

    public TerminalBuffer(int width, int height, int scrollbackMaxLines) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getWidth() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getHeight() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getScrollbackMaxLines() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void setCurrentAttributes(CellAttributes attributes) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public CellAttributes getCurrentAttributes() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getCursorColumn() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getCursorRow() {
        throw new UnsupportedOperationException("Not implemented yet");
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
