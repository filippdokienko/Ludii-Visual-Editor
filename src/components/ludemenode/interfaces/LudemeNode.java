package components.ludemenode.interfaces;

import LayoutManagement.Math.Vector2D;
import LayoutManagement.interfaces.iGNode;
import grammar.Ludeme;
import panels.editor.EditorPanel;

import javax.swing.*;
import java.util.List;

public abstract class LudemeNode extends JComponent implements ILudemeNode, iGNode {

    protected int x, y;
    protected final Ludeme LUDEME;

    public final EditorPanel EDITOR_PANEL;

    public LudemeNode(int x, int y, Ludeme ludeme, EditorPanel editorPanel){
        this.x = x;
        this.y = y;
        this.EDITOR_PANEL = editorPanel;
        this.LUDEME = ludeme;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public List<iGNode> getChildren() {
        return null;
    }

    @Override
    public List<iGNode> getSiblings() {
        return null;
    }

    @Override
    public Vector2D getPos() {
        return null;
    }

    @Override
    public void setPos() {

    }
}
