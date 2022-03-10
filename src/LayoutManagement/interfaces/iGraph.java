package LayoutManagement.interfaces;

import LayoutManagement.GraphDrawing.MetaGraph.Edge;
import LayoutManagement.GraphDrawing.MetaGraph.Node;
import LayoutManagement.Math.Vector2D;

import java.util.HashMap;
import java.util.List;

/**
 * An interface to be adopted by a main graph
 * @author nic0gin
 */
public interface iGraph
{
    /**
     * Retrieves the root of the graph
     * @return
     */
    iNode getRoot();

    /**
     *
     * @return
     */
    HashMap<Integer, List<Integer>> getAdjacencyList();

    /**
     *
     * @return
     */
    List<Edge> getEdgeList();

    /**
     *
     * @return
     */
    HashMap<Integer, Node> getNodeList();

    /**
     *
     * @return
     */
    int[][] getAdjacencyMatrix();

    /**
     *
     * @param id
     * @return
     */
    Vector2D getVertexPos(int id);

    /**
     *
     * @param id
     * @param coords
     */
    void setVertexPos(int id, Vector2D coords);

    int getNodeNum();

    int getEdgeNum();

    int addNode();

    int addNode(String label);

    void addEdge(int From, int To);

}
