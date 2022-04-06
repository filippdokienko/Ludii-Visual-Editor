package LayoutManagement.GraphDrawing;

import LayoutManagement.Math.Vector2D;
import model.interfaces.iGraph;

import static LayoutManagement.GraphRoutines.getNodeDepth;
import static java.lang.Math.abs;

public final class NodePlacementRoutines
{

    /**
     * Translate node placement by oPos vector with respect to root
     * @param r
     * @param oPos
     * @param graph
     */
    public static void translateByRoot(iGraph graph, int r, Vector2D oPos)
    {
        Vector2D t = graph.getNode(r).getPos().sub(oPos);
        graph.getNodeList().forEach((i,v) -> {
            v.setPos(v.getPos().sub(t));
        });
    }

    /**
     * Set inner horizontal layer distance. a < b.
     * @param r root
     * @param a layer
     * @param b layer
     * @param d distance
     */
    private void setInLayoutDistance(iGraph graph, int r, int a, int b, int d)
    {
        if ((abs(b-a) != 1)) return;

        int nId = a;
        int layer = getNodeDepth(graph, nId);
        while (layer != a)
        {

        }
    }

}
