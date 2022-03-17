package model;

import grammar.Ludeme;
import model.interfaces.iGNode;
import model.interfaces.iGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Graph of LudemeNode objects
 * @author Filipp Dokienko
 */

public class DescriptionGraph implements iGraph {

    //TODO: change to the HashMap
    List<LudemeNode> allLudemeNodes = new ArrayList<>();
    LudemeNode ROOT;

    public DescriptionGraph(){

    }

    public DescriptionGraph(LudemeNode root){
        this.ROOT = root;
    }

    @Override
    public void setRoot(iGNode node){
        if (!allLudemeNodes.contains((LudemeNode) node)) allLudemeNodes.add((LudemeNode) node);
        this.ROOT = (LudemeNode) node;
    }

    @Override
    public iGNode getRoot() {
        return ROOT;
    }

    @Override
    public void setRoot(int id) {
        this.ROOT = (LudemeNode) getNode(id);
    }

    @Override
    public HashMap<Integer, List<Integer>> getAdjacencyList() {
        return null;
    }

    @Override
    public List<Edge> getEdgeList() {
        return null;
    }

    @Override
    public HashMap<Integer, iGNode> getNodeList() {
        return null;
    }

    @Override
    public LudemeNode getNode(int id) {
        for(LudemeNode ln : allLudemeNodes){
            if(ln.getId() == id) return ln;
        }
        return null;
    }

    @Override
    public int addNode() {
        return 0;
    }

    @Override
    public int addNode(String label) {
        return 0;
    }

    public List<LudemeNode> getNodes() {
        return allLudemeNodes;
    }

    public List<LudemeNode> getNodes(Ludeme ludeme) {
        List<LudemeNode> result = new ArrayList<>();
        for(LudemeNode ln : allLudemeNodes) if(ln.getLudeme() == ludeme) result.add(ln);
        return result;
    }

    public List<LudemeNode> getNodes(String ludemeName) {
        List<LudemeNode> result = new ArrayList<>();
        for(LudemeNode ln : allLudemeNodes) if(Objects.equals(ln.getLudeme().getName(), ludemeName)) result.add(ln);
        return result;
    }

    @Override
    public int addNode(iGNode ludemeNode) {
        this.allLudemeNodes.add((LudemeNode) ludemeNode);
        return ludemeNode.getId();
    }

    @Override
    public void addEdge(int from, int to) {

    }

    @Override
    public void addEdge(iGNode from, iGNode to) {

    }

    @Override
    public void addEdge(int from, int to, int field) {

    }

    public void remove(LudemeNode ludemeNode) {
        this.allLudemeNodes.remove(ludemeNode);
    }

    public String toLud() {
        return ROOT.getStringRepresentation();
    }

}
