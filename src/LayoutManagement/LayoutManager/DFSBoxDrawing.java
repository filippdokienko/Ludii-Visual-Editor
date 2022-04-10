package LayoutManagement.LayoutManager;

import LayoutManagement.GraphDrawing.NodePlacementRoutines;
import LayoutManagement.Math.Vector2D;
import model.interfaces.iGNode;
import model.interfaces.iGraph;

import java.util.ArrayList;
import java.util.List;

import static LayoutManagement.GraphDrawing.NodePlacementRoutines.translateByRoot;
import static LayoutManagement.GraphRoutines.getNodeDepth;
import static java.lang.Math.*;

public class DFSBoxDrawing implements LayoutMethod
{
    private iGraph graph;
    private final int C3j;
    private int freeY;
    private final double wY = 0.5;
    private final double wX = 0.75;
    private final int root;

    /**
     *
     * @param graph graph
     * @param C3j a non-negative integer constraint
     */
    public DFSBoxDrawing(iGraph graph, int root, int C3j)
    {
        this.C3j = C3j;
        this.graph = graph;
        freeY = 0;
        this.root = root;
    }

    private void initPlacement(int nodeId, int freeX)
    {
        if (graph.getNode(nodeId).getChildren() == null || graph.getNode(nodeId).getChildren().size() == 0)
        {
            Vector2D piInit = new Vector2D(freeX, freeY);
            freeY += graph.getNode(nodeId).getWidth()*wY;

            graph.getNode(nodeId).setPos(piInit);
        }
        else
        {
            List<Integer> nodeCh = graph.getNode(nodeId).getChildren();
            iGNode nFirst = graph.getNode(nodeCh.get(0));
            iGNode nLast = graph.getNode(nodeCh.get(nodeCh.size()-1));

            nodeCh.forEach((s) -> {
                initPlacement(s, (int) (freeX + getNodeDepth(graph, s)*graph.getNode(s).getWidth()*wX));

                iGNode nV = graph.getNode(nodeId);
                Vector2D piInit = new Vector2D(freeX,
                        nFirst.getPos().getY() +
                                min(C3j, nLast.getPos().getY() - nFirst.getPos().getY()));
                nV.setPos(piInit);
                freeY = max(freeY, (int) (nV.getPos().getY() + nV.getHeight()*wY));


            });

        }
    }

    private void shift(int root)
    {
        List<Integer> Q = new ArrayList<>();
        Q.add(root);
        List<Integer> childNodes;
        int nId;
        while (!Q.isEmpty())
        {
            nId = Q.remove(0);
            childNodes = graph.getNode(nId).getChildren();
            for (int i = childNodes.size()-1; i >= 0; i--)
            {
                if (i > 1)
                {
                    iGNode nV = graph.getNode(i);
                    iGNode nVl = graph.getNode(childNodes.get(i-1));
                    nV.setPos(nV.getPos().sub(new Vector2D(0, nVl.getPos().getY() + nVl.getHeight() )));
                }
                Q.add(childNodes.get(i));
            }
        }

    }

    @Override
    public void applyLayout()
    {
        freeY = 0;
        Vector2D oPos = graph.getRoot().getPos(); // TODO: TO FIX cannot get correct position of root node
        initPlacement(root,0);
        //shift(r);
        translateByRoot(graph, root, oPos);
        //NodePlacementRoutines.packLayers(graph, root);
        // translate graph by root vertex coordinates

        //NodePlacementRoutines.resolveNodeTranslation(graph, root);
    }
}
