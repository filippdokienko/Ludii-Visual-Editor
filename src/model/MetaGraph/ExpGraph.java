package model.MetaGraph;

import LayoutManagement.GraphDrawing.DrawingFrame;
import LayoutManagement.Math.Vector2D;
import model.Edge;
import model.interfaces.iGNode;
import model.interfaces.iGraph;
import org.w3c.dom.Node;

import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static LayoutManagement.VisualEditor.LayoutConfigs.NODE_SIZE;

/**
 * Single instance of a graph to be displayed
 * @author nic0gin
 */

public class ExpGraph implements iGraph {

    //TODO: refactor with iGNode
    private ExpNode root;
    private List<Edge> edgeList;
    private HashMap<Integer, iGNode> nodeList;

    public ExpGraph() {

        nodeList = new HashMap<>();
        edgeList = new ArrayList<>();

        root = new ExpNode("root");
        nodeList.put(root.getId(), root);
    }

    public void updateNodePos(Vector2D pos) {
        // detect dragged node
        AtomicReference<Double> minDist = new AtomicReference<>(Double.MAX_VALUE);
        AtomicInteger clickedId = new AtomicInteger(0);
        nodeList.forEach((k,v) -> {
            Vector2D delta = pos.sub(v.getPos());
            double dist = delta.euclideanNorm();
            if (dist < NODE_SIZE && dist < minDist.get()) {
                minDist.set(dist);
                clickedId.set(v.getId());
            }
        });

        try {
            nodeList.get(clickedId.get()).setPos(pos);
        }
        catch (NullPointerException e)
        {

        }

    }

    @Override
    public HashMap<Integer, iGNode> getNodeList() {
        return nodeList;
    }

    public void randomizeNodePos() {
        nodeList.forEach((k, n)-> n.setPos(DrawingFrame.getRandomScreenPos()));
    }

    public Vector2D getNodePos(int id) {
        return nodeList.get(id).getPos();
    }

    public void removeNode(Vector2D pos) {
        // detect clicked node
        AtomicReference<Double> minDist = new AtomicReference<>(Double.MAX_VALUE);
        AtomicInteger clickedId = new AtomicInteger(0);
        nodeList.forEach((k,v) -> {
            Vector2D delta = pos.sub(v.getPos());
            double dist = delta.euclideanNorm();
            if (dist < NODE_SIZE && dist < minDist.get()) {
                minDist.set(dist);
                clickedId.set(v.getId());
            }
        });

        // remove edges
        edgeList.removeIf(e -> (e.getNodeA() == clickedId.get()) || (e.getNodeB() == clickedId.get()));

        // remove vertex
        nodeList.remove(clickedId.get());

    }

    public iGNode getNode(int id) {
        return nodeList.get(id);
    }

    @Override
    public iGNode getRoot() {
        return root;
    }

    @Override
    public void setRoot(int id) {

    }

    @Override
    public void setRoot(iGNode node) {

    }

    @Override
    public HashMap<Integer, List<Integer>> getAdjacencyList() {
        return null;
    }

    @Override
    public List<Edge> getEdgeList() {
        return edgeList;
    }

    @Override
    public void addEdge(int nodeA, int nodeB) {
        Edge e = new Edge(nodeA, nodeB);

        // TODO: get rid of this
        ((ExpNode) nodeList.get(nodeA)).addChildNode(nodeB);
        ((ExpNode) nodeList.get(nodeB)).addParent(nodeA);

        edgeList.add(e);
    }

    @Override
    public void addEdge(iGNode from, iGNode to) {

    }

    @Override
    public void addEdge(int from, int to, int field) {

    }

    @Override
    public int addNode(String data) {
        ExpNode n = new ExpNode(data);
        nodeList.put(n.getId(), n);
        return n.getId();
    }

    @Override
    public int addNode(iGNode node) {
        return 0;
    }

    @Override
    public int addNode() {
        ExpNode n = new ExpNode();
        nodeList.put(n.getId(), n);
        return n.getId();
    }




}
