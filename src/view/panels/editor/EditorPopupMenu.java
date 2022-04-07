package view.panels.editor;

import view.panels.IGraphPanel;

import javax.swing.*;

public class EditorPopupMenu extends JPopupMenu {

    public EditorPopupMenu(IGraphPanel graphPanel) {
        JMenuItem newLudeme = new JMenuItem("New Ludeme");
        JMenuItem arrangeGraph = new JMenuItem("Arrange Graph");

        newLudeme.addActionListener(e -> {
            graphPanel.showAllAvailableLudemes(getX(), getY());
                });

        add(newLudeme);
        add(arrangeGraph);
    }

}
