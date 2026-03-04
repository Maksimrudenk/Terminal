import java.util.Objects;

public class CellAttributes {
    private final TerminalColor foreground;
    private final TerminalColor background;
    private final boolean bold;
    private final boolean italic;
    private final boolean underline;

    public static final CellAttributes DEFAULT = new CellAttributes(TerminalColor.BLACK,
            TerminalColor.BLACK,
            false,
            false,
            false);


    public CellAttributes(TerminalColor foreground,
                          TerminalColor background,
                          boolean bold,
                          boolean italic,
                          boolean underline) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public TerminalColor getForeground() {
        return foreground;
    }

    public TerminalColor getBackground() {
        return background;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellAttributes that = (CellAttributes) o;
        return isBold() == that.isBold() && isItalic() == that.isItalic() && isUnderline() == that.isUnderline() && getForeground() == that.getForeground() && getBackground() == that.getBackground();
    }

    @Override
    public String toString() {
        return "CellAttributes{" +
                "foreground=" + foreground +
                ", background=" + background +
                ", bold=" + bold +
                ", italic=" + italic +
                ", underline=" + underline +
                '}';
    }
}
