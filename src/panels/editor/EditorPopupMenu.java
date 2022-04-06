package panels.editor;

import javax.swing.*;

public class EditorPopupMenu extends JPopupMenu {

    public EditorPopupMenu(){
        JMenuItem newLudeme = new JMenuItem("New Ludeme");
        JMenuItem arrangeGraph = new JMenuItem("Arrange Graph");

        add(newLudeme);
        add(arrangeGraph);
    }

}
