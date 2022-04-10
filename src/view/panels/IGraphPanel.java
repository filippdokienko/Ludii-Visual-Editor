package view.panels;

import LayoutManagement.LayoutManager.LayoutHandler;
import model.DescriptionGraph;
import model.LudemeNode;
import model.grammar.Ludeme;
import view.components.ludemenodecomponent.LudemeNodeComponent;
import view.components.ludemenodecomponent.inputs.LConnectionComponent;
import view.components.ludemenodecomponent.inputs.LIngoingConnectionComponent;

public interface IGraphPanel {
    void drawGraph(DescriptionGraph graph);
    DescriptionGraph getGraph();
    void startNewConnection(LConnectionComponent source);
    void cancelNewConnection();
    void addConnection(LConnectionComponent source, LIngoingConnectionComponent target);
    LudemeNodeComponent getNodeComponent(LudemeNode node);
    LudemeNode addNode(Ludeme ludeme, int x, int y, boolean connect);
    void showAllAvailableLudemes(int x, int y);
    void removeAllConnections(LudemeNode node);
    void removeConnection(LudemeNode node, LConnectionComponent connection);
    void clickedOnNode(LudemeNode node);
    void removeNode(LudemeNode node);
    LayoutHandler getLayoutHandler();
    void repaint();
}
