package LayoutManagement.GraphDrawing.MetaGraph;

import LayoutManagement.Math.Vector2D;
import java.awt.*;
import static LayoutManagement.VisualEditor.LayoutConfigs.NODE_SIZE;

/**
 * Stores information about edge endpoints and its graphics
 * @author nic0gin
 */

public class Edge {

    private final int nodeA;
    private final int nodeB;

    public Edge(int nodeA, int nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public void drawEdge(Graphics2D g2d) {
        Graph graph = Graph.getGraphInstance();
        Vector2D A = graph.getNodePos(nodeA);
        Vector2D B = graph.getNodePos(nodeB);
        g2d.setColor(Color.GRAY);
        g2d.drawLine((int)(A.getScreenTransX()+(NODE_SIZE*0.5)),
                (int)(A.getScreenTransY()+(NODE_SIZE*0.5)),
                (int)(B.getScreenTransX()+(NODE_SIZE*0.5)),
                (int)(B.getScreenTransY()+(NODE_SIZE*0.5)));
    }

    public Node getNodeA() {
        return Graph.getGraphInstance().getNode(nodeA);
    }

    public Node getNodeB() {
        return Graph.getGraphInstance().getNode(nodeB);
    }

}
