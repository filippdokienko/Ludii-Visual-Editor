package LayoutManagement.GraphDrawing.MetaGraph;

import LayoutManagement.GraphDrawing.DrawingFrame;
import LayoutManagement.Math.Vector2D;
import LayoutManagement.interfaces.iGraph;
import LayoutManagement.interfaces.iNode;

import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static LayoutManagement.VisualEditor.LayoutConfigs.NODE_SIZE;

/**
 * Single instance of a graph to be displayed
 * @author nic0gin
 */

public class Graph implements iGraph {

    private Node root;
    private List<Edge> edgeList;
    private HashMap<Integer, Node> nodeList;
    private static Graph graphInstance;

    private Graph() {

        //root = new Node("root");
        nodeList = new HashMap<>();
        edgeList = new ArrayList<>();

    }

    public static Graph getGraphInstance() {
        if (Graph.graphInstance == null) {
            Graph.graphInstance = new Graph();
        }

        return Graph.graphInstance;
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

        nodeList.get(clickedId.get()).setPos(pos);
    }

    @Override
    public int getNodeNum() {
        return nodeList.size();
    }

    @Override
    public int getEdgeNum() {
        return edgeList.size();
    }

    @Override
    public HashMap<Integer, Node> getNodeList() {
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
        edgeList.removeIf(e -> (e.getNodeA().getId() == clickedId.get()) || (e.getNodeB().getId() == clickedId.get()));

        // remove vertex
        nodeList.remove(clickedId.get());

    }

    public Node getNode(int id) {
        return nodeList.get(id);
    }


    @Override
    public iNode getRoot() {
        return null;
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
    public int[][] getAdjacencyMatrix() {
        return new int[0][];
    }

    @Override
    public Vector2D getVertexPos(int id) {
        return null;
    }

    @Override
    public void setVertexPos(int id, Vector2D coords) {

    }

    @Override
    public void addEdge(int nodeA, int nodeB) {
        Edge e = new Edge(nodeA, nodeB);
        nodeList.get(nodeA).addNeighbor(nodeB);
        nodeList.get(nodeB).addNeighbor(nodeA);
        edgeList.add(e);
    }

    @Override
    public int addNode(String data) {
        Node n = new Node(data);
        nodeList.put(n.getId(), n);
        return n.getId();
    }

    @Override
    public int addNode() {
        Node n = new Node();
        nodeList.put(n.getId(), n);
        return n.getId();
    }




}
