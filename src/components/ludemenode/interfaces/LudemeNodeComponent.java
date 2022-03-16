package components.ludemenode.interfaces;

import grammar.Ludeme;
import model.LudemeNode;
import panels.editor.EditorPanel;

import javax.swing.*;

public abstract class LudemeNodeComponent extends JComponent implements ILudemeNodeComponent {

    protected int x, y;
    protected final Ludeme LUDEME;
    public final EditorPanel EDITOR_PANEL;

    private final LudemeNode LUDEME_NODE;


    public LudemeNodeComponent(LudemeNode ludemeNode, EditorPanel editorPanel){
        this.LUDEME_NODE = ludemeNode;
        this.LUDEME = ludemeNode.getLudeme();
        this.EDITOR_PANEL = editorPanel;
        this.x = (int) ludemeNode.getPos().getX();
        this.y = (int) ludemeNode.getPos().getY();
    }

    public LudemeNodeComponent(int x, int y, Ludeme ludeme, EditorPanel editorPanel){
        this.x = x;
        this.y = y;
        this.EDITOR_PANEL = editorPanel;
        this.LUDEME = ludeme;
        this.LUDEME_NODE = null;
    }
}
