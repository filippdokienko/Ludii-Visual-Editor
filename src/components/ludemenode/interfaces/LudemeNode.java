package components.ludemenode.interfaces;

import grammar.Ludeme;
import panels.editor.EditorPanel;

import javax.swing.*;

public abstract class LudemeNode extends JComponent implements ILudemeNode {

    protected int x, y;
    protected final Ludeme LUDEME;

    public final EditorPanel EDITOR_PANEL;

    public LudemeNode(int x, int y, Ludeme ludeme, EditorPanel editorPanel){
        this.x = x;
        this.y = y;
        this.EDITOR_PANEL = editorPanel;
        this.LUDEME = ludeme;
    }
}
