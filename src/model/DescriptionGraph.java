package model;

import grammar.Ludeme;
import model.interfaces.iDescriptionGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Graph of LudemeNode objects
 * @author Filipp Dokienko
 */

public class DescriptionGraph implements iDescriptionGraph {

    List<LudemeNode> allLudemeNodes = new ArrayList<>();
    LudemeNode ROOT;

    public DescriptionGraph(){

    }

    public DescriptionGraph(LudemeNode root){
        this.ROOT = root;
    }

    @Override
    public void setRoot(LudemeNode ludemeNode){
        this.ROOT = ludemeNode;
    }

    @Override
    public LudemeNode getRoot() {
        return ROOT;
    }

    @Override
    public LudemeNode getNode(int id) {
        for(LudemeNode ln : allLudemeNodes){
            if(ln.getId() == id) return ln;
        }
        return null;
    }

    @Override
    public List<LudemeNode> getNodes() {
        return allLudemeNodes;
    }

    @Override
    public List<LudemeNode> getNodes(Ludeme ludeme) {
        List<LudemeNode> result = new ArrayList<>();
        for(LudemeNode ln : allLudemeNodes) if(ln.getLudeme() == ludeme) result.add(ln);
        return result;
    }

    @Override
    public List<LudemeNode> getNodes(String ludemeName) {
        List<LudemeNode> result = new ArrayList<>();
        for(LudemeNode ln : allLudemeNodes) if(Objects.equals(ln.getLudeme().getName(), ludemeName)) result.add(ln);
        return result;
    }

    @Override
    public void add(LudemeNode ludemeNode) {
        this.allLudemeNodes.add(ludemeNode);
    }

    /*@Override
    public void add(Ludeme ludeme, int x, int y) {
        LudemeNode ludemeNode = new LudemeNode(ludeme, x, y);
        this.allLudemeNodes.add(ludemeNode);
    }*/

    @Override
    public void remove(LudemeNode ludemeNode) {
        this.allLudemeNodes.remove(ludemeNode);
    }

    @Override
    public void addEdge(LudemeNode l1, LudemeNode l2) {

    }
}
