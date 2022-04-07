package view.components.ludemenodecomponent;

import view.panels.IGraphPanel;

import javax.swing.*;

public class NodePopupMenu extends JPopupMenu {
    public NodePopupMenu(LudemeNodeComponent nodeComponent, IGraphPanel graphPanel) {
        JMenuItem delete = new JMenuItem("Delete");
        add(delete);

        delete.addActionListener(e -> {
            graphPanel.removeNode(nodeComponent.getLudemeNode());
        });

    }
}
