package view.components.ludemenodecomponent;

import view.panels.IGraphPanel;

import javax.swing.*;

public class NodePopupMenu extends JPopupMenu {
    public NodePopupMenu(LudemeNodeComponent nodeComponent, IGraphPanel graphPanel) {
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem dynamic = new JMenuItem("(Un)Set Dynamic");
        add(delete);
        add(dynamic);

        delete.addActionListener(e -> {
            graphPanel.removeNode(nodeComponent.getLudemeNode());
        });

        dynamic.addActionListener(e -> {
            nodeComponent.changeDynamic();
        });

    }
}
