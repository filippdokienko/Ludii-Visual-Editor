package model.interfaces;

import grammar.Ludeme;
import model.LudemeNode;

import java.util.List;

/**
 * Interface for a graph of LudemeNode objects
 * @author Filipp Dokienko
 */

public interface iDescriptionGraph {
    // get root LudemeNode
    LudemeNode getRoot();
    void setRoot(LudemeNode root);

    // get LudemeNode by id
    LudemeNode getNode(int id);

    // get all Nodes
    List<LudemeNode> getNodes();
    // get List of LudemeNode by Ludeme (name)
    List<LudemeNode> getNodes(Ludeme ludeme);
    List<LudemeNode> getNodes(String ludemeName);

    // add LudemeNode
    void add(LudemeNode ludemeNode);
    //void add(Ludeme ludeme, int x, int y);
    // remove LudemeNode
    void remove(LudemeNode ludemeNode);

    void addEdge(LudemeNode l1, LudemeNode l2); // TODO: Edge between Input & LudemeNode and not LudemeNodes !!!
}
