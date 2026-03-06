import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TerminalBufferTest {

    @Test
    void setup_test() {
        TerminalBuffer buffer = new TerminalBuffer(80, 24, 2000);

        assertEquals(80, buffer.getWidth());
        assertEquals(24, buffer.getHeight());
        assertEquals(2000, buffer.getScrollBackLimit());
        assertEquals(0, buffer.getCursorColumn());
        assertEquals(0, buffer.getCursorRow());
        assertEquals(0, buffer.getCursorRow());
        assertEquals(0, buffer.getScrollbackLineCount());
    }

    @Test
    void defaultAttributes_test(){
        TerminalBuffer buffer = new TerminalBuffer(80, 24, 10);

        assertEquals(CellAttributes.DEFAULT, buffer.getCurrentAttributes());
    }

    @Test
    void attributesSetAndUsed_test() {
        TerminalBuffer buffer = new TerminalBuffer(80, 24, 10);
        CellAttributes attrs = new CellAttributes(TerminalColor.RED, TerminalColor.BLUE, true, true, false);

        buffer.setCurrentAttributes(attrs);
        buffer.writeText("X");

        assertEquals(attrs, buffer.getCurrentAttributes());
        assertEquals(attrs, buffer.getAttributesAt(0, 0));
    }

    @Test
    void setCursorPositionAndRelativeMovementStayWithinScreenBounds_test() {
        TerminalBuffer buffer = new TerminalBuffer(5, 3, 10);

        buffer.setCursorPosition(4, 2);
        buffer.moveCursorRight(10);
        buffer.moveCursorDown(10);

        assertEquals(4, buffer.getCursorColumn());
        assertEquals(2, buffer.getCursorRow());

        buffer.moveCursorLeft(10);
        buffer.moveCursorUp(10);

        assertEquals(0, buffer.getCursorColumn());
        assertEquals(0, buffer.getCursorRow());
    }

    @Test
    void writeTextAndReadAt_test() {
        TerminalBuffer buffer = new TerminalBuffer(80, 24, 10);

        buffer.writeText("Hello");

        assertEquals('H', buffer.getCharacterAt(0, 0));
        assertEquals('e', buffer.getCharacterAt(0, 1));
        assertEquals('l', buffer.getCharacterAt(0, 2));
        assertEquals('l', buffer.getCharacterAt(0, 3));
        assertEquals('o', buffer.getCharacterAt(0, 4));
    }

    @Test
    void writeEmpty_test(){
        TerminalBuffer buffer = new TerminalBuffer(80, 24, 10);

        buffer.writeText("");

        assertEquals('\0', buffer.getCharacterAt(0, 0));
    }

    @Test
    void writeText_overwritesExistingCells_andMovesCursor() {
        TerminalBuffer buffer = new TerminalBuffer(6, 2, 10);

        buffer.writeText("abcdef");
        buffer.setCursorPosition(2, 0);
        buffer.writeText("ZZ");

        assertEquals("abZZef", buffer.getLineAsString(0));
        assertEquals(4, buffer.getCursorColumn());
        assertEquals(0, buffer.getCursorRow());
    }

    @Test
    void insertText_shiftsRight_andWraps_whenLineEnds() {
        TerminalBuffer buffer = new TerminalBuffer(5, 2, 10);

        buffer.writeText("12345");
        buffer.setCursorPosition(2, 0);
        buffer.insertText("ab");

        assertEquals("12ab3", buffer.getLineAsString(0));
        assertEquals("45   ", buffer.getLineAsString(1));
        assertEquals(4, buffer.getCursorColumn());
        assertEquals(0, buffer.getCursorRow());
    }

    @Test
    void fillCurrentLine_canFillWithCharacter_orClearToEmpty() {
        TerminalBuffer buffer = new TerminalBuffer(4, 2, 10);

        buffer.writeText("abcd");
        buffer.setCursorPosition(0, 0);
        buffer.fillCurrentLine('x');
        assertEquals("xxxx", buffer.getLineAsString(0));

        buffer.fillCurrentLine(null);
        assertEquals("    ", buffer.getLineAsString(0));
    }

    @Test
    void insertEmptyLineAtBottom_scrollsTopLineIntoScrollback() {
        TerminalBuffer buffer = new TerminalBuffer(3, 2, 10);

        buffer.writeText("111");
        buffer.setCursorPosition(0, 1);
        buffer.writeText("222");

        buffer.insertEmptyLineAtBottom();

        assertEquals(1, buffer.getScrollbackLineCount());
        assertEquals("111", buffer.getLineAsString(0));
        assertEquals("222", buffer.getLineAsString(1));
        assertEquals("   ", buffer.getLineAsString(2));
    }

    @Test
    void clearScreen_keepsScrollback_butResetsVisibleArea() {
        TerminalBuffer buffer = new TerminalBuffer(3, 2, 10);

        buffer.writeText("aaa");
        buffer.insertEmptyLineAtBottom();
        buffer.clearScreen();

        assertEquals(1, buffer.getScrollbackLineCount());
        assertEquals("   ", buffer.getLineAsString(1));
        assertEquals("   ", buffer.getLineAsString(2));
    }

    @Test
    void clearAll_resetsScreenAndScrollback() {
        TerminalBuffer buffer = new TerminalBuffer(3, 2, 10);

        buffer.writeText("aaa");
        buffer.insertEmptyLineAtBottom();
        buffer.clearAll();

        assertEquals(0, buffer.getScrollbackLineCount());
        assertEquals("   ", buffer.getLineAsString(0));
        assertEquals("   ", buffer.getLineAsString(1));
    }

    @Test
    void contentAccess_returnsCharactersAttributes_andJoinedContent() {
        TerminalBuffer buffer = new TerminalBuffer(4, 2, 10);
        CellAttributes attrs = new CellAttributes(TerminalColor.GREEN, TerminalColor.DEFAULT, false, true, true);

        buffer.setCurrentAttributes(attrs);
        buffer.writeText("Hi");

        assertEquals('H', buffer.getCharacterAt(0, 0));
        assertEquals('i', buffer.getCharacterAt(0, 1));
        assertEquals(attrs, buffer.getAttributesAt(0, 0));
        assertEquals("Hi  \n    ", buffer.getScreenAsString());
        assertEquals("Hi  \n    ", buffer.getAllContentAsString());
    }

    @Test
    void scrollbackIsCappedAtConfiguredMaximum() {
        TerminalBuffer buffer = new TerminalBuffer(2, 2, 2);

        buffer.writeText("aa");
        buffer.insertEmptyLineAtBottom();
        buffer.writeText("bb");
        buffer.insertEmptyLineAtBottom();
        buffer.writeText("cc");
        buffer.insertEmptyLineAtBottom();

        assertEquals(2, buffer.getScrollbackLineCount());
        assertEquals("bb", buffer.getLineAsString(0));
        assertEquals("cc", buffer.getLineAsString(1));
    }

    @Test
    void resize_changesDimensions_andPreservesBottomVisibleContent_whenPossible() {
        TerminalBuffer buffer = new TerminalBuffer(4, 3, 10);

        buffer.writeText("1111");
        buffer.setCursorPosition(0, 1);
        buffer.writeText("2222");
        buffer.setCursorPosition(0, 2);
        buffer.writeText("3333");

        buffer.resize(4, 2);

        assertEquals(4, buffer.getWidth());
        assertEquals(2, buffer.getHeight());
        assertEquals("2222", buffer.getLineAsString(0));
        assertEquals("3333", buffer.getLineAsString(1));
    }
}

