package view.panels.editor;

import view.components.ludemenodecomponent.inputs.LConnectionComponent;

public interface IEditorPanel {
    public void startNewConnection(LConnectionComponent source);
    public void cancelNewConnection();
    public void finishNewConnection();
}
