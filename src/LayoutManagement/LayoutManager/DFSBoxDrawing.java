package LayoutManagement.LayoutManager;

import LayoutManagement.Math.Vector2D;
import model.interfaces.iGNode;
import model.interfaces.iGraph;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class DFSBoxDrawing implements LayoutMethod
{
    private iGraph graph;
    private int C3j;
    private int freeY;
    private final double wY = 1.25;

    /**
     *
     * @param graph graph
     * @param C3j a non-negative integer constraint
     */
    public DFSBoxDrawing(iGraph graph, int C3j)
    {
        this.C3j = C3j;
        this.graph = graph;
        freeY = 0;
    }

    /**
     * Get trees and widths of trees
     */
    private int extraPrep()
    {
        return graph.getRoot().getId();
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
                initPlacement(s, freeX + getDepth(s)*graph.getNode(s).getHeight());

                iGNode nV = graph.getNode(nodeId);
                Vector2D piInit = new Vector2D(freeX,
                        nFirst.getPos().getY() +
                                min(C3j, nLast.getPos().getY() - nFirst.getPos().getY()));
                nV.setPos(piInit);
                freeY = max(freeY, (int) (nV.getPos().getY() + nV.getWidth()*wY));


            });

        }
    }

    private int getDepth(int nodeId) {
        int d = 0;
        int n = nodeId;
        while (graph.getNode(n).getId() != graph.getRoot().getId())
        {
            n = graph.getNode(n).getParent();
            d++;
        }
        return d;
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
            for (int i = childNodes.size()-1; i == 0; i--)
            {
                if (i > 1)
                {
                    iGNode nV = graph.getNode(i);
                    iGNode nVl = graph.getNode(childNodes.get(i-1));
                    nV.setPos(nV.getPos().sub(new Vector2D(0, nVl.getPos().getY() + nVl.getWidth() )));
                }
                Q.add(childNodes.get(i));
            }
        }

    }

    @Override
    public void applyLayout()
    {
        int r = extraPrep();
        initPlacement(r,0);
        shift(r);
        // translate graph by root vertex coordinates
    }
}
