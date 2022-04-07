package view.panels;

import model.DescriptionGraph;
import model.LudemeNode;
import model.grammar.Ludeme;
import view.components.ludemenodecomponent.LudemeNodeComponent;
import view.components.ludemenodecomponent.inputs.LConnectionComponent;
import view.components.ludemenodecomponent.inputs.LIngoingConnectionComponent;

public interface IGraphPanel {
    public void drawGraph(DescriptionGraph graph);
    public DescriptionGraph getGraph();
    public void startNewConnection(LConnectionComponent source);
    public void cancelNewConnection();
    public void addConnection(LConnectionComponent source, LIngoingConnectionComponent target);
    public LudemeNodeComponent getNodeComponent(LudemeNode node);
    public void addNode(Ludeme ludeme, int x, int y, boolean connect);
    public void showAllAvailableLudemes(int x, int y);
    public void removeConnections(LudemeNode node);
    public void clickedOnNode(LudemeNode node);
    public void removeNode(LudemeNode node);
}
