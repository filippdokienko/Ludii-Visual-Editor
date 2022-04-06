package LayoutManagement;

import LayoutManagement.Math.Vector2D;
import model.interfaces.iGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph manipulation procedures
 * @author nic0gin
 */
public final class GraphRoutines
{

    /**
     * Update of depth for graph nodes by BFS traversal
     * @param graph
     * @param r root id
     */
    public static void updateNodeDepth(iGraph graph, int r)
    {
        List<Integer> Q = new ArrayList<>();
        List<Integer> Visited = new ArrayList<>();

        int d = 0;
        Visited.add(r);
        Q.add(r);
        while (!Q.isEmpty())
        {
            int n = Q.remove(0);
            graph.getNode(n).setDepth(d);

            List<Integer> children = graph.getNode(n).getChildren();
            children.forEach((v) -> {
                if (!Visited.contains(v))
                {
                    Q.add(v);
                    Visited.add(v);
                }
            });
            d++;
        }

    }

    /**
     * Get depth of a node from the graph
     * @param graph
     * @param v index of a node
     * @return
     */
    public static int getNodeDepth(iGraph graph, int v)
    {
        return graph.getNode(v).getDepth();
    }

    /**
     * Get index of a node w.r.t. its parent's list
     * @param graph
     * @param v index of a node w.r.t. node list
     * @return
     */
    public static int getChildIndex(iGraph graph, int v)
    {
        return graph.getNode(graph.getNode(v).getParent()).getChildren().indexOf(v);
    }

    /**
     * Get number of siblings of a node
     * @param graph
     * @param v index of a node
     * @return
     */
    public static int getNumSiblings(iGraph graph, int v)
    {
        return graph.getNode(graph.getNode(v).getParent()).getChildren().size();
    }

}
