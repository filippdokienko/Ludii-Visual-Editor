package view.panels.editor;

import view.panels.IGraphPanel;

import javax.swing.*;

public class EditorPopupMenu extends JPopupMenu {

    public EditorPopupMenu(IGraphPanel graphPanel) {
        JMenuItem newLudeme = new JMenuItem("New Ludeme");
        JMenuItem arrangeGraph = new JMenuItem("Arrange Graph");
        JMenuItem duplicateScreen = new JMenuItem("Duplicate Screen");

        newLudeme.addActionListener(e -> {
            graphPanel.showAllAvailableLudemes(getX(), getY());
                });

        duplicateScreen.addActionListener(e -> {
            JFrame frame = new JFrame("Define Editor");
            EditorPanel2 editorPanel2 = new EditorPanel2(5000,5000);
            frame.setContentPane(editorPanel2);
            editorPanel2.drawGraph(graphPanel.getGraph());
            frame.setVisible(true);
            frame.setPreferredSize(frame.getPreferredSize());
            frame.setSize(1200,800);
                });

        add(newLudeme);
        add(arrangeGraph);
        add(duplicateScreen);
    }

}
