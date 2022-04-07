package handler;

import model.DescriptionGraph;
import model.LudemeNode;

public class Handler {
    public static void addNode(DescriptionGraph graph, LudemeNode node){
        graph.addNode(node);
    }
    public static void removeNode(DescriptionGraph graph, LudemeNode node){
        graph.removeNode(node);
    }
    public static void updateInput(DescriptionGraph graph, LudemeNode node, int index, Object input){
        node.setProvidedInput(index, input);
    }
    public static void updatePosition(DescriptionGraph graph, LudemeNode node, int x, int y){
        node.setPos(x, y);
    }
}
